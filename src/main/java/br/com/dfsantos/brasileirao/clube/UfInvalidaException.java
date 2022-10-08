package br.com.dfsantos.brasileirao.clube;

public class UfInvalidaException extends Exception {

  private static final String MESSAGE = "UF inválida.";

  public UfInvalidaException() {
    super(MESSAGE);
  }

}
