package com.jvfl.gestaodecisoes.domain.constantes;

public abstract class Constantes {

    public static final String ENTIDADE_INEXISTENTE = "A entidade [{%s}] de id:[{%d}], não existe no Banco de Dados.";
    public static final String ENTIDADE_EM_USO = "A entidade [{%s}] de id:[{%d}] está em uso por outras Entidades, não pode ser excluida.";
    public static final String REGRA_NEGOCIO_CONFILTANTE = "A entidade [{%s}], está violando uma regra de negocio, acao nao executada, motivo:[{%s}]";
    public static final String APROVADO = "APROVADA";
    public static final String REPROVADA = "REPROVADA";
    public static final String EMPATE = "EMPATE";

}