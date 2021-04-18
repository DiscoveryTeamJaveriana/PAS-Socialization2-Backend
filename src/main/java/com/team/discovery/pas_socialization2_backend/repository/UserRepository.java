package com.team.discovery.pas_socialization2_backend.repository;

import com.team.discovery.pas_socialization2_backend.model.despachos_db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByUserName(String userName);
}
