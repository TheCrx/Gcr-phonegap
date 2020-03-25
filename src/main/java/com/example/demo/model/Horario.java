package com.example.demo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity

public class Horario implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dia;
    @DateTimeFormat(pattern = "HH:mm")
    private Date hora;
    private String tipo;
    private Integer vacantes;
    @OneToMany(mappedBy ="horario",cascade = CascadeType.ALL)
    private Set<HorarioAlumno> horarioAlumnos=new HashSet<>();

    public Horario(){}
    public Horario(String tipo,Date hora, Integer vacantes, Date dia) {
        this.tipo= tipo;
        this.hora=hora;
        this.vacantes=vacantes;
        this.dia=dia;
    }

    public Date getDia() {

        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id_horario) {
        this.id = id_horario;
    }

    public Date getHora() { return hora;}

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

    public Set<HorarioAlumno> getHorarioAlumnos() {
        return horarioAlumnos;
    }

    public void setHorarioAlumnos(Set<HorarioAlumno> horarioAlumnos) {
        this.horarioAlumnos = horarioAlumnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return id.equals(horario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
