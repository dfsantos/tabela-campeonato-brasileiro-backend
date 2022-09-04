package br.com.dfsantos.brasileirao.domain.campeonato.entity.exception;

public class PeriodoCampeonatoInvalidoException extends Exception {

  private static final String MESSAGE = "Período de início e término do campeonato inválido.";

  public PeriodoCampeonatoInvalidoException() {
    super(MESSAGE);
  }

}
