package br.com.dfsantos.brasileirao.campeonato.usecase.listagem;

import br.com.dfsantos.brasileirao.campeonato.domain.Campeonato;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.dfsantos.brasileirao.campeonato.domain.CampeonatoUnitTest.campeonato;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

@DisplayName("Caso de uso: listar campeonatos")
public class ListagemCampeonatoUseCaseTest {

  private ListagemCampeonatoUseCase useCase;

  private RepositorioCampeonato repositorioCampeonato = mock(RepositorioCampeonato.class);

  @BeforeEach
  void setUp() {
    useCase = new ListagemCampeonatoUseCase(repositorioCampeonato);
  }

  @Nested
  @DisplayName("executa com sucesso quando")
  class ExecutaComSucesso {

    @Test
    @DisplayName("não encontra campeonatos e retorna lista vazia")
    void nao_encontra_campeonatos_armazenados_e_retorna_lista_vazia() {
      // given
      given(repositorioCampeonato.listarTodosCampeonatos())
        .willReturn(emptyList());

      // when
      var retorno = useCase.executar();

      // then
      assertThat(retorno.campeonatos()).isEmpty();
    }

    @Test
    @DisplayName("encontra campeonatos e retorna lista com dados")
    void encontra_campeonatos_armazenados_e_retorna_lista_com_dados() {
      // given
      List<Campeonato> campeonatos = newArrayList(campeonato());
      given(repositorioCampeonato.listarTodosCampeonatos())
        .willReturn(campeonatos);

      // when
      var retorno = useCase.executar();

      // then
      assertThat(retorno.campeonatos()).containsAll(campeonatos);
    }
  }

  public static ListagemCampeonatoUseCaseOutput output() {
    return new ListagemCampeonatoUseCaseOutput(newArrayList(campeonato()));
  }

  public static ListagemCampeonatoUseCaseOutput outputListaVazia() {
    return new ListagemCampeonatoUseCaseOutput(emptyList());
  }

}
