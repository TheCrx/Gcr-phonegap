package com.example.demo.controllers;

import com.example.demo.model.Alumno;
import com.example.demo.model.Horario;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerAlumno {
    @Autowired
    AlumnoRepository repositoryAlu;
    @Autowired
    HorarioRepository repositoryHor;

    @GetMapping("/mostraralumnos")
    public String mostraralumnos(Model modelo){
        modelo.addAttribute("alumnos", new Alumno());
        modelo.addAttribute("alumnos", repositoryAlu.findAll());
        modelo.addAttribute("horarios", repositoryHor.findAll());
        return ("mostraralumnos");
    }

    @GetMapping("/inscribir")
    public String inscribir(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "inscribir";
    }

    @PostMapping("/addAlumno")
    public String addAlumno(Alumno alu, RedirectAttributes redirectAttrs) {
        Alumno save = repositoryAlu.save(alu);
        redirectAttrs
                .addFlashAttribute("mensaje", "Alumno agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:inscribir";
    }
    @PostMapping("/mostraralumno")
    public String mostraralumno(Model model, @RequestParam(name="id") Long id){
        model.addAttribute("id_horario", id);
        model.addAttribute("alumnos",repositoryAlu.findAll());
        return "mostraralumno";
    }
    @PostMapping("/insertalumno/{id}")
    public String insertalumno(Model modelo, @ModelAttribute(value="rut") String rut,@PathVariable(name="id") Long id){
        if(repositoryAlu.findAlumnoByRut(rut)==null){
            System.out.println("Alumno inexistente");
            return "index";
        }
        Alumno alu=repositoryAlu.findAlumnoByRut(rut);
        modelo.addAttribute("alumno",alu);
        modelo.addAttribute("id",id);
        System.out.println(alu.getId());
        return "redirect:updatehorarioal/"+id+"/"+alu.getId();
    }
}
