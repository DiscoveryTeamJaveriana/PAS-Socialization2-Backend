package com.team.discovery.pas_socialization2_backend.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class Cotizar   {
  @JsonProperty("idUsuarioTransporte")
  private Integer idUsuarioTransporte = null;

  @JsonProperty("oferta")
  private Integer oferta = null;

}

