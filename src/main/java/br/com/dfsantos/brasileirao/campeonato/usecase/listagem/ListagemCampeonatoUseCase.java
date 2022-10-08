package br.com.dfsantos.brasileirao.campeonato.usecase.listagem;

import br.com.dfsantos.brasileirao.campeonato.domain.Campeonato;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListagemCampeonatoUseCase {

  private final RepositorioCampeonato repositorioCampeonato;

  public ListagemCampeonatoUseCase(final RepositorioCampeonato repositorioCampeonato) {
    this.repositorioCampeonato = repositorioCampeonato;
  }

  public ListagemCampeonatoUseCaseOutput executar() {
    final List<Campeonato> campeonatos = repositorioCampeonato.listarTodosCampeonatos();
    return new ListagemCampeonatoUseCaseOutput(campeonatos);
  }

}
