package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.domain.dto.VotarDto;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import com.jvfl.gestaodecisoes.domain.service.AssociadoService;
import com.jvfl.gestaodecisoes.domain.service.PautaService;
import com.jvfl.gestaodecisoes.domain.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MontaVotoService {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoservice;

    public Voto montaVotoService(VotarDto votarDto){
        Voto voto = new Voto();
        voto.setSessao(sessaoservice.findOrFail(votarDto.getSessao()));
        voto.setPauta(pautaService.findOrFail(votarDto.getPauta()));
        voto.setAssociado(associadoService.findOrFail(votarDto.getAssociado()));
        voto.setFavoravel(votarDto.getFavoravel());
        return voto;
    }
}
