package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.SessaoRepository;
import com.jvfl.gestaodecisoes.domain.dto.SessaoDto;
import com.jvfl.gestaodecisoes.domain.model.Sessao;
import com.jvfl.gestaodecisoes.domain.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    SessaoService sessaoService;

    @GetMapping
    public List<SessaoDto> listar(){
        return sessaoRepository.findAll().stream().map(s -> sessaoService.getDto(s)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SessaoDto buscar(@PathVariable Integer id) {
        return new SessaoDto(sessaoService.findOrFail(id));
    }

    @GetMapping("/por-associacao/{associacaoId}")
    public List<SessaoDto> listarPorAssociacao(@PathVariable Integer associacaoId){
        return sessaoRepository.findAllByAssociacao_Id(associacaoId).stream().
                map(s -> sessaoService.getDto(s)).collect(Collectors.toList());
    }

    @GetMapping("/por-pautaId/{pautaId}")
    public List<SessaoDto> listarPorpauta(@PathVariable Integer pautaId){
        return sessaoRepository.findAllByPauta_Id(pautaId).stream()
                .map(s -> sessaoService.getDto(s)).collect(Collectors.toList());
    }

    @GetMapping("/por-dataSessao/{dataSessao}")
    public List<SessaoDto> listarPorDataSessao(@PathVariable Date date){
        return sessaoRepository.findAllByDataSessaoEquals(date).stream()
                .map(s -> sessaoService.getDto(s)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Sessao sessao) {sessaoService.salvar(sessao); }

    @PutMapping("/{sessaoId}") // Atualizacao total do objeto
    public SessaoDto atualizar(@PathVariable Integer sessaoId, @RequestBody Sessao sessao) {
        return sessaoService.update(sessaoId, sessao);
    }

    @PutMapping("/iniciar-sessao/{sessaoId}") // Atualizacao total do objeto
    public SessaoDto iniciarSessao(@PathVariable Integer sessaoId) {
        return sessaoService.iniciar(sessaoId);
    }

    @PutMapping("/finalizar-sessao/{sessaoId}") // Atualizacao total do objeto
    public SessaoDto finalizarSessao(@PathVariable Integer sessaoId) {
        return sessaoService.finalizar(sessaoId);
    }

    @DeleteMapping("/{sessaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer sessaoId) {
        sessaoService.excluir(sessaoId);
    }
}
