package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Controller de campeonatos: unit test")
class CampeonatoControllerUnitTest {

  private CampeonatoController controller;

  private CriacaoCampeonatoUseCase criacaoCampeonatoUseCase = mock(CriacaoCampeonatoUseCase.class);

  @BeforeEach
  void setUp() {
    controller = new CampeonatoController(criacaoCampeonatoUseCase);
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

  public static NovoCampeonatoRequestBody novoCampeonatoRequestBody() {
    return new NovoCampeonatoRequestBody(ANO, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);
  }

}
