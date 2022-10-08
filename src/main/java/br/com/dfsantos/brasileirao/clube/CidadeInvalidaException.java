package br.com.dfsantos.brasileirao.clube;

public class CidadeInvalidaException extends Exception {

  private static final String MESSAGE = "Cidade inválida.";

  public CidadeInvalidaException() {
    super(MESSAGE);
  }

}
