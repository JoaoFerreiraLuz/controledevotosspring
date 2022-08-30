package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.PautaRepository;
import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.PautaDto;
import com.jvfl.gestaodecisoes.domain.dto.ResultadoPautaDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PautaDto getDto(Pauta pauta) {
        return modelMapper.map(pauta, PautaDto.class);
    }

    public Pauta findOrFail(Integer pautaId) {
        return pautaRepository.findById(pautaId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Pauta.class.getSimpleName(), pautaId)
                        ));

    }

    public Pauta salvar(Pauta pauta) {
        try {
            if (!StringUtils.hasLength(pauta.getDescricao())) {
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Pauta.class.getSimpleName(),
                            "Nao foi possivel salvar a pauta.")
            );
        }
        return pautaRepository.save(pauta);
    }

    public void excluir(Integer pautaId) {
        try {
            pautaRepository.deleteById(pautaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    "Não foi possivel excluir a pauta"
            );
        }
    }

    public ResultadoPautaDto consultaResultadoPauta(Integer pautaId) {
        Pauta pauta = findOrFail(pautaId);
        List<Voto> votos = votoRepository.findVotosByPauta_Id(pautaId);
        return new ResultadoPautaDto(pauta,
                votos.stream().filter(v -> v.getFavoravel().equals(true)).count(),
                votos.stream().filter(v -> v.getFavoravel().equals(false)).count());
    }


}
