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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "despacho", schema = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipping {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cantidadCajas")
    private Long boxesAmount;

    @Column(name = "pesoTotal")
    private Integer totalWeight;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "idUsuarioDestino", referencedColumnName = "id")
    private User user;
}
