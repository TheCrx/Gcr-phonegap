package com.example.demo.repository;
import com.example.demo.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    Alumno findAlumnoByRut(String rut);
    Alumno findByEmail(String email);
}
