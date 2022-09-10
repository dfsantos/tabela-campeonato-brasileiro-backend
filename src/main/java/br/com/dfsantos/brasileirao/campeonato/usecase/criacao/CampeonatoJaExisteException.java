package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

public class CampeonatoJaExisteException extends NovoCampeonatoException {

  public static final String MESSAGE = "Campeonato já existe.";

  public CampeonatoJaExisteException() {
    super(MESSAGE);
  }

}
