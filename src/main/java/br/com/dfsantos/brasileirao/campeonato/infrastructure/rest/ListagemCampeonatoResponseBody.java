package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import br.com.dfsantos.brasileirao.campeonato.domain.entity.Campeonato;

import java.util.List;

public record ListagemCampeonatoResponseBody(
  List<Campeonato> data
) {
}
