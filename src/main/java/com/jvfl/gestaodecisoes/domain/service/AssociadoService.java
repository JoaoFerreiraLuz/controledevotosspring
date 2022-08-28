package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.AssociadoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeEmUsoException;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.dto.AssociadoDto;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaAssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    ValidaAssociadoService validaAssociadoService;

    public Associado findOrFail(Integer associadoId) {
        return associadoRepository.findById(associadoId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), associadoId)
                        ));

    }

    public AssociadoDto salvar(Associado associado) {
        try{
            if(!validaAssociadoService.associadoValido(associado)){
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associado.class.getSimpleName(), "Nao foi possivel salvar o associado. Verifique os campos 'nome' e 'associacaoId'.")
            );
        }
        return new AssociadoDto(associadoRepository.save(associado));
    }

    public void excluir(Integer associadoId) {
        try {
            associadoRepository.deleteById(associadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), associadoId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Constantes.ENTIDADE_EM_USO, Associado.class.getSimpleName(), associadoId)
            );
        }
    }
}
