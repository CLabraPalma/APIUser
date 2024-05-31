package org.usuarios.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.usuarios.dto.ErrorResponse;
import org.usuarios.dto.PhoneDTO;
import org.usuarios.dto.UserDTO;
import org.usuarios.model.Phones;
import org.usuarios.model.Users;
import org.usuarios.repository.UsersRepository;


@RestController
public class UserController {

    @Autowired
    UsersRepository usersRepository;


    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody UserDTO user) {
        // Validar campos requeridos
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getPhones() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Validar formato de correo
        if (!user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return ResponseEntity.badRequest().body(null);
        }

        Users _user = new Users();

        // Validar formato de contraseña (añade tu lógica aquí)

        // Verificar si el correo ya está registrado
        if (usersRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("El email ya está en uso"));

        }
        user.setId(UUID.randomUUID().toString());
        _user.setName(user.getName());
        _user.setEmail(user.getEmail());
        _user.setPassword(user.getPassword());

        // Generar token (puedes usar UUID.randomUUID() para generar un UUID)
        String token = UUID.randomUUID().toString();
        _user.setToken(token);

        List<Phones> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : user.getPhones()) {
            Phones phone = new Phones();
            phone.setNumber(phoneDTO.getNumber());
            phone.setCityCode(phoneDTO.getCitycode());
            phone.setCountryCode(phoneDTO.getCountrycode());
            phone.setUser(_user); // Asigna el usuario al teléfono

            phones.add(phone);
        }
        // Asignar la lista completa de teléfonos al usuario
        _user.setPhones(phones);
        // Establecer fechas y activar usuario
        LocalDateTime now = LocalDateTime.now();
        _user.setCreated(now);
        _user.setModified(now);
        _user.setLastLogin(now);
        _user.setActive(true);

        usersRepository.save(_user);


        // Devolver respuesta exitosa
        return ResponseEntity.ok(user);
    }
}
