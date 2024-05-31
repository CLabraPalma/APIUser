package org.usuarios.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;



}
