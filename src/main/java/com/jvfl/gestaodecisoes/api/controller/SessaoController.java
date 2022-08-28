package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.SessaoRepository;
import com.jvfl.gestaodecisoes.domain.dto.SessaoDto;
import com.jvfl.gestaodecisoes.domain.model.Sessao;
import com.jvfl.gestaodecisoes.domain.service.Sessaoservice;
import org.springframework.beans.BeanUtils;
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
    Sessaoservice sessaoservice;

    @GetMapping
    public List<SessaoDto> listar(){
        return (sessaoRepository.findAll()).stream().map(SessaoDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SessaoDto buscar(@PathVariable Integer id) {
        return new SessaoDto(sessaoservice.findOrFail(id));
    }

    @GetMapping("/por-associacao/{associacao}")
    public List<SessaoDto> listarPorAssociacao(@PathVariable Integer associacao){
        return (sessaoRepository.findSessaoByAssociacao_Id(associacao)).stream().map(SessaoDto::new).collect(Collectors.toList());
    }

    @GetMapping("/por-pautaId/{pauta}")
    public List<SessaoDto> listarPorpauta(@PathVariable Integer pauta){
        return (sessaoRepository.findSessaosByPauta_Id(pauta)).stream().map(SessaoDto::new).collect(Collectors.toList());
    }

//    @GetMapping("/por-dataSessao/{dataSessao}")
//    public List<SessaoDto> listarPorpauta(@PathVariable Date date){
//        return (sessaoRepository.findSessaosByData_sessao(date)).stream().map(SessaoDto::new).collect(Collectors.toList());
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Sessao sessao) {sessaoservice.salvar(sessao); }

    @PutMapping("/{sessaoId}") // Atualizacao total do objeto
    public SessaoDto atualizar(@PathVariable Integer sessaoId, @RequestBody Sessao sessao) {
        Sessao sessaoAtual = sessaoservice.findOrFail(sessaoId);
        BeanUtils.copyProperties(sessao, sessaoAtual, "id");
        return sessaoservice.salvar(sessaoAtual);
    }

    @DeleteMapping("/{sessaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer sessaoId) {
        sessaoservice.excluir(sessaoId);
    }


}
