package com.team.discovery.pas_socialization2_backend.model.despachos_db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad_cajas")
    private Long boxesAmount;

    @Column(name = "peso_total")
    private Double totalWeight;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "mejor_oferta")
    private Long bestOffer;

    @Column(name = "transportadora")
    private String dispatcher;

    @OneToOne
    @JoinColumn(name = "id_usuario_destino", referencedColumnName = "id")
    private User user;
}
