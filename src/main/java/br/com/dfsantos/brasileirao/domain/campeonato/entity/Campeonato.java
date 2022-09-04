package br.com.dfsantos.brasileirao.domain.campeonato.entity;

import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.AnoCampeonatoInvalidoException;
import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.NumeroParticipantesInvalidoException;
import br.com.dfsantos.brasileirao.domain.campeonato.entity.exception.PeriodoCampeonatoInvalidoException;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Campeonato {

  private final Integer ano;
  private final Integer numeroParticipantes;
  private final LocalDate dataInicio;
  private final LocalDate dataTermino;

  public Campeonato(final Integer ano, final Integer numeroParticipantes,
                    final LocalDate dataInicio, final LocalDate dataTermino) throws Exception {
    this.ano = ano;
    this.numeroParticipantes = numeroParticipantes;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;

    isValido();
  }

  public Integer getAno() {
    return ano;
  }

  public Integer getNumeroParticipantes() {
    return numeroParticipantes;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  private void isValido() throws Exception {
    if (isNull(ano) || ano.equals(0)) {
      throw new AnoCampeonatoInvalidoException();
    }

    if (isNull(numeroParticipantes) || numeroParticipantes.equals(0)) {
      throw new NumeroParticipantesInvalidoException();
    }

    if (dataTermino.isBefore(dataInicio)) {
      throw new PeriodoCampeonatoInvalidoException();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Campeonato that = (Campeonato) o;

    if (!Objects.equals(ano, that.ano)) return false;
    if (!Objects.equals(numeroParticipantes, that.numeroParticipantes))
      return false;
    if (!Objects.equals(dataInicio, that.dataInicio)) return false;
    return Objects.equals(dataTermino, that.dataTermino);
  }

  @Override
  public int hashCode() {
    int result = ano != null ? ano.hashCode() : 0;
    result = 31 * result + (numeroParticipantes != null ? numeroParticipantes.hashCode() : 0);
    result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
    result = 31 * result + (dataTermino != null ? dataTermino.hashCode() : 0);
    return result;
  }

}
