package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import java.time.LocalDate;

public record BuscaCampeonatoResponseBody(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
