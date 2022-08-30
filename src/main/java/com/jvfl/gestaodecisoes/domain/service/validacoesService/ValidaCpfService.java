package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.exception.CPFInvalidoExcepton;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidaCpfService {

    public Boolean validaCPF(String cpf) {
        ResponseEntity<String> response;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://user-info.herokuapp.com/users/" + cpf;
            response = restTemplate.getForEntity(url, String.class);
            if (!response.getBody().contains("ABLE_TO_VOTE")) {
                throw new CPFInvalidoExcepton("O Cpf do Associado não foi aprovado.");
            }
        } catch (CPFInvalidoExcepton e) {
            throw new CPFInvalidoExcepton(
                String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associado.class.getSimpleName(), e.getMessage())
            );
        }
        return response.getBody().contains("ABLE_TO_VOTE");
    }
}
