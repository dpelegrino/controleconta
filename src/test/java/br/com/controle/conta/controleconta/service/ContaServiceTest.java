package br.com.controle.conta.controleconta.service;

import br.com.controle.conta.controleconta.comun.Response;
import br.com.controle.conta.controleconta.comun.RestConstErrosEnum;
import br.com.controle.conta.controleconta.comun.TipoContaEnum;
import br.com.controle.conta.controleconta.comun.TipoPessoaEnum;
import br.com.controle.conta.controleconta.entity.Conta;
import br.com.controle.conta.controleconta.entity.Pessoa;
import br.com.controle.conta.controleconta.negocio.ContaNegocio;
import br.com.controle.conta.controleconta.repository.ContaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by danilo on 28/04/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ContaService.class, secure = false)
public class ContaServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ContaRepository contaRepository;

    @MockBean
    private ContaNegocio contaNegocio;

    @Test
    public void criarContaMatriz() throws Exception {
        String contaMatrizJson = "{\"nome\" : \"Conta Matriz\", \"tpConta\" : \"MATRIZ\", \"pessoa\" : {\"cpfCnpj\" : \"30307048829\", \"tipoPessoa\" : \"FISICA\",  \"dtNascimento\" : \"01/09/2018\"}}}";

        Response<String> resp= new Response<String>();
        resp.setData(RestConstErrosEnum.CONTA_MATRIZ_SUCESSO);

        Mockito.when(contaNegocio.incluirConta(Mockito.any(Conta.class))).thenReturn(resp);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/conta")
                .accept(MediaType.APPLICATION_JSON).content(contaMatrizJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void criarContaFilial() throws Exception {
        String contaFilialJson = "{\"nome\" : \"conta Filial\", \"tpConta\" : \"FILIAL\", \"pessoa\" : {\"cpfCnpj\" : \"30307048828\", \"tipoPessoa\" : \"FISICA\",  \"dtNascimento\" : \"01/09/2018\"}, \"contaPai\" : {\"pessoa\" : { \"cpfCnpj\" : \"30307048829\"}}}";

        Response<String> resp= new Response<String>();
        resp.setData(RestConstErrosEnum.CONTA_MATRIZ_SUCESSO);

        Conta contaMatriz = new Conta();
        contaMatriz.setId(1L);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        contaMatriz.setNome("Conta Matriz");
        contaMatriz.setDtCriacao(new Date());
        contaMatriz.setTpConta(TipoContaEnum.MATRIZ);
        pessoa.setCpfCnpj(30307048829L);
        pessoa.setTipoPessoa(TipoPessoaEnum.FISICA);
        pessoa.setDtNascimento(new Date());
        contaMatriz.setPessoa(pessoa);

        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(contaMatriz);
        Mockito.when(contaNegocio.incluirConta(Mockito.any(Conta.class))).thenReturn(resp);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/conta")
                .accept(MediaType.APPLICATION_JSON).content(contaFilialJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
