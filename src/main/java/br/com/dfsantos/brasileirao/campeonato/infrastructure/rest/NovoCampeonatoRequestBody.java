package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import java.time.LocalDate;

record NovoCampeonatoRequestBody(
  Integer ano,
  Integer numeroParticipantes,
  LocalDate dataInicio,
  LocalDate dataTermino
) {
}
