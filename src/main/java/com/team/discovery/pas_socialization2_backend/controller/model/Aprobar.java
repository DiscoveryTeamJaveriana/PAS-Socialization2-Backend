package com.team.discovery.pas_socialization2_backend.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class Aprobar   {
  @JsonProperty("idDespacho")
  private Integer idDespacho = null;

  @JsonProperty("aprobado")
  private Boolean aprobado = null;

}