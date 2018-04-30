package br.com.controle.conta.controleconta.entity;

import br.com.controle.conta.controleconta.comun.TipoTransfEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by danilo on 28/04/2018.
 */
@Entity
public class Transferencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String chave;
    @NotNull
    @ManyToOne
    private Conta contaEntrada;
    @ManyToOne
    private Conta contaSaida;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private TipoTransfEnum tipoTransferencia;

    private Date dtTransferencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Conta getContaEntrada() {
        return contaEntrada;
    }

    public void setContaEntrada(Conta contaEntrada) {
        this.contaEntrada = contaEntrada;
    }

    public Conta getContaSaida() {
        return contaSaida;
    }

    public void setContaSaida(Conta contaSaida) {
        this.contaSaida = contaSaida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransfEnum getTipoTransferencia() {
        return tipoTransferencia;
    }

    public void setTipoTransferencia(TipoTransfEnum tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }

    public Date getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(Date dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }
}
