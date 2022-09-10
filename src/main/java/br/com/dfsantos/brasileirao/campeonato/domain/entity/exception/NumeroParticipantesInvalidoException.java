package br.com.dfsantos.brasileirao.campeonato.domain.entity.exception;

public class NumeroParticipantesInvalidoException extends Exception {

  private static final String MESSAGE = "Número de participantes inválido.";

  public NumeroParticipantesInvalidoException() {
    super(MESSAGE);
  }

}
