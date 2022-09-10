package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("smoke")
@SpringBootTest
@DisplayName("Controller de campeonatos: smoke test")
public class CampeonatoControllerSmokeTest {

  @Autowired
  private CampeonatoController controller;

  @Test
  @DisplayName("Bean CampeonatoController inicializa")
  void inicializacao_do_bean() {
    assertNotNull(controller);
  }

}
