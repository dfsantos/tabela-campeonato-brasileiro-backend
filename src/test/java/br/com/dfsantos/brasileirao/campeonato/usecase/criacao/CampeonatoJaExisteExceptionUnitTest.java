package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CampeonatoJaExisteException")
class CampeonatoJaExisteExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new CampeonatoJaExisteException();
    }).hasMessage("Campeonato já existe.");
  }

}
