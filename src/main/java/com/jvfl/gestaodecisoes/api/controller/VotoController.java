package com.jvfl.gestaodecisoes.api.controller;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.dto.VotarDto;
import com.jvfl.gestaodecisoes.domain.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody VotarDto votarDto) {votoService.salvar(votarDto); }

    @DeleteMapping("/{votoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer votoId) {
        votoService.excluir(votoId);
    }
}
