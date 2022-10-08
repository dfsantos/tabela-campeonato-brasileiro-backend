package br.com.dfsantos.brasileirao.clube.domain;

import br.com.dfsantos.brasileirao.clube.domain.CidadeInvalidaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("CidadeInvalidaException")
class CidadeInvalidaExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new CidadeInvalidaException();
    }).hasMessage("Cidade inválida.");
  }

}
