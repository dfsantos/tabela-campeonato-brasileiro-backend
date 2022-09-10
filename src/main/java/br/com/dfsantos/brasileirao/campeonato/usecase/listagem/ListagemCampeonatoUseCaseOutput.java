package br.com.dfsantos.brasileirao.campeonato.usecase.listagem;

import br.com.dfsantos.brasileirao.campeonato.domain.entity.Campeonato;

import java.util.List;

public record ListagemCampeonatoUseCaseOutput(
  List<Campeonato> campeonatos
) {
}
