package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.AssociacaoRepository;
import com.jvfl.gestaodecisoes.domain.dto.AssociacaoDto;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/associacoes")
public class AssociacaoController {

    @Autowired
    AssociacaoRepository associacaoRepository;

    @Autowired
    AssociacaoService associacaoSercive;

    @GetMapping
    public List<AssociacaoDto> listar() {
        return associacaoRepository.findAll().stream().map(a -> associacaoSercive.getDto(a)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AssociacaoDto buscar(@PathVariable Integer id) {
        return associacaoSercive.getDto(associacaoSercive.findOrFail(id));
    }

    @GetMapping("/por-descricao/{descricao}")
    public List<AssociacaoDto> listar(@PathVariable String descricao) {
        return associacaoRepository
                .findAllByDescricaoContaining(descricao)
                .stream()
                .map(a -> associacaoSercive.getDto(a))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Associacao associacao) {
        associacaoSercive.salvar(associacao);
    }

    @PutMapping("/{associacaoId}") // Atualizacao total do objeto
    public AssociacaoDto atualizar(@PathVariable Integer associacaoId, @RequestBody Associacao associacao) {
        return associacaoSercive.getDto(associacaoSercive.update(associacaoId, associacao));
    }

    @DeleteMapping("/{associacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer associacaoId) {
        associacaoSercive.excluir(associacaoId);
    }

}
