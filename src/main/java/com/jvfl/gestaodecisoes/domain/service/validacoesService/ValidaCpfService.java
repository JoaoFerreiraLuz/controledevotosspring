package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.exception.BusinessExcepton;
import com.jvfl.gestaodecisoes.domain.model.Associado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidaCpfService {

    public Boolean cpfValido(String cpf){
        ResponseEntity<String> response;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://user-info.herokuapp.com/users/" + cpf;
            response = restTemplate.getForEntity(url, String.class);
            if(!response.getBody().contains("ABLE_TO_VOTE")){
                throw new BusinessExcepton(
                        String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associado.class.getSimpleName(), "O Cpf do Associado não foi aprovado.")
                );
            }
        }catch (BusinessExcepton e){
            throw new BusinessExcepton(
                    String.format(Constantes.REGRA_NEGOCIO_CONFILTANTE, Associado.class.getSimpleName(), "O Cpf do Associado não foi aprovado.")
            );
        }
        return response.getBody().contains("ABLE_TO_VOTE") ? true : false;
    }
}
