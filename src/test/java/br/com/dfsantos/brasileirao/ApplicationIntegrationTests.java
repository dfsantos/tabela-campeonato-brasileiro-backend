package br.com.dfsantos.brasileirao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Aplicação: smoke test")
class ApplicationIntegrationTests {

  @Test
  @DisplayName("contexto Spring carrega com sucesso")
  void contextLoads() {
  }

}
