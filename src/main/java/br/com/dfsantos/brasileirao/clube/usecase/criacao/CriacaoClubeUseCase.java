package br.com.dfsantos.brasileirao.clube.usecase.criacao;

import br.com.dfsantos.brasileirao.clube.domain.Clube;
import br.com.dfsantos.brasileirao.clube.usecase.RepositorioClube;

import java.util.UUID;

public class CriacaoClubeUseCase {

  private final RepositorioClube repositorioClube;

  public CriacaoClubeUseCase(RepositorioClube repositorioClube) {
    this.repositorioClube = repositorioClube;
  }

  public CriacaoClubeUseCaseOutput executar(final CriacaoClubeUseCaseInput input) throws NovoClubeException {
    Clube novoClube = from(input);
    UUID idNovoClube = repositorioClube.armazenar(novoClube);
    return new CriacaoClubeUseCaseOutput(idNovoClube);
  }

  private Clube from(CriacaoClubeUseCaseInput input) throws NovoClubeException {
    try {
      return new Clube(
        input.nomeCurto(),
        input.cidade(),
        input.uf()
      );
    } catch (Exception e) {
      throw new NovoClubeException();
    }
  }

}
