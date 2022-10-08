package br.com.dfsantos.brasileirao.clube;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("UfInvalidaException")
class UfInvalidaExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new UfInvalidaException();
    }).hasMessage("UF inválida.");
  }

}
