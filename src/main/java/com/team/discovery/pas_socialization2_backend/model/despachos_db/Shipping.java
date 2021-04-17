package com.team.discovery.pas_socialization2_backend.model.despachos_db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "despacho", schema = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cantidadCajas", nullable = false)
    private Integer boxesAmount;

    @Column(name = "pesoTotal", nullable = false)
    private Integer totalWeight;

    @Column(name = "idEstado", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "idUsuarioDestino", referencedColumnName = "id")
    private User user;
}
