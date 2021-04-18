package com.team.discovery.pas_socialization2_backend.model.despachos_db;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "oferta", schema = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_despacho", referencedColumnName = "id")
    private Shipping shipping;

    @OneToOne
    @JoinColumn(name = "id_usuario_transporte", referencedColumnName = "id")
    private User userTransport;

    @Column(name = "oferta")
    private Integer value;
}
