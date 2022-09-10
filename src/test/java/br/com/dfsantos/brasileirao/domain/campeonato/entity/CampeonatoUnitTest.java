package br.com.dfsantos.brasileirao.domain.campeonato.entity;

import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.AnoCampeonatoInvalidoException;
import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.NumeroParticipantesInvalidoException;
import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.PeriodoCampeonatoInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Campeonato")
public class CampeonatoUnitTest {

  public static final int ANO = 2003;
  public static final int NUMERO_PARTICIPANTES = 24;
  public static final LocalDate _29_03_2003 = of(ANO, 3, 29);
  public static final LocalDate _14_12_2003 = of(ANO, 12, 14);

  @Nested
  @DisplayName("quando for criado")
  class QuandoForCriado {

    @Test
    @DisplayName("não gera erro para dados válidos")
    void tem_os_dados_de_cadastro_completos() throws Exception {
      var campeonato = new Campeonato(ANO, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);

      assertEquals(campeonato(), campeonato);
    }

    @Test
    @DisplayName("gera erro para ano nulo")
    void campeonato_invalido_quando_ano_for_nulo() {
      assertThrows(AnoCampeonatoInvalidoException.class,
        () -> new Campeonato(null, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003)
      );
    }

    @Test
    @DisplayName("gera erro para ano zero")
    void campeonato_invalido_quando_ano_for_zero() {
      assertThrows(AnoCampeonatoInvalidoException.class,
        () -> new Campeonato(0, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003)
      );
    }

    @Test
    @DisplayName("gera erro para número de participantes nulo")
    void campeonato_invalido_quando_numeroParticipantes_for_nulo() {
      assertThrows(NumeroParticipantesInvalidoException.class,
        () -> new Campeonato(ANO, null, _29_03_2003, _14_12_2003)
      );
    }

    @Test
    @DisplayName("gera erro para número de participantes zero")
    void campeonato_invalido_quando_numeroParticipantes_for_zero() {
      assertThrows(NumeroParticipantesInvalidoException.class,
        () -> new Campeonato(ANO, 0, _29_03_2003, _14_12_2003)
      );
    }

    @Test
    @DisplayName("gera erro para data de término anterior à data de início")
    void campeonato_invalido_quando_dataTermino_anterio_a_dataInicio() {
      assertThrows(PeriodoCampeonatoInvalidoException.class,
        () -> new Campeonato(ANO, NUMERO_PARTICIPANTES, _14_12_2003, _29_03_2003)
      );
    }

  }

  public static Campeonato campeonato() {
    try {
      return new Campeonato(ANO, NUMERO_PARTICIPANTES, _29_03_2003, _14_12_2003);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
