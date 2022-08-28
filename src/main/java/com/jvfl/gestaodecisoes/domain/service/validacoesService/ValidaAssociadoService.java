package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.domain.model.Associacao;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import com.jvfl.gestaodecisoes.domain.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidaAssociadoService {

    @Autowired
    private AssociacaoService associacaoService;

    @Autowired
    ValidaCpfService validaCpfService;

    public Boolean associadoValido(Associado associado){
        Associacao associacao = associacaoService.findOrFail(associado.getAssociacao().getId());
        return StringUtils.hasLength(associado.getNome()) && associacao != null && validaCpfService.cpfValido(associado.getCpf()) ? true : false;

    }
}
