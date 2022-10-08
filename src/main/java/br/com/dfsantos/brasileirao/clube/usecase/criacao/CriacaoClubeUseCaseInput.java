package br.com.dfsantos.brasileirao.clube.usecase.criacao;

public record CriacaoClubeUseCaseInput(
  String nomeCurto,
  String cidade,
  String uf
) {
}
