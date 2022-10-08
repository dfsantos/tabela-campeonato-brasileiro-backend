package br.com.dfsantos.brasileirao.clube.domain;

public class CidadeInvalidaException extends Exception {

  private static final String MESSAGE = "Cidade inválida.";

  public CidadeInvalidaException() {
    super(MESSAGE);
  }

}
