package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

import java.time.LocalDate;

public record CriacaoCampeonatoUseCaseInput(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
