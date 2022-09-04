package br.com.dfsantos.brasileirao.usecase.campeonato.criacao;

import br.com.dfsantos.brasileirao.domain.campeonato.entity.Campeonato;
import org.springframework.stereotype.Component;

@Component
public class CriacaoCampeonatoUseCase {

  private final RepositorioCampeonato repositorioCampeonato;

  public CriacaoCampeonatoUseCase(final RepositorioCampeonato repositorioCampeonato) {
    this.repositorioCampeonato = repositorioCampeonato;
  }

  public CriacaoCampeonatoUseCaseOutput executar(final CriacaoCampeonatoUseCaseInput input) throws NovoCampeonatoException {
    Campeonato campeonato = from(input);
    validarPreExecucao(input);
    return criar(campeonato);
  }

  private Campeonato from(CriacaoCampeonatoUseCaseInput input) throws NovoCampeonatoException {
    try {
      return new Campeonato(
          input.ano(),
          input.numeroParticipantes(),
          input.dataInicio(),
          input.dataTermino()
      );
    } catch (Exception e) {
      throw new NovoCampeonatoException();
    }
  }

  private void validarPreExecucao(CriacaoCampeonatoUseCaseInput input) throws CampeonatoJaExisteException {
    if (repositorioCampeonato.localizarPorAno(input.ano()).isPresent()) {
      throw new CampeonatoJaExisteException();
    }
  }

  private CriacaoCampeonatoUseCaseOutput criar(Campeonato campeonato) {
    return new CriacaoCampeonatoUseCaseOutput(
        campeonato.getAno()
    );
  }

}
