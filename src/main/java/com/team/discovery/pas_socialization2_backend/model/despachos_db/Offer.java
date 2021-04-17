package com.team.discovery.pas_socialization2_backend.model.despachos_db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oferta", schema = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idDespacho", referencedColumnName = "id")
    private Shipping shipping;

    @OneToOne
    @JoinColumn(name = "idUsuarioTransporte", referencedColumnName = "id")
    private User userTransport;

    @Column(name = "oferta", nullable = false)
    private Integer value;
}
