package br.com.dfsantos.brasileirao.campeonato.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("AnoCampeonatoInvalidoException")
class AnoCampeonatoInvalidoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new AnoCampeonatoInvalidoException();
    }).hasMessage("Ano do campeonato é inválido.");
  }

}
