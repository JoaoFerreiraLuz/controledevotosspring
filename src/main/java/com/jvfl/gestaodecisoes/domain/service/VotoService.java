package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.VotarDto;
import com.jvfl.gestaodecisoes.domain.dto.VotoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.CPFInvalidoExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.exception.SessaoEncerradaExcepton;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.MontaVotoService;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaSessaoService;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaVotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private MontaVotoService montaVotoService;

    @Autowired
    private ValidaVotoService validaVotoService;

    @Autowired
    private ValidaSessaoService validaSessaoService;

    @Autowired
    private ModelMapper modelMapper;

    public VotoDto getDto(Voto voto) {
        return modelMapper.map(voto, VotoDto.class);
    }

    public VotoDto salvar(VotarDto votarDto) {
        Voto voto = new Voto();
        try {
            voto = montaVotoService.montaVotoService(votarDto);
            if (!validaVotoService.votoValido(voto)) {
                throw new CPFInvalidoExcepton("Associado nao esta apto a votar");
            }
            if (!validaSessaoService.validaInicioETerminoSessao(voto.getSessao())) {
                throw new SessaoEncerradaExcepton("Sessao encerrada, aguarde a proxima!");
            }
        } catch (Exception e) {
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Voto.class.getSimpleName(), e.getMessage())
            );
        }
        return getDto(votoRepository.save(voto));
    }

    public void excluir(Integer votoId) {
        try {
            votoRepository.deleteById(votoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), votoId)
            );
        }
    }
}
