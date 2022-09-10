package br.com.dfsantos.brasileirao.campeonato.domain.entity.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("PeriodoCampeonatoInvalidoException")
class PeriodoCampeonatoInvalidoExceptionUnitTest {

  @Test
  @DisplayName("descreve a causa do erro")
  void descreve_motivo_excecao() {
    assertThatThrownBy(() -> {
      throw new PeriodoCampeonatoInvalidoException();
    }).hasMessage("Período de início e término do campeonato inválido.");
  }

}
