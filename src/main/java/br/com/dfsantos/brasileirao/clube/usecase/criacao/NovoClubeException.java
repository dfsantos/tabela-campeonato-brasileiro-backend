package br.com.dfsantos.brasileirao.clube.usecase.criacao;

public class NovoClubeException extends Exception {

  public static final String MESSAGE = "Erro ao tentar criar clube.";

  public NovoClubeException() {
    super(MESSAGE);
  }

}
