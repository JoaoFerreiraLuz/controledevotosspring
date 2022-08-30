package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.PautaRepository;
import com.jvfl.gestaodecisoes.domain.dto.PautaDto;
import com.jvfl.gestaodecisoes.domain.dto.ResultadoPautaDto;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    PautaService pautaService;

    @GetMapping
    public List<PautaDto> listar(){
        return pautaRepository.findAll().stream().map(p -> pautaService.getDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PautaDto buscar(@PathVariable Integer id) {
        return pautaService.getDto(pautaService.findOrFail(id));
    }

    @GetMapping("/por-descricao/{descricao}")
    public List<PautaDto> listar(@PathVariable String descricao){
        return pautaRepository.findPautaByDescricao(descricao).stream().map(p -> pautaService.getDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/resultado-pauta/{pautaId}")
    public ResultadoPautaDto listar(@PathVariable Integer pautaId){
        return pautaService.consultaResultadoPauta(pautaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Pauta pauta) {pautaService.salvar(pauta); }

    @DeleteMapping("/{pautaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer pautaId) {
        pautaService.excluir(pautaId);
    }
}
