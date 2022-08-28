package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeEmUsoException;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.api.repository.AssociacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AssociacaoService {

    @Autowired
    private AssociacaoRepository associacaoRepository;

    public Associacao salvar(Associacao associacao) {
        try{
            if(!StringUtils.hasLength(associacao.getDescricao())){
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associacao.class.getSimpleName(), "Nao eh possivel salvar uma Associacao sem nome.")
            );
        }
        return associacaoRepository.save(associacao);
    }

    public void excluir(Integer associacaoId) {
        try {
            associacaoRepository.deleteById(associacaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associacao.class.getSimpleName(), associacaoId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Constantes.ENTIDADE_EM_USO, Associacao.class.getSimpleName(), associacaoId)
            );
        }
    }

    public Associacao findOrFail(Integer associacaoId) {
        return associacaoRepository.findById(associacaoId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Associacao.class.getSimpleName(), associacaoId)
                        ));
    }
}

