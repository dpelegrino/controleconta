package br.com.controle.conta.controleconta.comun;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by danilo on 26/04/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoContaEnum implements Serializable {
    MATRIZ('M'),
    FILIAL('F');

    private char codigo;

    TipoContaEnum(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }
}
