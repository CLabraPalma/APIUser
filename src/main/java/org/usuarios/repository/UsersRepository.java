package org.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.usuarios.dto.UserDTO;
import org.usuarios.model.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findAll();

    UserDTO findByEmail(String email);

    boolean existsByEmail(String email);
}