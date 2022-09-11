package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCaseOutput;

public record BuscaCampeonatoResponseBody(
  BuscaCampeonatoUseCaseOutput data
) {
}
