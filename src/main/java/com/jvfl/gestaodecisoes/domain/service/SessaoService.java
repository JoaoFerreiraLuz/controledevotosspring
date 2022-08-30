package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.SessaoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.SessaoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.model.Sessao;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaSessaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private AssociacaoService associacaoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private ValidaSessaoService validaSessaoService;

    @Autowired
    private ModelMapper modelMapper;

    public SessaoDto getDto(Sessao sessao) {
        return modelMapper.map(sessao, SessaoDto.class);
    }

    public Sessao findOrFail(Integer sessaoId) {
        return sessaoRepository.findById(sessaoId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Sessao.class.getSimpleName(), sessaoId)
                        ));

    }

    public SessaoDto salvar(Sessao sessao) {
        try {
            validaSessaoService.verificarValidacoes(sessao);
            Associacao associacao = associacaoService.findOrFail(sessao.getAssociacao().getId());
            Pauta pauta = pautaService.findOrFail(sessao.getPauta().getId());
            if (sessao.getDataSessao() == null || associacao == null || pauta == null) {
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Sessao.class.getSimpleName(),
                            "Nao foi possivel salvar o associado. Verifique os campos 'nome' e 'associacaoId'.")
            );
        }
        return getDto(sessaoRepository.save(sessao));
    }

    public void excluir(Integer sessaoId) {
        try {
            sessaoRepository.deleteById(sessaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), sessaoId)
            );
        }
    }

    public SessaoDto update(Integer sessaoId, Sessao sessao) {
        try {
            Sessao sessaoAtual = findOrFail(sessaoId);
            BeanUtils.copyProperties(sessao, sessaoAtual, "id");
            validaSessaoService.verificarValidacoes(sessao);
            Associacao associacao = associacaoService.findOrFail(sessao.getAssociacao().getId());
            Pauta pauta = pautaService.findOrFail(sessao.getPauta().getId());
            if (sessao.getDataSessao() == null || associacao == null || pauta == null) {
                throw new BusinessExcepton(" As informaçoes da sessao estão incompletas. ");
            }
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Sessao.class.getSimpleName(),
                            "Nao foi possivel atualizar a sessao, verifique se os campos estão preenchidos. ")
            );
        }
        return getDto(sessaoRepository.save(sessao));
    }

    public SessaoDto iniciar(Integer sessaoId) {
        Sessao sessaoAtual;
        try {
            sessaoAtual = findOrFail(sessaoId);
            Sessao sessaoNova = new Sessao();
            sessaoNova.setInicioSessao(new Date());
            Date dataTermino = new Date(1 +
                    (Constantes.DURACAO_SESSAO * Constantes.ONE_MINUTE_IN_MILLIS));
            sessaoNova.setTerminoSessao(dataTermino);
            BeanUtils.copyProperties(sessaoNova, sessaoAtual, "id");
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Sessao.class.getSimpleName(),
                            "Não foi possivel iniciar a sessao.")
            );
        }
        return getDto(sessaoRepository.save(sessaoAtual));
    }

    public SessaoDto finalizar(Integer sessaoId) {
        SessaoDto sessaoDto;
        try {
            Sessao sessaoAtual = findOrFail(sessaoId);
            if (sessaoAtual.getTerminoSessao() != null && sessaoAtual.getTerminoSessao().before(new Date())) {
                throw new BusinessExcepton("Sessaõ não iniciada ou ja finalizou.");
            }
            sessaoAtual.setTerminoSessao(new Date());
            sessaoDto = getDto(sessaoRepository.save(sessaoAtual));
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Sessao.class.getSimpleName(),
                            "Não foi possivelfinalizar a sessão.")
            );
        }
        return sessaoDto;
    }

}
