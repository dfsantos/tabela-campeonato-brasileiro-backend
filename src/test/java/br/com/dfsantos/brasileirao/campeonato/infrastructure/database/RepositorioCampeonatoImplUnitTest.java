package br.com.dfsantos.brasileirao.campeonato.infrastructure.database;

import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.dfsantos.brasileirao.campeonato.domain.entity.CampeonatoUnitTest._2003;
import static br.com.dfsantos.brasileirao.campeonato.domain.entity.CampeonatoUnitTest.campeonato;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositório de campeonatos")
class RepositorioCampeonatoImplUnitTest {

  private RepositorioCampeonato repositorio;

  @BeforeEach
  void setUp() {
    repositorio = new RepositorioCampeonatoImpl();
  }

  @Test
  @DisplayName("retorna resultado vazio quando campeonato não é localizado por ano")
  void resultado_vazio_quando_nao_encontra_campeonato_por_ano() {
    // expect
    assertThat(repositorio.localizarPorAno(_2003)).isEmpty();
  }

  @Test
  @DisplayName("armazena campeonato para o ano informado")
  void armazena_campeonato_para_ano_informado() {
    // given
    repositorio.armazenar(campeonato());

    // expect
    assertThat(repositorio.localizarPorAno(_2003)).isPresent();
  }

}
