package br.com.dfsantos.brasileirao;

import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.RepositorioCampeonato;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RepositorioCampeonato repositorioCampeonato() {
    return ano -> Optional.empty();
  }

}
