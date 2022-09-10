package br.com.dfsantos.brasileirao.infrastructure.rest.campeonato;

import br.com.dfsantos.brasileirao.usecase.campeonato.criacao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @RequestMapping
  @PostMapping(produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
  public ResponseEntity criarCampeonato(@RequestBody NovoCampeonatoRequestBody requestBody) throws NovoCampeonatoException {
    CriacaoCampeonatoUseCaseOutput output = criacaoCampeonato.executar(new CriacaoCampeonatoUseCaseInput(
      requestBody.ano(),
      requestBody.numeroParticipantes(),
      requestBody.dataInicio(),
      requestBody.dataTermino()
    ));
    return created(URI.create(PATH + "/" + output.ano())).build();
  }

  @ResponseStatus(code = CONFLICT, reason = "Campeonato já existe.")
  @ExceptionHandler(CampeonatoJaExisteException.class)
  public void campeonatoJaExisteExceptionHandler() {}

  @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Não foi possível atender a requisição.")
  @ExceptionHandler(NovoCampeonatoException.class)
  public void novoCampeonatoExceptionHandler() {}

}
