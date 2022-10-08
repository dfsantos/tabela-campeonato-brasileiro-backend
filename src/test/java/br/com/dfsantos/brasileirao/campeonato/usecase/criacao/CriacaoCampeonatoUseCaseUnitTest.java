package br.com.dfsantos.brasileirao.campeonato.usecase.criacao;

import br.com.dfsantos.brasileirao.campeonato.domain.Campeonato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.dfsantos.brasileirao.campeonato.domain.CampeonatoUnitTest.*;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Caso de uso: criar campeonato")
public class CriacaoCampeonatoUseCaseUnitTest {

  private CriacaoCampeonatoUseCase casoDeUso;

  private RepositorioCampeonato repositorioCampeonato = mock(RepositorioCampeonato.class);

  @BeforeEach
  void setUp() {
    casoDeUso = new CriacaoCampeonatoUseCase(repositorioCampeonato);
  }

  @Nested
  @DisplayName("executa com sucesso quando")
  class ExecutaComSucesso {

    @Test
    @DisplayName("nenhum erro é gerado")
    void nenhum_erro_gerado() throws NovoCampeonatoException {
      // expect
      assertDoesNotThrow(() -> casoDeUso.executar(inputValido()));
    }

    @Test
    @DisplayName("ano do campeonato criado é retornado")
    void ano_do_campeonato_retornado() throws NovoCampeonatoException {
      // given
      var input = inputValido();

      // when
      var output = casoDeUso.executar(input);

      // then
      assertEquals(input.ano(), output.ano());
    }

    @Test
    @DisplayName("o campeonato é armazenado")
    void campeonato_armazenado() throws NovoCampeonatoException {
      // given
      var input = inputValido();

      // when
      var output = casoDeUso.executar(input);

      // then
      verify(repositorioCampeonato).armazenar(any(Campeonato.class));
    }

  }

  @Nested
  @DisplayName("não executa com sucesso por erro de validação quando")
  class NaoExecutaComSucesso {

    @Test
    @DisplayName("dados são inválidos")
    void dados_sao_invalidos() {
      // expect
      assertThrows(NovoCampeonatoException.class, () -> casoDeUso.executar(inputInvalido()));
    }

    @Test
    @DisplayName("campeonato já existe")
    void campeonato_ja_existe() throws NovoCampeonatoException {
      // given
      given(repositorioCampeonato.localizarPorAno(_2003)).willReturn(of(campeonato()));

      // expect
      assertThrows(CampeonatoJaExisteException.class, () -> casoDeUso.executar(inputValido()));
    }

  }

  public static CriacaoCampeonatoUseCaseInput inputValido() {
    return new CriacaoCampeonatoUseCaseInput(_2003, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);
  }

  public static CriacaoCampeonatoUseCaseInput inputInvalido() {
    return new CriacaoCampeonatoUseCaseInput(_2003, 0, _29_03_2003, _14_12_2003);
  }

  public static CriacaoCampeonatoUseCaseOutput output() {
    return new CriacaoCampeonatoUseCaseOutput(_2003);
  }

}
