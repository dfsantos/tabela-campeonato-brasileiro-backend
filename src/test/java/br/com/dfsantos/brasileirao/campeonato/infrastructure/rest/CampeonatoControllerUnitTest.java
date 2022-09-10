package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCase;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCaseInput;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCaseUnitTest;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.CampeonatoNaoEncontradoException;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CampeonatoJaExisteException;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCase;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCaseInput;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.NovoCampeonatoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.dfsantos.brasileirao.campeonato.domain.entity.CampeonatoUnitTest.*;
import static br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCaseUnitTest.output;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Controller de campeonatos: unit test")
class CampeonatoControllerUnitTest {

  private CampeonatoController controller;

  private CriacaoCampeonatoUseCase criacaoCampeonatoUseCase = mock(CriacaoCampeonatoUseCase.class);
  private BuscaCampeonatoUseCase buscaCampeonatoUseCase = mock(BuscaCampeonatoUseCase.class);

  @BeforeEach
  void setUp() {
    controller = new CampeonatoController(
      criacaoCampeonatoUseCase,
      buscaCampeonatoUseCase
    );
  }

  @Nested
  @DisplayName("ao tentar criar campeonato")
  class CriarCampeonato {

    @Test
    @DisplayName("inicia o caso de uso para criação de campeonato")
    void inicia_caso_de_uso_para_criar_campeonato() throws NovoCampeonatoException {
      //given
      when(criacaoCampeonatoUseCase.executar(any())).thenReturn(output());

      // when
      var retorno = controller.criarCampeonato(novoCampeonatoRequestBody());

      // then
      verify(criacaoCampeonatoUseCase).executar(any(CriacaoCampeonatoUseCaseInput.class));
    }

    @Test
    @DisplayName("lança exceção quando ocorre algum erro")
    void lanca_excecao_quando_erro_de_criacao() throws NovoCampeonatoException {
      //given
      when(criacaoCampeonatoUseCase.executar(any())).thenThrow(NovoCampeonatoException.class);

      // when // then
      assertThrows(NovoCampeonatoException.class, () -> controller.criarCampeonato(novoCampeonatoRequestBody()));
    }

    @Test
    @DisplayName("lança exceção para http status 409 quando campeonato já existe")
    void lanca_excecao_409_quando_campeonato_ja_existe() throws NovoCampeonatoException {
      //given
      when(criacaoCampeonatoUseCase.executar(any())).thenThrow(CampeonatoJaExisteException.class);

      // when // then
      assertThrows(CampeonatoJaExisteException.class, () -> controller.criarCampeonato(novoCampeonatoRequestBody()));
    }

  }

  @Nested
  @DisplayName("ao tentar buscar campeonato")
  class LocalizarCampeonato {

    @Test
    @DisplayName("inicia o caso de uso para busca de campeonato")
    void inicia_caso_de_uso_para_buscar_campeonato() throws CampeonatoNaoEncontradoException {
      //given
      when(buscaCampeonatoUseCase.executar(any())).thenReturn(BuscaCampeonatoUseCaseUnitTest.output());

      // when
      controller.buscarCampeonato(_2003);

      // then
      verify(buscaCampeonatoUseCase).executar(any(BuscaCampeonatoUseCaseInput.class));
    }

    @Test
    @DisplayName("retorna dados do campeonato encontrado")
    void retorna_dados_campeonato_encontrado() throws CampeonatoNaoEncontradoException {
      //given
      when(buscaCampeonatoUseCase.executar(any())).thenReturn(BuscaCampeonatoUseCaseUnitTest.output());

      // when
      var retorno = controller.buscarCampeonato(_2003);

      // then
      assertEquals(buscaCampeonatoResponseBody(), retorno);
    }

    @Test
    @DisplayName("lança exceção quando não encontra o campeonato")
    void lanca_excecao_quando_erro_de_criacao() throws CampeonatoNaoEncontradoException {
      //given
      when(buscaCampeonatoUseCase.executar(any())).thenThrow(CampeonatoNaoEncontradoException.class);

      // when // then
      assertThrows(CampeonatoNaoEncontradoException.class, () -> controller.buscarCampeonato(_2003));
    }

  }

  public static NovoCampeonatoRequestBody novoCampeonatoRequestBody() {
    return new NovoCampeonatoRequestBody(_2003, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);
  }

  public static BuscaCampeonatoResponseBody buscaCampeonatoResponseBody() {
    return new BuscaCampeonatoResponseBody(_2003, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);
  }

}
