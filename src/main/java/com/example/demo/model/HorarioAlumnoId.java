package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HorarioAlumnoId implements Serializable {
    @Column(name = "horario_id")
    private Long horario_id;
    @Column(name = "alumno_id")
    private Long alumno_id;
    private HorarioAlumnoId(){}

    public HorarioAlumnoId(Long horario_id, Long alumno_id) {
        this.alumno_id=alumno_id;
        this.horario_id=horario_id;
    }

    public Long getHorario_id() {
        return horario_id;
    }

    public void setHorario_id(Long horario_id) {
        this.horario_id = horario_id;
    }

    public Long getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(Long alumno_id) {
        this.alumno_id = alumno_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioAlumnoId that = (HorarioAlumnoId) o;
        return Objects.equals(horario_id, that.horario_id) &&
                Objects.equals(alumno_id, that.alumno_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horario_id, alumno_id);
    }
}
