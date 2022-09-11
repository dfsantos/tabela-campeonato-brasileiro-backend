package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCase;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCaseInput;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.BuscaCampeonatoUseCaseOutput;
import br.com.dfsantos.brasileirao.campeonato.usecase.busca.CampeonatoNaoEncontradoException;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.*;
import br.com.dfsantos.brasileirao.campeonato.usecase.listagem.ListagemCampeonatoUseCase;
import br.com.dfsantos.brasileirao.campeonato.usecase.listagem.ListagemCampeonatoUseCaseOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;

import static br.com.dfsantos.brasileirao.campeonato.infrastructure.rest.CampeonatoController.PATH;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(PATH)
public class CampeonatoController {

  public static final String PATH = "/v1/campeonatos";
  private final CriacaoCampeonatoUseCase criacaoCampeonato;
  private final BuscaCampeonatoUseCase buscaCampeonatoUseCase;
  private final ListagemCampeonatoUseCase listagemCampeonatoUseCase;

  public CampeonatoController(final CriacaoCampeonatoUseCase criacaoCampeonato,
                              final BuscaCampeonatoUseCase buscaCampeonatoUseCase,
                              final ListagemCampeonatoUseCase listagemCampeonatoUseCase) {
    this.criacaoCampeonato = criacaoCampeonato;
    this.buscaCampeonatoUseCase = buscaCampeonatoUseCase;
    this.listagemCampeonatoUseCase = listagemCampeonatoUseCase;
  }

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

  @GetMapping(path = "/{ano}", produces = {APPLICATION_JSON_VALUE})
  public BuscaCampeonatoResponseBody buscarCampeonato(@PathVariable("ano") Integer ano) throws CampeonatoNaoEncontradoException {
    BuscaCampeonatoUseCaseOutput output = buscaCampeonatoUseCase.executar(new BuscaCampeonatoUseCaseInput(ano));
    return new BuscaCampeonatoResponseBody(output);
  }

  @GetMapping(produces = {APPLICATION_JSON_VALUE})
  public ListagemCampeonatoResponseBody listarCampeonatos() {
    ListagemCampeonatoUseCaseOutput output = listagemCampeonatoUseCase.executar();
    return new ListagemCampeonatoResponseBody(output.campeonatos());
  }

  @ResponseStatus(code = CONFLICT, reason = CampeonatoJaExisteException.MESSAGE)
  @ExceptionHandler(CampeonatoJaExisteException.class)
  public void campeonatoJaExisteExceptionHandler() {
  }

  @ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = NovoCampeonatoException.MESSAGE)
  @ExceptionHandler(NovoCampeonatoException.class)
  public void novoCampeonatoExceptionHandler() {
  }

  @ResponseStatus(code = NOT_FOUND, reason = CampeonatoNaoEncontradoException.MESSAGE)
  @ExceptionHandler(CampeonatoNaoEncontradoException.class)
  public void campeonatoNaoEncontradoExceptionHandler() {
  }

}
