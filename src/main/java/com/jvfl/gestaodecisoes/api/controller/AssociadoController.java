package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.AssociadoRepository;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.dto.AssociadoDto;
import com.jvfl.gestaodecisoes.domain.service.AssociadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    AssociadoService associadoService;

    @GetMapping
    public List<AssociadoDto> listar(){
        return associadoRepository.findAll().stream().map(a-> associadoService.getDto(a)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AssociadoDto buscar(@PathVariable Integer id) {
        return associadoService.getDto(associadoService.findOrFail(id));
    }

    @GetMapping("/por-nome/{nome}")
    public List<AssociadoDto> listar(@PathVariable String nome){
        return associadoRepository.findAssociadoByNomeContaining(nome).stream().map(a-> associadoService.getDto(a)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Associado associado) {associadoService.salvar(associado); }

    @PutMapping("/{associadoId}") // Atualizacao total do objeto
    public AssociadoDto atualizar(@PathVariable Integer associadoId, @RequestBody Associado associado) {
        Associado associadoAtual = associadoService.findOrFail(associadoId);
        BeanUtils.copyProperties(associadoAtual, associado, "id");
        return associadoService.salvar(associadoAtual);
    }// quem tem is na esquerda

    @DeleteMapping("/{associadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer associadoId) {
        associadoService.excluir(associadoId);
    }

}
