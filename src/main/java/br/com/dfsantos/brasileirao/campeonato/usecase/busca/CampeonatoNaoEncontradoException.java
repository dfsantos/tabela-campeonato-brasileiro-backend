package br.com.dfsantos.brasileirao.campeonato.usecase.busca;

public class CampeonatoNaoEncontradoException extends Exception {

  public static final String MESSAGE = "O campeonato não foi encontrado.";

  public CampeonatoNaoEncontradoException() {
    super(MESSAGE);
  }

}
