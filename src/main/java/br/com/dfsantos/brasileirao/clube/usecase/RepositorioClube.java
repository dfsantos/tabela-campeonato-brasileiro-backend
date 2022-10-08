package br.com.dfsantos.brasileirao.clube.usecase;

import br.com.dfsantos.brasileirao.clube.domain.Clube;

import java.util.UUID;

public interface RepositorioClube {

  UUID armazenar(Clube clube);

}
