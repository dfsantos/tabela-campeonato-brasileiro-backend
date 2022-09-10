package br.com.dfsantos.brasileirao.campeonato.usecase.busca;

import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.springframework.stereotype.Component;

@Component
public class BuscaCampeonatoUseCase {

  private final RepositorioCampeonato repositorioCampeonato;

  public BuscaCampeonatoUseCase(final RepositorioCampeonato repositorioCampeonato) {
    this.repositorioCampeonato = repositorioCampeonato;
  }

  public BuscaCampeonatoUseCaseOutput executar(BuscaCampeonatoUseCaseInput input) throws CampeonatoNaoEncontradoException {
    return repositorioCampeonato.localizarPorAno(input.ano())
      .map(campeonato -> new BuscaCampeonatoUseCaseOutput(
        campeonato.getAno(),
        campeonato.getNumeroParticipantes(),
        campeonato.getDataInicio(),
        campeonato.getDataTermino()
      )).orElseThrow(() -> new CampeonatoNaoEncontradoException());
  }

}
