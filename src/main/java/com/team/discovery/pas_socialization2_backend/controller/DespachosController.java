package com.team.discovery.pas_socialization2_backend.controller;

import com.team.discovery.pas_socialization2_backend.controller.model.Aprobar;
import com.team.discovery.pas_socialization2_backend.controller.model.Cotizar;
import com.team.discovery.pas_socialization2_backend.controller.model.Usuario;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.State;
import com.team.discovery.pas_socialization2_backend.service.IDispatchService;
import com.team.discovery.pas_socialization2_backend.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/javeriana")
public class DespachosController {

    private IUserService userService;
    private IDispatchService dispatchService;

    @Autowired
    public DespachosController(IUserService userService, IDispatchService dispatchService) {
        this.userService = userService;
        this.dispatchService = dispatchService;
    }


    @PostMapping("/Usuario")
    public ResponseEntity<String> createUser(@RequestBody final Usuario requestUsuario) {
        log.info("Creating User for Id {}", requestUsuario.getId());
        userService.createUser(requestUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/Usuario/{nombreUsuario}")
    public ResponseEntity<Usuario> searchUser(@PathVariable String nombreUsuario) {
        log.info("Search User for Username {}", nombreUsuario);
        return new ResponseEntity<>(userService.searchUser(nombreUsuario),HttpStatus.OK);
    }

    @GetMapping("/DespachoProveedor")
    public ResponseEntity<List> searchSupplierDispatch() {
        log.info("Search available dispatch");
        return new ResponseEntity<>(dispatchService.searchDispatches(State.DESPACHO_OFERTADO),HttpStatus.OK);
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
