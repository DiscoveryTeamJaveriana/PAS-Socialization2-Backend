package com.team.discovery.pas_socialization2_backend.service.impl;

import com.team.discovery.pas_socialization2_backend.controller.model.Usuario;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.Rol;
import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import com.team.discovery.pas_socialization2_backend.repository.UserRepository;
import com.team.discovery.pas_socialization2_backend.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements IUserService {

private UserRepository userRepository;

public UserService (UserRepository userRepository) {
    this.userRepository = userRepository;
}

    @Override
    public void  createUser(Usuario requestUsuario) {

        User user = User.builder().id(requestUsuario.getId().longValue()).name(requestUsuario.getNombres())
                .lastName(requestUsuario.getApellidos()).address(requestUsuario.getDireccion()).phone(requestUsuario.getTelefono())
                .rol(Rol.CLIENT).userName(requestUsuario.getNombreUsuario()).password(requestUsuario.getContrasea()).email(requestUsuario.getCorreo()).build();
        userRepository.save(user);
        log.info("Successful user creation");
    }

    @Override
    public Usuario searchUser(String nombreUsuario) {

    User user = userRepository.findUsersByUserName(nombreUsuario);
    Usuario usuarioFinal = new Usuario();

    if(user != null){
        usuarioFinal.setApellidos(user.getLastName());
        usuarioFinal.setCorreo(user.getEmail());
        usuarioFinal.setContrasea(user.getPassword());
        usuarioFinal.setNombreUsuario(user.getUserName());
        usuarioFinal.setDireccion(user.getAddress());
        usuarioFinal.setId(user.getId().intValue());
        usuarioFinal.setNombres(user.getName());
        usuarioFinal.setTelefono(user.getPhone());
    }else{
        log.info("Usuario no Existe");
    }

    return usuarioFinal;
    }
}
