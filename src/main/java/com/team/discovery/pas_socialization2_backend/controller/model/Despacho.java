package com.team.discovery.pas_socialization2_backend.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class Despacho   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("cantidadCajas")
  private Integer cantidadCajas = null;

  @JsonProperty("pesoTotal")
  private Integer pesoTotal = null;

  @JsonProperty("idEstado")
  private Integer idEstado = null;

  @JsonProperty("idUsuarioDestino")
  private Integer idUsuarioDestino = null;

  @JsonProperty("mejorOferta")
  private Integer mejorOferta = null;

  @JsonProperty("transportadora")
  private String transportadora = null;

}

