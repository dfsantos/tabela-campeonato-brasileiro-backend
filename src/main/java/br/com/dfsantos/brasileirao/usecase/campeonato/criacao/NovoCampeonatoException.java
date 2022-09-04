package br.com.dfsantos.brasileirao.usecase.campeonato.criacao;

public class NovoCampeonatoException extends Exception {

  private static final String MESSAGE = "Erro ao tentar criar campeonato.";

  public NovoCampeonatoException() {
    super(MESSAGE);
  }

}
