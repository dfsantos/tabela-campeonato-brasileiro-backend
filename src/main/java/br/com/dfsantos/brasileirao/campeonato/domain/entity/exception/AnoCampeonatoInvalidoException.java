package br.com.dfsantos.brasileirao.campeonato.domain.entity.exception;

public class AnoCampeonatoInvalidoException extends Exception {

  private static final String MESSAGE = "Ano do campeonato é inválido.";

  public AnoCampeonatoInvalidoException() {
    super(MESSAGE);
  }

}
