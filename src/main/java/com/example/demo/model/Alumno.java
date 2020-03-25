package com.example.demo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data

@Entity

public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private Integer edad;
    private String rut;
    private String nacionalidad;
    private String sexo;
    private String domicilio;
    private String email;
    private String telefono_casa;
    private String telefono_celular1;
    private String telefono_celular2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date f_nacimiento;
    @OneToMany(mappedBy = "alumno",cascade = CascadeType.ALL)
    private Set<HorarioAlumno> horarioalumno= new HashSet<>();


    public Alumno(String nombre, Integer edad, String rut, String nacionalidad, String sexo, String domicilio, String email, String telefono_casa, String telefono_celular1, String telefono_celular2, Date f_nacimiento){

        this.nombre = nombre;
        this.edad = edad;
        this.rut = rut;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
        this.domicilio = domicilio;
        this.email = email;
        this.telefono_casa = telefono_casa;
        this.telefono_celular1 = telefono_celular1;
        this.telefono_celular2 = telefono_celular2;
        this.f_nacimiento=f_nacimiento;
    }

    public Alumno(){

    }

    public Long getId() { return id; }
    public String getNombre() {return nombre;}
    public Integer getEdad() { return edad; }
    public String getRut() {return rut; }
    public String getNacionalidad() {return nacionalidad; }
    public String getSexo() {return sexo; }
    public String getDomicilio() {return domicilio; }
    public String getEmail() { return email;}
    public String getTelefono_casa() {return telefono_casa; }
    public String getTelefono_celular1() {return telefono_celular1; }
    public String getTelefono_celular2() {return telefono_celular2; }
    public Date getF_nacimiento() {
        return f_nacimiento; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono_casa(String telefono_casa) {
        this.telefono_casa = telefono_casa;
    }

    public void setTelefono_celular1(String telefono_celular1) {
        this.telefono_celular1 = telefono_celular1;
    }

    public void setTelefono_celular2(String telefono_celular2) {
        this.telefono_celular2 = telefono_celular2;
    }

    public void setF_nacimiento(Date f_nacimiento) {
        this.f_nacimiento = f_nacimiento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id.equals(alumno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}