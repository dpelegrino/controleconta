package br.com.controle.conta.controleconta.comun;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by danilo on 26/04/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoTransfEnum implements Serializable {
    ENTRE_FILIAIS('F'),
    APORTE('A'),
    EXTORNO('E');

    private final char codigo;

    TipoTransfEnum(char codigo) {
        this.codigo = codigo;
    }
    public char getCodigo() {
        return codigo;
    }

}
