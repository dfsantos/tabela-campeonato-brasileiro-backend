package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NovoCampeonatoException")
class NovoCampeonatoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new NovoCampeonatoException();
    }).hasMessage("Erro ao tentar criar campeonato.");
  }

}
