package br.com.dfsantos.brasileirao.domain.campeonato.entity.exception;

public class AnoCampeonatoInvalidoException extends Exception {

  private static final String MESSAGE = "Ano do campeonato é inválido.";

  public AnoCampeonatoInvalidoException() {
    super(MESSAGE);
  }

}
