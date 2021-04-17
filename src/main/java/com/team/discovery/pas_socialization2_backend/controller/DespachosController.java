package com.team.discovery.pas_socialization2_backend.controller;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Cotizar;
import com.team.discovery.pas_socialization2_backend.controller.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/javeriana")
public class DespachosController {

    @PostMapping("/Usuario")
    public ResponseEntity<String> createUser(@RequestBody final Usuario requestUsuario) {
        log.info("Creating User for Id {}", requestUsuario.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/Usuario/{nombreUsuario}")
    public ResponseEntity<String> searchUser(@PathVariable String nombreUsuario) {
        log.info("Search User for Username {}", nombreUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/DespachoProveedor")
    public ResponseEntity<String> searchSupplierDispatch() {
        log.info("Search available dispatch");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/DespachoProveedor/{id}")
    public ResponseEntity<String> quote(@PathVariable int id, @RequestBody Cotizar requestCotizar) {
        log.info("Quote for Id dispatch {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/DespachoCliente/{idUsuarioDestino}")
    public ResponseEntity<String> searchDispatchClient(@PathVariable int idUsuarioDestino) {
        log.info("Search available dispatch for User ID {}", idUsuarioDestino);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/DespachoCliente")
    public ResponseEntity<String> approveDispatch(@RequestBody final Aprobar requestAprobar) {
        log.info("Approve for Id dispatch {}", requestAprobar.getIdDespacho());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/DespachoClienteHistorico/{id}")
    public ResponseEntity<String> searchHistoricalDispatch(@PathVariable int id) {
        log.info("Search historical dispatch for User ID {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
