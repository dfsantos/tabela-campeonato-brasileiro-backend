package br.com.dfsantos.brasileirao.usecase.campeonato.criacao;

import br.com.dfsantos.brasileirao.domain.campeonato.entity.Campeonato;

import java.util.Optional;

public interface RepositorioCampeonato {

  Optional<Campeonato> localizarPorAno(Integer ano);

}
