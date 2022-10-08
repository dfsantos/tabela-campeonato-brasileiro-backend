package br.com.dfsantos.brasileirao.clube.domain;

public class NomeClubeInvalidoException extends Exception {

  private static final String MESSAGE = "Nome do clube é inválido.";

  public NomeClubeInvalidoException() {
    super(MESSAGE);
  }

}
