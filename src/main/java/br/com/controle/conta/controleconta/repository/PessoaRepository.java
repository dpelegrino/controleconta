package br.com.controle.conta.controleconta.repository;

import br.com.controle.conta.controleconta.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by danilo on 26/04/2018.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCpfCnpj(Long cpf);
}
