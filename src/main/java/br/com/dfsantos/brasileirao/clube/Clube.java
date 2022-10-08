package br.com.dfsantos.brasileirao.clube;

import static java.util.Objects.isNull;

public class Clube {

  private final String nomeCurto;
  private final String cidade;
  private final String uf;

  public Clube(final String nomeCurto, final String cidade, final String uf) throws NomeClubeInvalidoException, CidadeInvalidaException, UfInvalidaException {
    this.nomeCurto = nomeCurto;
    this.cidade = cidade;
    this.uf = uf;

    if (isNull(nomeCurto) || nomeCurto.isEmpty()) {
      throw new NomeClubeInvalidoException();
    }

    if (isNull(cidade) || cidade.isEmpty()) {
      throw new CidadeInvalidaException();
    }

    if (isNull(uf) || uf.isEmpty()) {
      throw new UfInvalidaException();
    }
  }

}
