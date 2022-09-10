package br.com.dfsantos.brasileirao.usecase.campeonato.criacao;

import java.time.LocalDate;

public record CriacaoCampeonatoUseCaseInput(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
