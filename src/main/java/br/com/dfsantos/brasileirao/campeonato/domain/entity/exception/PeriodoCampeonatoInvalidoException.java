package br.com.dfsantos.brasileirao.campeonato.domain.entity.exception;

public class PeriodoCampeonatoInvalidoException extends Exception {

  private static final String MESSAGE = "Período de início e término do campeonato inválido.";

  public PeriodoCampeonatoInvalidoException() {
    super(MESSAGE);
  }

}
