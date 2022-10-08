package br.com.dfsantos.brasileirao.clube.domain;

public class UfInvalidaException extends Exception {

  private static final String MESSAGE = "UF inválida.";

  public UfInvalidaException() {
    super(MESSAGE);
  }

}
