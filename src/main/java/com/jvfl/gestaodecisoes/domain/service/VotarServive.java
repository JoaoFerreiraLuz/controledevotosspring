package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.VotoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotarServive {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private PautaService pautaService;


    public VotoDto salvar(Voto voto) {
        try{
            Associado associado = associadoService.findOrFail(voto.getAssociado().getId());
            Pauta pauta = pautaService.findOrFail(voto.getPauta().getId());
            if(voto.getFavoravel() == null || associado == null || pauta == null){
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Voto.class.getSimpleName(), ".")
            );
        }
        return new VotoDto(votoRepository.save(voto));
    }

}
