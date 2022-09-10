package br.com.dfsantos.brasileirao.campeonato.usecase.busca;

import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.dfsantos.brasileirao.campeonato.domain.entity.CampeonatoUnitTest.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("Caso de uso: buscar campeonato")
class BuscaCampeonatoUseCaseUnitTest {

  private BuscaCampeonatoUseCase buscaCampeonatoUseCase;

  private RepositorioCampeonato repositorioCampeonato = mock(RepositorioCampeonato.class);

  @BeforeEach
  void setUp() {
    buscaCampeonatoUseCase = new BuscaCampeonatoUseCase(repositorioCampeonato);
  }

  @Nested
  @DisplayName("executa com sucesso quando")
  class ExecutaComSucesso {

    @Test
    @DisplayName("encontra o campeonato e retorna seus dados")
    void encontra_campeonato_e_retorna_dados() throws CampeonatoNaoEncontradoException {
      // given
      given(repositorioCampeonato.localizarPorAno(anyInt()))
        .willReturn(of(campeonato()));

      // when
      BuscaCampeonatoUseCaseOutput resultadoBusca = buscaCampeonatoUseCase.executar(inputValido());

      // then
      assertEquals(output(), resultadoBusca);
    }

    @Test
    @DisplayName("não encontra o campeonato e lança uma exceção")
    void nao_encontra_campeonato_e_lanca_excecao() {
      // given
      given(repositorioCampeonato.localizarPorAno(anyInt()))
        .willReturn(empty());

      // when // then
      assertThrows(CampeonatoNaoEncontradoException.class, () -> buscaCampeonatoUseCase.executar(inputValido()));
    }

  }

  public static BuscaCampeonatoUseCaseInput inputValido() {
    return new BuscaCampeonatoUseCaseInput(_2003);
  }

  public static BuscaCampeonatoUseCaseOutput output() {
    return new BuscaCampeonatoUseCaseOutput(
      _2003,
      NUMERO_PARTICIPANTES,
      _29_03_2003,
      _14_12_2003
    );
  }

}
