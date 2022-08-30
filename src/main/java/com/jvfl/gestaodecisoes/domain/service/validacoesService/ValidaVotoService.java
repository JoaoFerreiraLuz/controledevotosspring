package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.LocalDeVotoInvalidoExcepton;
import com.jvfl.gestaodecisoes.domain.exception.VotoJaRegistradoExcepton;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidaVotoService {

    @Autowired
    private VotoRepository votoRepository;

    public Boolean votoValido(Voto voto) {
        try {
            Optional<Voto> votoOptional =
                    votoRepository.findByAssociado_IdAndAndPauta_Id(
                            voto.getAssociado().getId(), voto.getPauta().getId());
            if (votoOptional.isPresent()) {
                throw new VotoJaRegistradoExcepton("Associado ja votou para esta pauta, aguarde a proxima!");
            }
            if (voto.getAssociado().getAssociacao().getId() != voto.getSessao().getAssociacao().getId()) {
                throw new LocalDeVotoInvalidoExcepton("O associado não pode votar em sessoes de outra associação.");
            }
        } catch (Exception e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Voto.class.getSimpleName(), e.getMessage())
            );
        }
        return Boolean.TRUE;
    }
}
