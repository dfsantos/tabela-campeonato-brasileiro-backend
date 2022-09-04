package br.com.dfsantos.brasileirao.domain.campeonato.entity.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NumeroParticipantesInvalidoException")
class NumeroParticipantesInvalidoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new NumeroParticipantesInvalidoException();
    }).hasMessage("Número de participantes inválido.");
  }

}