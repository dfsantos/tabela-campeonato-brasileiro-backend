package br.com.dfsantos.brasileirao.campeonato.usecase.busca;

import java.time.LocalDate;

public record BuscaCampeonatoUseCaseOutput(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
