package br.com.controle.conta.controleconta.repository;

import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by danilo on 26/04/2018.
 */
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query("Select t from Transferencia t join Conta ce on t.contaEntrada.id = ce.id where ce.pessoa.cpfCnpj = :cpfCnpj")
    List<Transferencia> historicoEntrada(@Param("cpfCnpj") long cpfCnpj);

    @Query("Select t from Transferencia t join Conta cs on t.contaSaida.id = cs.id where cs.pessoa.cpfCnpj = :cpfCnpj")
    List<Transferencia> historicoSaida(@Param("cpfCnpj") long cpfCnpj);
}
