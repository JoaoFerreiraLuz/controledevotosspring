package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.api.repository.AssociacaoRepository;
import com.jvfl.gestaodecisoes.domain.dto.AssociacaoDto;
import com.jvfl.gestaodecisoes.domain.service.AssociacaoService;
import org.springframework.beans.BeanUtils;
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
    public List<AssociacaoDto> listar(){
        return (associacaoRepository.findAll()).stream().map(AssociacaoDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AssociacaoDto buscar(@PathVariable Integer id) {
        return new AssociacaoDto(associacaoSercive.findOrFail(id));
    }

    @GetMapping("/por-descricao/{descricao}")
    public List<AssociacaoDto> listar(@PathVariable String descricao){
        return (associacaoRepository.findAllByDescricaoContaining(descricao)).stream().map(AssociacaoDto::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Associacao associacao) {
        associacaoSercive.salvar(associacao);
    }

    @PutMapping("/{associacaoId}") // Atualizacao total do objeto
    public AssociacaoDto atualizar(@PathVariable Integer associacaoId, @RequestBody Associacao associacao) {
        Associacao associacaoAtual = associacaoSercive.findOrFail(associacaoId);
        BeanUtils.copyProperties(associacao, associacaoAtual, "id");
        return new AssociacaoDto(associacaoSercive.salvar(associacaoAtual));
    }

    @DeleteMapping("/{associacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer associacaoId) {
        associacaoSercive.excluir(associacaoId);
    }
/*
    @PatchMapping("/{associacaoId}") // Atualizacao parcial do objeto
    public ResponseEntity<?> atualizarParcial(@PathVariable Integer associacaoId, @RequestBody Map<String, Object> campos) {
        Associacao associacao = associacaoSercive.findOrFail(associacaoId);

        merge(campos, associacao);

        return atualizar(associacaoId, associacao);
    }

    private void merge(Map<String, Object> dadosOrigem, Associacao associacaoDestino) {
        ObjectMapper objectMapper = new ObjectMapper(); // converte mapper
        Associacao associacaoOrigem = objectMapper.convertValue(dadosOrigem, Associacao.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Associacao.class, nomePropriedade);
            field.setAccessible(Boolean.TRUE);

            Object novoValor = ReflectionUtils.findField(field, associacaoOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor + "\n");
            ReflectionUtils.setField(field, associacaoDestino, novoValor);
        });
    } */
}
