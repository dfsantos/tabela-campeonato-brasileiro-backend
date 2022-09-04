package br.com.dfsantos.brasileirao.infrastructure.rest.campeonato;

import br.com.dfsantos.brasileirao.usecase.campeonato.criacao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static br.com.dfsantos.brasileirao.infrastructure.rest.campeonato.CampeonatoController.PATH;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(PATH)
public class CampeonatoController {

  public static final String PATH = "/v1/campeonatos";
  private final CriacaoCampeonatoUseCase criacaoCampeonato;

  public CampeonatoController(final CriacaoCampeonatoUseCase criacaoCampeonato) {
    this.criacaoCampeonato = criacaoCampeonato;
  }

  @PostMapping(produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
  public ResponseEntity criarCampeonato(@RequestBody NovoCampeonatoRequestBody requestBody) {
    try {
      CriacaoCampeonatoUseCaseOutput output = criacaoCampeonato.executar(new CriacaoCampeonatoUseCaseInput(
          requestBody.ano(),
          requestBody.numeroParticipantes(),
          requestBody.dataInicio(),
          requestBody.dataTermino()
      ));
      return created(URI.create(PATH + "/" + output.ano())).build();
    } catch (CampeonatoJaExisteException e) {
      throw new ResponseStatusException(CONFLICT);
    } catch (NovoCampeonatoException e) {
      throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
    }
  }

}
