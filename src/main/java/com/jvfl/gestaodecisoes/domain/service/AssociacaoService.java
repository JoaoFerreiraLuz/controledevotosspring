package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.AssociacaoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.AssociacaoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AssociacaoService {

    @Autowired
    private AssociacaoRepository associacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AssociacaoDto getDto(Associacao associacao) {
        return modelMapper.map(associacao, AssociacaoDto.class);
    }

    public Associacao salvar(Associacao associacao) {
        try {
            Associacao associacaoConsulta = findOrFail(associacao.getId());
            if (associacaoConsulta != null) {
                throw new BusinessExcepton("Associação já cadastrada.");
            }
            if (!StringUtils.hasLength(associacao.getDescricao())) {
                throw new BusinessExcepton("Preencha a descrição da associação.");
            }
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associacao.class.getSimpleName(),
                            "Nao é possivel salvar uma Associacao sem nome.")
            );
        }
        return associacaoRepository.save(associacao);
    }

    public Associacao update(Integer id, Associacao associacao) {
        try {
            Associacao associacaoAtual = findOrFail(id);
            BeanUtils.copyProperties(associacao, associacaoAtual, "id");
            if (!StringUtils.hasLength(associacao.getDescricao())) {
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associacao.class.getSimpleName(),
                            "Nao é possivel salvar uma Associacao sem nome.")
            );
        }
        return associacaoRepository.save(associacao);
    }

    public void excluir(Integer associacaoId) {
        try {
            associacaoRepository.deleteById(associacaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    "Não foi localizada a pauta para exclusão."
            );
        }
    }

    public Associacao findOrFail(Integer associacaoId) {
        return associacaoRepository.findById(associacaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(Constantes.ENTIDADE_INEXISTENTE, Associacao.class.getSimpleName(), associacaoId)
                ));
    }
}

