package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.SessaoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.SessaoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeEmUsoException;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.model.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class Sessaoservice {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private AssociacaoService associacaoService;

    @Autowired
    private PautaService pautaService;

    public Sessao findOrFail(Integer sessaoId) {
        return sessaoRepository.findById(sessaoId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Sessao.class.getSimpleName(), sessaoId)
                        ));

    }

    public SessaoDto salvar(Sessao sessao) {
        try{
            Associacao associacao = associacaoService.findOrFail(sessao.getAssociacao().getId());
            Pauta pauta = pautaService.findOrFail(sessao.getPauta().getId());
            if(sessao.getData_sessao() == null || associacao == null || pauta == null ){
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associado.class.getSimpleName(), "Nao foi possivel salvar o associado. Verifique os campos 'nome' e 'associacaoId'.")
            );
        }
        return new SessaoDto(sessaoRepository.save(sessao));
    }

    public void excluir(Integer sessaoId) {
        try {
            sessaoRepository.deleteById(sessaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), sessaoId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Constantes.ENTIDADE_EM_USO, Associado.class.getSimpleName(), sessaoId)
            );
        }
    }
}
