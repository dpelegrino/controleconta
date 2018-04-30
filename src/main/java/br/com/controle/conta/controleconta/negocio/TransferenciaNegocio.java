package br.com.controle.conta.controleconta.negocio;

import br.com.controle.conta.controleconta.comun.*;
import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Pessoa;
import br.com.controle.conta.controleconta.entity.Transferencia;
import br.com.controle.conta.controleconta.repository.ContaRepository;
import br.com.controle.conta.controleconta.repository.PessoaRepository;
import br.com.controle.conta.controleconta.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by danilo on 26/04/2018.
 */
@Service
public class TransferenciaNegocio {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public Response transferencia(Transferencia transferencia) {
        Response resp = validaTransferencia(transferencia);
        if (resp.getErrors() != null && resp.getErrors().size() > 0) {
            return resp;
        }
        transferencia.setDtTransferencia(new Date());

        transferenciaRepository.save(transferencia);

        if (transferencia.getTipoTransferencia().getCodigo() == TipoTransfEnum.ENTRE_FILIAIS.getCodigo()) {
            resp.setData(RestConstErrosEnum.TRANSFERENCIA_FILIAIS_SUCESSO);
        } else {
            resp.setData(RestConstErrosEnum.TRANSFERENCIA_APORTE_SUCESSO);
        }
        return resp;
    }

    public List<Transferencia> historico(Long cpfCnpj) {
        List<Transferencia> ret = new ArrayList<>();
        ret.addAll(transferenciaRepository.historicoEntrada(cpfCnpj));
        ret.addAll(transferenciaRepository.historicoSaida(cpfCnpj));
        return ret;
    }

    private Response validaTransferencia(Transferencia tranferencia) {
        Response<Conta> response = new Response<Conta>();
        Conta contaEntrada = null;

        if (tranferencia.getContaEntrada().getPessoa() == null || tranferencia.getContaEntrada().getPessoa().getCpfCnpj() == null) {
            response.getErrors().add(RestConstErrosEnum.CPFCNPJ_CONTAENTRADA_NULO);
        } else {
            contaEntrada = contaRepository.buscarPorCpfCnpjPessoa(tranferencia.getContaEntrada().getPessoa().getCpfCnpj());
            System.out.print(contaRepository.buscarPorCpfCnpjPessoa(tranferencia.getContaEntrada().getPessoa().getCpfCnpj()));
            if (contaEntrada == null) {
                response.getErrors().add(RestConstErrosEnum.CONTA_ENTRADA_INEXISTENTE + tranferencia.getContaEntrada().getPessoa().getCpfCnpj());
            } else {
                List<Transferencia> lisTransfEntrada = new ArrayList<>();
                tranferencia.setContaEntrada(contaEntrada);
                lisTransfEntrada.add(tranferencia);
                contaEntrada.setTransferenciaEntrada(lisTransfEntrada);
            }
        }

        if(tranferencia.getTipoTransferencia().getCodigo() == TipoTransfEnum.ENTRE_FILIAIS.getCodigo()){
            if (tranferencia.getContaSaida() == null || tranferencia.getContaSaida().getPessoa() == null || tranferencia.getContaSaida().getPessoa().getCpfCnpj() == null) {
                response.getErrors().add(RestConstErrosEnum.CPFCNPJ_CONTASAIDA_NULO);
            } else {
                Conta contaSaida = contaRepository.buscarPorCpfCnpjPessoa(tranferencia.getContaSaida().getPessoa().getCpfCnpj());
                if (contaSaida == null) {
                    response.getErrors().add(RestConstErrosEnum.CONTA_SAIDA_INEXISTENTE + tranferencia.getContaSaida().getPessoa().getCpfCnpj());
                } else {
                    List<Transferencia> lisTransfSaida = new ArrayList<>();
                    tranferencia.setContaSaida(contaSaida);
                    lisTransfSaida.add(tranferencia);
                    contaSaida.setTransferenciaSaida(lisTransfSaida);
                }
            }
        } else {
            if(tranferencia.getChave() == null) {
                response.getErrors().add(RestConstErrosEnum.APORTE_CHAVE_NULO);
            } else {
                if(contaEntrada.getTpConta().getCodigo() != TipoContaEnum.MATRIZ.getCodigo()){
                    response.getErrors().add(RestConstErrosEnum.APORTE_CONTA_NAO_MATRIZ);
                }
            }
        }
        return response;
    }
}
