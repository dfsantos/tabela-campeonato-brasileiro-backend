package br.com.dfsantos.brasileirao.campeonato.infrastructure.rest;

import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CampeonatoJaExisteException;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCase;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCaseInput;
import br.com.dfsantos.brasileirao.campeonato.usecase.criacao.NovoCampeonatoException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.dfsantos.brasileirao.campeonato.infrastructure.rest.CampeonatoControllerUnitTest.novoCampeonatoRequestBody;
import static br.com.dfsantos.brasileirao.campeonato.usecase.criacao.CriacaoCampeonatoUseCaseUnitTest.output;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tags({@Tag("slice"), @Tag("rest"), @Tag("contract")})
@WebMvcTest(CampeonatoController.class)
@DisplayName("Controller de campeonatos: integration test")
public class CampeonatoControllerIntegrationTest {

  private static final String ENDPOINT = "/v1/campeonatos";

  public static final String PATH_CAMPEONATO_CRIADO = "/v1/campeonatos/2003";

  @MockBean
  private CriacaoCampeonatoUseCase criacaoCampeonatoUseCase;

  @Nested
  @DisplayName("Endpoint para criar campeonato")
  class EndpointCriarCampeonatos {

    private static final String REQUEST_BODY = """
          {
            "ano": 2003,
            "numeroParticipantes": 24,
            "dataInicio": "2003-03-29",
            "dataTermino": "2003-12-14"
          }
      """;

    @Nested
    @DisplayName("atende ao contrato que")
    class AtendeContrato {

      @BeforeEach
      void setUp() throws NovoCampeonatoException {
        given(criacaoCampeonatoUseCase.executar(any(CriacaoCampeonatoUseCaseInput.class)))
          .willReturn(output());
      }

      @Test
      @DisplayName("responde no path /v1/campeonatos")
      void path_correto(@Autowired MockMvc mockMvc) throws Exception {
        gerarRequisicaoValida(mockMvc)
          .andExpect(status().is(not(NOT_FOUND.value())));
      }

      @Test
      @DisplayName("responde no método POST")
      void metodo_correto(@Autowired MockMvc mockMvc) throws Exception {
        gerarRequisicaoValida(mockMvc)
          .andExpect(status().is(not(METHOD_NOT_ALLOWED.value())));
      }

      @Nested
      @DisplayName("quando processa com sucesso")
      class QuandoProcessaComSucesso {

        @Test
        @DisplayName("aceita Content-Type application/json")
        void aceita_content_type_application_json(@Autowired MockMvc mockMvc) throws Exception {
          gerarRequisicaoValida(mockMvc)
            .andExpect(status().is(not(UNSUPPORTED_MEDIA_TYPE.value())));
        }

        @Test
        @DisplayName("responde com Content-Type application/json")
        void retorna_content_type_application_json(@Autowired MockMvc mockMvc) throws Exception {
          gerarRequisicaoValida(mockMvc)
            .andExpect(status().is(not(NOT_ACCEPTABLE.value())));
        }

        @Test
        @DisplayName("responde com status HTTP 201")
        void retorna_status_http_201(@Autowired MockMvc mockMvc) throws Exception {
          gerarRequisicaoValida(mockMvc)
            .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("responde Location para recurso criado")
        void retorna_location_para_recurso_criado(@Autowired MockMvc mockMvc) throws Exception {
          gerarRequisicaoValida(mockMvc)
            .andExpect(header().string(LOCATION, PATH_CAMPEONATO_CRIADO));
        }

      }

      @Nested
      @DisplayName("quando rejeita por erro de requisição")
      class QuandoRejeitaPorErroRequisicao {

        private static final String REQUEST_BODY_INVALIDO = "";

        @Test
        @DisplayName("responde com status HTTP 400")
        void retorna_status_http_400(@Autowired MockMvc mockMvc) throws Exception {
          mockMvc.perform(
              post(ENDPOINT, novoCampeonatoRequestBody())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(REQUEST_BODY_INVALIDO)
            ).andDo(print())
            .andExpect(status().isBadRequest());
        }

      }

      @Nested
      @DisplayName("quando rejeita por erro de negócio")
      class QuandoRejeitaPorErroNegocio {

        @Test
        @DisplayName("responde com status HTTP 409 quando recurso já existe")
        void retorna_status_http_409_quando_recurso_ja_existe(@Autowired MockMvc mockMvc) throws Exception {
          given(criacaoCampeonatoUseCase.executar(any(CriacaoCampeonatoUseCaseInput.class)))
            .willThrow(CampeonatoJaExisteException.class);

          gerarRequisicaoValida(mockMvc)
            .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("responde mensagem de erro quando recurso já existe")
        void retorna_mensagem_de_erro_quando_recurso_ja_existe(@Autowired MockMvc mockMvc) throws Exception {
          given(criacaoCampeonatoUseCase.executar(any(CriacaoCampeonatoUseCaseInput.class)))
            .willThrow(CampeonatoJaExisteException.class);

          gerarRequisicaoValida(mockMvc)
            .andExpect(status().reason(CampeonatoJaExisteException.MESSAGE));
        }

        @Test
        @DisplayName("responde com status HTTP 422 quando não consegue processar a requisição")
        void retorna_status_http_422_quando_nao_consegue_processar(@Autowired MockMvc mockMvc) throws Exception {
          given(criacaoCampeonatoUseCase.executar(any(CriacaoCampeonatoUseCaseInput.class)))
            .willThrow(NovoCampeonatoException.class);

          gerarRequisicaoValida(mockMvc)
            .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @DisplayName("responde com mensagem de erro quando não consegue processar a requisição")
        void retorna_mensagem_de_erro_quando_nao_consegue_processar(@Autowired MockMvc mockMvc) throws Exception {
          given(criacaoCampeonatoUseCase.executar(any(CriacaoCampeonatoUseCaseInput.class)))
            .willThrow(NovoCampeonatoException.class);

          gerarRequisicaoValida(mockMvc)
            .andExpect(status().reason(NovoCampeonatoException.MESSAGE));
        }

      }

      private ResultActions gerarRequisicaoValida(MockMvc mockMvc) throws Exception {
        return mockMvc.perform(
          post(ENDPOINT)
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(REQUEST_BODY)
        ).andDo(print());
      }

    }

  }

}
