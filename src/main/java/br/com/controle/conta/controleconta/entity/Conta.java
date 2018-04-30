package br.com.controle.conta.controleconta.entity;

import br.com.controle.conta.controleconta.comun.SituacaoContaEnum;
import br.com.controle.conta.controleconta.comun.TipoContaEnum;
import br.com.controle.conta.controleconta.comun.TipoTransfEnum;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by danilo on 26/04/2018.
 */
@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @NotNull
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss", locale = "pt_BR")
    private Date dtCriacao;
    @NotNull
    private TipoContaEnum tpConta;
    private SituacaoContaEnum situacao;
    @JsonBackReference
    @ManyToOne
    private Conta contaPai;
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Pessoa pessoa;
    @OneToMany
    private List<Conta> filhas;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Transferencia> transferenciaEntrada;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Transferencia> transferenciaSaida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtCriacao() {
        return this.dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public List<Conta> getFilhas() {
        return filhas;
    }

    public void setFilhas(List<Conta> filhas) {
        this.filhas = filhas;
    }

    public TipoContaEnum getTpConta() {
        return tpConta;
    }

    public void setTpConta(TipoContaEnum tpConta) {
        this.tpConta = tpConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Conta getContaPai() {
        return contaPai;
    }

    public void setContaPai(Conta contaPai) {
        this.contaPai = contaPai;
    }

    public SituacaoContaEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoContaEnum situacao) {
        this.situacao = situacao;
    }

    public List<Transferencia> getTransferenciaEntrada() {
        return transferenciaEntrada;
    }

    public void setTransferenciaEntrada(List<Transferencia> transferenciaEntrada) {
        this.transferenciaEntrada = transferenciaEntrada;
    }

    public List<Transferencia> getTransferenciaSaida() {
        return transferenciaSaida;
    }

    public void setTransferenciaSaida(List<Transferencia> transferenciaSaida) {
        this.transferenciaSaida = transferenciaSaida;
    }
}
