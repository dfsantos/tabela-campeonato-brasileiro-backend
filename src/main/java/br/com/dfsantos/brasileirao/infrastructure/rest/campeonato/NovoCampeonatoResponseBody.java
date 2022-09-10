package br.com.dfsantos.brasileirao.infrastructure.rest.campeonato;

import java.time.LocalDate;

record NovoCampeonatoResponseBody(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
