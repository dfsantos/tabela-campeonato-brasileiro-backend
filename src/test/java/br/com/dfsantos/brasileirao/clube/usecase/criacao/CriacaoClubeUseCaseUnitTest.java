package br.com.dfsantos.brasileirao.clube.usecase.criacao;

import br.com.dfsantos.brasileirao.clube.domain.Clube;
import br.com.dfsantos.brasileirao.clube.usecase.RepositorioClube;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Caso de uso: criar clube")
class CriacaoClubeUseCaseUnitTest {

  public static final UUID ID_CLUBE = randomUUID();
  public static final String NOME_CURTO = "Grêmio";
  public static final String CIDADE = "Porto Alegre";
  public static final String UF = "RS";

  private CriacaoClubeUseCase casoDeUso;

  private RepositorioClube repositorioClube = mock(RepositorioClube.class);

  @BeforeEach
  void setUp() {
    casoDeUso = new CriacaoClubeUseCase(repositorioClube);
  }

  @Nested
  @DisplayName("executa com sucesso quando")
  class ExecutaComSucesso {

    @Test
    @DisplayName("nenhum erro é gerado")
    void nenhum_erro_gerado() {
      // expect
      assertDoesNotThrow(() -> casoDeUso.executar(inputValido()));
    }

    @Test
    @DisplayName("ID do clube criado é retornado")
    void ano_do_clube_retornado() throws NovoClubeException {
      // given
      var input = inputValido();

      // and
      given(repositorioClube.armazenar(any(Clube.class))).willReturn(ID_CLUBE);

      // when
      var output = casoDeUso.executar(input);

      // then
      assertEquals(output(), output);
    }

    @Test
    @DisplayName("o clube é armazenado")
    void clube_armazenado() throws NovoClubeException {
      // when
      casoDeUso.executar(inputValido());

      // then
      verify(repositorioClube).armazenar(any(Clube.class));
    }

  }

  @Nested
  @DisplayName("não executa com sucesso por erro de validação quando")
  class NaoExecutaComSucesso {

    @Test
    @DisplayName("dados são inválidos")
    void dados_sao_invalidos() {
      // expect
      assertThrows(NovoClubeException.class, () -> casoDeUso.executar(inputInvalido()));
    }
  }

  public static CriacaoClubeUseCaseInput inputValido() {
    return new CriacaoClubeUseCaseInput(NOME_CURTO, CIDADE, UF);
  }

  public static CriacaoClubeUseCaseInput inputInvalido() {
    return new CriacaoClubeUseCaseInput(null, null, null);
  }

  public static CriacaoClubeUseCaseOutput output() {
    return new CriacaoClubeUseCaseOutput(ID_CLUBE);
  }

}
