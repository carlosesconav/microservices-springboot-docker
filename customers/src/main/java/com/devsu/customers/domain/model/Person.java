package com.devsu.customers.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *<p>
 * Class: Person
 *<p/>
 *
 * Clase abstracta que representa a una persona.
 * Contiene atributos comunes a todas las personas.
 * Esta clase no se mapea a una tabla en la base de datos.
 **/
@Getter
@Setter
@MappedSuperclass
public abstract class Person {

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @Column(name = "age", length = 2, nullable = false)
    private Integer age;

    @Column(name = "identification", length = 20, unique = true, nullable = false)
    private String identification;

    @Column(name = "identification_type", length = 5, nullable = false)
    private String identificationType;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone", length = 30, nullable = false)
    private String phone;

}
