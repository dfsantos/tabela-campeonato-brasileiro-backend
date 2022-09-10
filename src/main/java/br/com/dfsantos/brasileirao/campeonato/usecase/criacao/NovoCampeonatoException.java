package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

public class NovoCampeonatoException extends Exception {

  private static final String MESSAGE = "Erro ao tentar criar campeonato.";

  public NovoCampeonatoException() {
    super(MESSAGE);
  }

}
