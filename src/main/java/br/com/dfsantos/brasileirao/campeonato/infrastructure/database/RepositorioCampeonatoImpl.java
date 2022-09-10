package br.com.dfsantos.brasileirao.campeonato.infrastructure.database;

import br.com.dfsantos.brasileirao.campeonato.domain.entity.Campeonato;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Repository
public class RepositorioCampeonatoImpl implements RepositorioCampeonato {

  private Map<Integer, Campeonato> campeonatos = new HashMap<>();

  @Override
  public Optional<Campeonato> localizarPorAno(Integer ano) {
    return ofNullable(campeonatos.get(ano));
  }

  @Override
  public void armazenar(Campeonato campeonato) {
    campeonatos.put(campeonato.getAno(), campeonato);
  }

  @Override
  public List<Campeonato> listarTodosCampeonatos() {
    return campeonatos.entrySet().stream()
      .map(campeonato -> campeonato.getValue())
      .collect(toList());
  }

}
