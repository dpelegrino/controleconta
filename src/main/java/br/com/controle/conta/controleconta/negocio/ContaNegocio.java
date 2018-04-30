package br.com.controle.conta.controleconta.negocio;

import br.com.controle.conta.controleconta.comun.*;
import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Pessoa;
import br.com.controle.conta.controleconta.repository.ContaRepository;
import br.com.controle.conta.controleconta.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by danilo on 26/04/2018.
 */
@Service
public class ContaNegocio {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Response incluirConta(Conta conta) {
        Response resp = validaInclusao(conta);
        if (resp.getErrors() != null && resp.getErrors().size() > 0) {
            return resp;
        }
        conta.setDtCriacao(new Date());
        conta.setSituacao(SituacaoContaEnum.ATIVA);
        conta.getPessoa().setConta(conta);

        contaRepository.save(conta);

        if (conta.getTpConta().getCodigo() == TipoContaEnum.FILIAL.getCodigo()) {
            resp.setData(RestConstErrosEnum.CONTA_FILIAL_SUCESSO);
        } else {
            resp.setData(RestConstErrosEnum.CONTA_MATRIZ_SUCESSO);
        }
        return resp;
    }

    public Conta buscarPorCpfCnpjPessoa(Long cpfCnpj) {
        return contaRepository.buscarPorCpfCnpjPessoa(cpfCnpj);
    }

    public Conta buscarFilhasPorCpfCnpjPessoa(Long cpfCnpjPai, Long cpfCnpjFilha) {
        return contaRepository.buscarFilhaPorCpfCnpjPessoa(cpfCnpjPai, cpfCnpjFilha);
    }

    private Response validaInclusao(Conta contaInclusao) {
        Response<Conta> response = new Response<Conta>();

        if (contaInclusao.getPessoa().getCpfCnpj() == null) {
            response.getErrors().add(RestConstErrosEnum.CONTA_CPFCNPJ_NULO);
        } else {
            Pessoa pessoa = pessoaRepository.findByCpfCnpj(contaInclusao.getPessoa().getCpfCnpj());
            if (pessoa != null) {
                response.getErrors().add(RestConstErrosEnum.CONTA_CADASTRADA + contaInclusao.getPessoa().getCpfCnpj());
            }
        }

        if (contaInclusao.getPessoa().getTipoPessoa() == null) {
            response.getErrors().add(RestConstErrosEnum.PESSOA_TIPO_NULO);
        } else {
            if (contaInclusao.getPessoa().getTipoPessoa().getCodigo() == TipoPessoaEnum.FISICA.getCodigo()) {
                if (contaInclusao.getPessoa().getDtNascimento() == null) {
                    response.getErrors().add(RestConstErrosEnum.PESSOA_FISISCA_DTNASC_NULO);
                }
            } else {
                if (contaInclusao.getPessoa().getNomeFantasia() == null) {
                    response.getErrors().add(RestConstErrosEnum.PESSOA_JURIDICA_NOMFANTASIA_NULO);
                }
            }
        }

        if ((contaInclusao).getTpConta().getCodigo() == TipoContaEnum.FILIAL.getCodigo()) {
            if (contaInclusao.getContaPai() == null || contaInclusao.getContaPai().getPessoa().getCpfCnpj() == null) {
                response.getErrors().add(RestConstErrosEnum.CPFCNPJ_CONTA_PAI_NULO);
            } else {
                Conta contaPai = contaRepository.buscarPorCpfCnpjPessoa(contaInclusao.getContaPai().getPessoa().getCpfCnpj());
                if (contaPai == null) {
                    response.getErrors().add(RestConstErrosEnum.CONTA_PAI_INEXISTENTE + contaInclusao.getContaPai().getPessoa().getCpfCnpj());
                } else {
                    List<Conta> listFilhas = new ArrayList<>();
                    listFilhas.add(contaInclusao);
                    contaInclusao.setContaPai(contaPai);
                    contaPai.setFilhas(listFilhas);
                }
            }
        } else {
            if (contaInclusao.getContaPai() != null) {
                response.getErrors().add(RestConstErrosEnum.CONTA_MATRIZ_SEM_PAI);
            }
        }
        return response;
    }
}
