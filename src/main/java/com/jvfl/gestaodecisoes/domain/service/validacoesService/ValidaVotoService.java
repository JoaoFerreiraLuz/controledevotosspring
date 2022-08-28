package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidaVotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ValidaCpfService validaCpfService;

    public Boolean votoValido(Voto voto){
        List<Voto> votos = votoRepository.findVotosByPauta_Id(voto.getPauta().getId());
        Voto votoAux = votos.stream().filter(v -> v.getAssociado().getId().equals(voto.getAssociado().getId())).findFirst().orElse(null);
        return validaCpfService.cpfValido(voto.getAssociado().getCpf()) && votoAux == null ? true : false;
    }
}
