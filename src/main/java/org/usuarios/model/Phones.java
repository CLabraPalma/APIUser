package org.usuarios.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "phones")
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria
    private String number;
    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
