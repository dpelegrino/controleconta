package br.com.controle.conta.controleconta.service;

import br.com.controle.conta.controleconta.comun.Response;
import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Pessoa;
import br.com.controle.conta.controleconta.negocio.ContaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by danilo on 25/04/2018.
 * Serviços referente a Conta
 */
@RestController
@RequestMapping("/conta")
public class ContaService {

    @Autowired
    private ContaNegocio contaNegocio;

    Response<Conta> response = new Response<Conta>();

    @PostMapping
    //Chamada do serviço de inclusao da conta(POST)
    public ResponseEntity<Response<Conta>> incluir(@Valid @RequestBody Conta conta) {

        try {
            //Efetua chamada de negocio para inclusao da conta
            response = contaNegocio.incluirConta(conta);
            //Caso obtenha erro de validação no negocio, retorna Bad Request e a descrição do erro(Setado no negocio)
            if (response.getErrors() != null && response.getErrors().size() > 0) {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        //Retorna ok caso não tenha encontrado nenhum problema no negocio.
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cnpj}")
    public Conta pesquisarPorCpfCnpj(@PathVariable("cnpj") Long cnpj) {
        return contaNegocio.buscarPorCpfCnpjPessoa(cnpj);
    }

    @GetMapping("/{cpfCnpjPai}/{cpfCnpjFilha}")
    public Conta pesquisarPorNome(@PathVariable Long cpfCnpjPai, @PathVariable Long cpfCnpjFilha ) {
        return contaNegocio.buscarFilhasPorCpfCnpjPessoa(cpfCnpjPai, cpfCnpjFilha);
    }

}
