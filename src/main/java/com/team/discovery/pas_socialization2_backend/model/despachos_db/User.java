package com.team.discovery.pas_socialization2_backend.model.despachos_db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario", schema = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombres")
    private String name;

    @Column(name = "apellidos")
    private String lastName;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "nombreUsuario")
    private String userName;

    @Column(name = "contraseña")
    private String password;
}
