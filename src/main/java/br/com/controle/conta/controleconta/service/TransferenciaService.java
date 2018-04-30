package br.com.controle.conta.controleconta.service;

import br.com.controle.conta.controleconta.comun.Response;
import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Transferencia;
import br.com.controle.conta.controleconta.negocio.ContaNegocio;
import br.com.controle.conta.controleconta.negocio.TransferenciaNegocio;
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
@RequestMapping("/transferencia")
public class TransferenciaService {

    @Autowired
    private TransferenciaNegocio transferenciaNegocio;

    Response<Transferencia> response = new Response<Transferencia>();

    @PostMapping
    //Chamada do serviço de inclusao da conta(POST)
    public ResponseEntity<Response<Transferencia>> tranferir(@Valid @RequestBody Transferencia transferencia) {

        try {
            //Efetua chamada de negocio para inclusao da conta
            response = transferenciaNegocio.transferencia(transferencia);
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

    @GetMapping("/historico/{cnpj}")
    public List<Transferencia> historico(@PathVariable("cnpj") Long cnpj) {
        return transferenciaNegocio.historico(cnpj);
    }

}
