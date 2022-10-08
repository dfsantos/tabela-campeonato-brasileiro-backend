package br.com.dfsantos.brasileirao.clube.usecase.criacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NovoClubeException")
class NovoClubeExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new NovoClubeException();
    }).hasMessage("Erro ao tentar criar clube.");
  }

}
