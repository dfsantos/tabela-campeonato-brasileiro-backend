package br.com.dfsantos.brasileirao.clube;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Clube")
class ClubeUnitTest {

  public static final String NOME_CURTO = "Grêmio";
  public static final String CIDADE = "Porto Alegre";
  public static final String UF = "RS";

  @Nested
  @DisplayName("quando for criado")
  class QuandoCriado {

    @Test
    @DisplayName("não gera erro com dados válidos")
    void nao_gera_erro_com_dados_validos() {
      assertDoesNotThrow(() -> new Clube(NOME_CURTO, CIDADE, UF));
    }

    @Test
    @DisplayName("gera erro com nome nulo")
    void gera_erro_com_nome_nulo() {
      assertThrows(NomeClubeInvalidoException.class, () -> new Clube(null, CIDADE, UF));
    }

    @Test
    @DisplayName("gera erro com nome em branco")
    void gera_erro_com_nome_branco() {
      assertThrows(NomeClubeInvalidoException.class, () -> new Clube("", CIDADE, UF));
    }

    @Test
    @DisplayName("gera erro com cidade nulo")
    void gera_erro_com_cidade_nulo() {
      assertThrows(CidadeInvalidaException.class, () -> new Clube(NOME_CURTO, null, UF));
    }

    @Test
    @DisplayName("gera erro com cidade em branco")
    void gera_erro_com_cidade_branco() {
      assertThrows(CidadeInvalidaException.class, () -> new Clube(NOME_CURTO, "", UF));
    }

    @Test
    @DisplayName("gera erro com uf nulo")
    void gera_erro_com_uf_nulo() {
      assertThrows(UfInvalidaException.class, () -> new Clube(NOME_CURTO, CIDADE, null));
    }

    @Test
    @DisplayName("gera erro com uf em branco")
    void gera_erro_com_uf_branco() {
      assertThrows(UfInvalidaException.class, () -> new Clube(NOME_CURTO, CIDADE, ""));
    }

  }

}
