package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter

@Entity(name= "HorarioAlumno")
@Table(name= "horario_alumno")
public class HorarioAlumno implements Serializable {
    @EmbeddedId
    private HorarioAlumnoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("horarioId")
    private Horario horario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alumnoId")
    private Alumno alumno;

    private boolean presente;
    private HorarioAlumno(){}
    public HorarioAlumno(Horario horario,Alumno alumno) {
        this.alumno = alumno;
        this.presente = presente;
        this.presente= false;
        this.id= new HorarioAlumnoId(horario.getId(),alumno.getId());
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioAlumno that = (HorarioAlumno) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

