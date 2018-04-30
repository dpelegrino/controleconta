package br.com.controle.conta.controleconta.comun;

/**
 * Created by danilo on 29/04/2018.
 */
public final class RestConstErrosEnum {
    public static final String CONTA_CADASTRADA = "Conta ja cadastrada para este CPF/CNPJ numero: ";
    public static final String TRANSFERENCIA_FILIAIS_SUCESSO = "Tranferencia entre Filiais realizada com sucesso.";
    public static final String TRANSFERENCIA_APORTE_SUCESSO = "Aporte realizado com sucesso!";
    public static final String CONTA_CPFCNPJ_NULO = "Cpf/Cnpj deve ser informado!";
    public static final String PESSOA_TIPO_NULO = "Tipo de pessoa deve ser informado!";
    public static final String PESSOA_FISISCA_DTNASC_NULO = "Data de nascimento deve ser informada para tipo de pessoa fisica!";
    public static final String PESSOA_JURIDICA_NOMFANTASIA_NULO = "Nome Fantasia deve ser informada para tipo de pessoa juridica!";
    public static final String CPFCNPJ_CONTAENTRADA_NULO = "Cpf/Cnpj da conta que recebera a transferencia não informado!";
    public static final String CPFCNPJ_CONTA_PAI_NULO = "Cpf/Cnpj da conta pai deve ser informado para contas do tipo FILIAL!";
    public static final String CPFCNPJ_CONTASAIDA_NULO = "Cpf/Cnpj da conta que transferira o valor não informado!";
    public static final String CONTA_PAI_INEXISTENTE =  "Conta Pai Inexistente para Cpf/Cnpj: ";
    public static final String CONTA_ENTRADA_INEXISTENTE = "Conta que recebera a tranferencia Inexistente para este Cpf/Cnpj : ";
    public static final String CONTA_SAIDA_INEXISTENTE = "Conta que transferira o valor Inexistente para este Cpf/Cnpj : ";
    public static final String APORTE_CHAVE_NULO = "Necessario informar chave  para transferencias do tipo Aporte!";
    public static final String CONTA_MATRIZ_SEM_PAI = "Conta Matriz não pode ter conta pai";
    public static final String APORTE_CONTA_NAO_MATRIZ = "Somente conta matriz pode receber aporte!";
    public static final String NECESSARIO_CONTAS_FILHAS =  "Necessario contas filhas para inclusão!";
    public static final String CONTA_MATRIZ_SUCESSO =  "Conta Matriz cadastrada com sucesso!";
    public static final String CONTA_FILIAL_SUCESSO =  "Conta Filial cadastrada com sucesso!";


}
