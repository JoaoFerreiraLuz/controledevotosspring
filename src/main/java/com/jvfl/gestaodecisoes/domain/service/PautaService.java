package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.PautaRepository;
import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.ResultadoPautaDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeEmUsoException;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Pauta findOrFail(Integer pautaId) {
        return pautaRepository.findById(pautaId)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format(Constantes.ENTIDADE_INEXISTENTE, Pauta.class.getSimpleName(), pautaId)
                        ));

    }

    public Pauta salvar(Pauta pauta) {
        try{
            if(!StringUtils.hasLength(pauta.getDescricao())){
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Pauta.class.getSimpleName(), "Nao foi possivel salvar o associado. Verifique os campos 'nome' e 'associacaoId'.")
            );
        }
        return pautaRepository.save(pauta);
    }

    public void excluir(Integer pautaId) {
        try {
            pautaRepository.deleteById(pautaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Pauta.class.getSimpleName(), pautaId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Constantes.ENTIDADE_EM_USO, Pauta.class.getSimpleName(), pautaId)
            );
        }
    }

    public ResultadoPautaDto consultaResultadoPauta(Integer pautaId){
        Pauta pauta = findOrFail(pautaId);
        List<Voto> votos = votoRepository.findVotosByPauta_Id(pautaId);
        return new ResultadoPautaDto(pauta,
                votos.stream().filter(v -> v.getFavoravel().equals(true)).count(),
                votos.stream().filter(v -> v.getFavoravel().equals(false)).count());
    }


}
