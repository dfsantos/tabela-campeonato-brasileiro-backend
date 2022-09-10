package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

public class NovoCampeonatoException extends Exception {

  public static final String MESSAGE = "Erro ao tentar criar campeonato.";

  public NovoCampeonatoException() {
    super(MESSAGE);
  }

  public NovoCampeonatoException(final String message) {
    super(message);
  }

}
