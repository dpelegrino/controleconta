package br.com.controle.conta.controleconta.repository;

import br.com.controle.conta.controleconta.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by danilo on 26/04/2018.
 */
public interface ContaRepository extends JpaRepository<Conta, Long> {
    //@Query("Select c from Conta c join Pessoa p on c.id = p.conta.id where p.cpfCnpj = :cpfCnpj ")
    @Query("Select c from Conta c where c.pessoa.cpfCnpj = :cpfCnpj ")
    Conta buscarPorCpfCnpjPessoa(@Param("cpfCnpj") long cpfCnpj);

    @Query("Select c from Conta c join Pessoa p on c.id = p.conta.id where p.cpfCnpj = :cpfCnpjFilha and c.contaPai.pessoa.cpfCnpj = :cpfCnpjPai")
    Conta buscarFilhaPorCpfCnpjPessoa(@Param("cpfCnpjPai") long cpfCnpjPai, @Param("cpfCnpjFilha") long cpfCnpjFilha);

   /* Query query = entityManager.createQuery("SELECT di FROM Device di JOIN di.user u WHERE u.id=:userId and di.deviceName=:deviceName");*/
}
