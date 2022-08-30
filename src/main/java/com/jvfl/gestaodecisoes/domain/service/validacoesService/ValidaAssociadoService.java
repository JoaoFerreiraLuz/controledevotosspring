package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.api.repository.AssociadoRepository;
import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaAssociadoService {

    @Autowired
    private AssociacaoService associacaoService;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    ValidaCpfService validaCpfService;

    public Boolean associadoValido(Associado associado) {
        Associado associadoAux = associadoRepository.findAssociadoByCpfEquals(associado.getCpf());
        Associacao associacao = associacaoService.findOrFail(associado.getAssociacao().getId());
        return associadoAux == null && associacao != null && validaCpfService.validaCPF(associado.getCpf());
    }
}
