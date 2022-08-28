package com.jvfl.gestaodecisoes.domain.service;

import com.jvfl.gestaodecisoes.api.repository.VotoRepository;
import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.dto.VotarDto;
import com.jvfl.gestaodecisoes.domain.dto.VotoDto;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeEmUsoException;
import com.jvfl.gestaodecisoes.domain.exception.EntidadeNaoEncontradaException;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.model.Voto;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.MontaVotoService;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaSessaoService;
import com.jvfl.gestaodecisoes.domain.service.validacoesService.ValidaVotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public VotoDto salvar(VotarDto votarDto) {
        Voto voto = new Voto();
        String mensagemErro = "";
        try{
            voto = montaVotoService.montaVotoService(votarDto);
            if(!validaVotoService.votoValido(voto)){
                mensagemErro = "Eleitor nao esta apto a votar";
                throw new BusinessExcepton();
            }
            if(!validaSessaoService.validaInicioETerminoSessao(voto.getSessao())){
                mensagemErro = "Cessao encerrada, aguarde a proxima!";
                throw new BusinessExcepton();
            }
        } catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Voto.class.getSimpleName(), mensagemErro)
            );
        }
        return new VotoDto(votoRepository.save(voto));
    }

    public void excluir(Integer votoId) {
        try {
            votoRepository.deleteById(votoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(Constantes.ENTIDADE_INEXISTENTE, Associado.class.getSimpleName(), votoId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(Constantes.ENTIDADE_EM_USO, Associado.class.getSimpleName(), votoId)
            );
        }
    }
}
