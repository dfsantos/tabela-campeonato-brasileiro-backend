package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

import br.com.dfsantos.brasileirao.campeonato.domain.Campeonato;

import java.util.List;
import java.util.Optional;

public interface RepositorioCampeonato {

  Optional<Campeonato> localizarPorAno(Integer ano);

  void armazenar(Campeonato campeonato);

  List<Campeonato> listarTodosCampeonatos();

}
