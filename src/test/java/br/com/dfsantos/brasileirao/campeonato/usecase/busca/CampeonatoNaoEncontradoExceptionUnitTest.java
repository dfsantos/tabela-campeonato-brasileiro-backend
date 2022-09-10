package br.com.dfsantos.brasileirao.campeonato.usecase.busca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CampeonatoNaoEncontradoException")
class CampeonatoNaoEncontradoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new CampeonatoNaoEncontradoException();
    }).hasMessage("O campeonato não foi encontrado.");
  }

}
