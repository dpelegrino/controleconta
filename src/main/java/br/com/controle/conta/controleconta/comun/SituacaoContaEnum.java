package br.com.controle.conta.controleconta.comun;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by danilo on 26/04/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SituacaoContaEnum implements Serializable {
    ATIVA('A'),
    BLOQUEADA('B'),
    CANCELADA('C');

    private final char codigo;

    SituacaoContaEnum(char codigo) {
        this.codigo = codigo;
    }
    public char getCodigo() {
        return codigo;
    }

}
