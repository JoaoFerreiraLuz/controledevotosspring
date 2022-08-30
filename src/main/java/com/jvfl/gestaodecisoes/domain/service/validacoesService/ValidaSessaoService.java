package com.jvfl.gestaodecisoes.domain.service.validacoesService;

import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.model.Sessao;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ValidaSessaoService {

    public void verificarValidacoes(Sessao sessao) {
        if (validaInicioETerminoSessao(sessao)) {
            ajustarDuracaoDaSessao(sessao);
        }
        return;
    }

    private void ajustarDuracaoDaSessao(Sessao sessao) {
        if (sessao.getDataSessao() == null) {
            sessao.setDataSessao(new Date());
        }
        if (sessao.getInicioSessao() == null) {
            sessao.setInicioSessao(new Date());
        }
        long curTimeInMs = sessao.getInicioSessao().getTime();
        Date dataTermino = new Date(curTimeInMs +
                (Constantes.DURACAO_SESSAO * Constantes.ONE_MINUTE_IN_MILLIS));
        sessao.setTerminoSessao(dataTermino);
        return;
    }

    public Boolean validaInicioETerminoSessao(Sessao sessao) {
        return sessao.getInicioSessao() == null || sessao.getTerminoSessao() == null;
    }
}
