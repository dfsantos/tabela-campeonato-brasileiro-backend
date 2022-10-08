package br.com.dfsantos.brasileirao.clube.domain;

import br.com.dfsantos.brasileirao.clube.domain.NomeClubeInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NomeClubeInvalidoException")
class NomeClubeInvalidoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new NomeClubeInvalidoException();
    }).hasMessage("Nome do clube é inválido.");
  }

}
