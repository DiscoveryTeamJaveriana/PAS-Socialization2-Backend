package com.team.discovery.pas_socialization2_backend.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class Usuario   {
  @JsonProperty("apellidos")
  private String apellidos = null;

  @JsonProperty("correo")
  private String correo = null;

  @JsonProperty("direccion")
  private String direccion = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("idRol")
  private Integer idRol = null;

  @JsonProperty("nombreUsuario")
  private String nombreUsuario = null;

  @JsonProperty("nombres")
  private String nombres = null;

  @JsonProperty("telefono")
  private String telefono = null;

  @JsonProperty("contraseña")
  private String contrasea = null;

}

