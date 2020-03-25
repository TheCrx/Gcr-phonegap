package com.example.demo.controllers;

import com.example.demo.model.Alumno;
import com.example.demo.model.Horario;
import com.example.demo.model.HorarioAlumno;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


@Controller
public class ControllerHorario {

    @Autowired
    HorarioRepository repositoryHor;
    @Autowired
    AlumnoRepository repositoryAlu;

    @GetMapping("/mostrarhorarios")
    public String mostrarhorarios(Model modelo){
        ArrayList<Dictionary> dict=new ArrayList<>();
        for(Iterator<Horario> it= repositoryHor.findAll().iterator(); it.hasNext();){
            Horario h= it.next();
            Dictionary hor= new Hashtable();
            hor.put("id",h.getId());
            hor.put("hora",h.getHora());
            hor.put("vacantes",h.getVacantes());
            hor.put("tipo",h.getTipo());
            hor.put("dia",h.getDia());
            ArrayList <Alumno> alu=new ArrayList<>();
            for(Iterator<HorarioAlumno> it2= h.getHorarioAlumnos().iterator(); it2.hasNext();){
                HorarioAlumno ha=it2.next();
                alu.add(repositoryAlu.findById(ha.getAlumno().getId()).get());
            }hor.put("alumnos",alu);
            dict.add(hor);
        }
        modelo.addAttribute("horarios",dict);
        modelo.addAttribute("newHorario",null);
        return ("mostrarhorarios");
    }
    @GetMapping("/lista")
    public String lista(Model modelo){
        ArrayList<Dictionary> dict=new ArrayList<>();
        for(Iterator<Horario> it= repositoryHor.findAll().iterator(); it.hasNext();){
            Horario h= it.next();
            Dictionary hor= new Hashtable();
            hor.put("id",h.getId());
            hor.put("hora",h.getHora());
            hor.put("vacantes",h.getVacantes());
            hor.put("tipo",h.getTipo());
            hor.put("dia",h.getDia());
            ArrayList <Dictionary> alu=new ArrayList<>();
            for(Iterator<HorarioAlumno> it2= h.getHorarioAlumnos().iterator(); it2.hasNext();){
                HorarioAlumno ha=it2.next();
                Dictionary dic=new Hashtable();
                Alumno aluwu= repositoryAlu.findById(ha.getAlumno().getId()).get();
                if (ha.isPresente()){
                    dic.put("asistencia","Presente");
                }else{
                    dic.put("asistencia","Ausente");
                }
                dic.put("id",aluwu.getId());
                dic.put("nombre",aluwu.getNombre());
                dic.put("rut",aluwu.getRut());
                alu.add(dic);
            }hor.put("alumnos",alu);
            dict.add(hor);
        }
        modelo.addAttribute("horarios",dict);
        modelo.addAttribute("newHorario",null);
        return ("lista");
    }
    @GetMapping("/inscribirhorario")
    public String inscribirhorario(Model model) {
        model.addAttribute("horario", new Horario());
        return "inscribirhorario";
    }
    @PostMapping("/addHorario")
    public String processForm(Horario hor, RedirectAttributes redirectAttrs) {
        Horario save = repositoryHor.save(hor);
        redirectAttrs
                .addFlashAttribute("mensaje", "Horario agregado correctamente")
                .addFlashAttribute(     "clase", "success");
        return "redirect:inscribirhorario";
    }
    @PostMapping("/addHorAl")
    public String addHoraAl(@RequestParam(name="id_horario") Long id_horario,@RequestParam(name="id_alumno") Long id_alumno){
        Horario horarioedit= repositoryHor.findById(id_horario).get();
        Alumno alumno=repositoryAlu.findById(id_alumno).get();
        Set<HorarioAlumno> a=horarioedit.getHorarioAlumnos();
        HorarioAlumno b=new HorarioAlumno(horarioedit,alumno);
        if(horarioedit.getVacantes()<=0){
            System.out.println("Curso completo");
            return "redirect:admin_panel";
        }for(Iterator<HorarioAlumno> it= a.iterator(); it.hasNext();){
            if(it.next().equals(b)){
                System.out.println("Alumno ya ingresado");
                return "redirect:admin_panel";
            }
        }a.add(b);
        horarioedit.setVacantes(horarioedit.getVacantes()-1);
        horarioedit.setHorarioAlumnos(a);
        repositoryHor.save(horarioedit);
        return "redirect:admin_panel";
    }@PostMapping("/ponerAsistente")
    public String ponerAsistente(@RequestParam(name="id_horario") Long id_horario,@RequestParam(name="id_alumno") Long id_alumno){
        Horario horarioedit= repositoryHor.findById(id_horario).get();
        Alumno alumno=repositoryAlu.findById(id_alumno).get();
        Set<HorarioAlumno> a=horarioedit.getHorarioAlumnos();
        HorarioAlumno newb=new HorarioAlumno(horarioedit,alumno);
        Set<HorarioAlumno> newa = new HashSet<>();
        for(Iterator<HorarioAlumno> it= a.iterator(); it.hasNext();){
            HorarioAlumno oldb=it.next();
            if(oldb.equals(newb)){
                if(oldb.isPresente()){
                    oldb.setPresente(false);
                }else{
                    oldb.setPresente(true);
                }


            }newa.add(oldb);
        }
        horarioedit.setVacantes(horarioedit.getVacantes()-1);
        horarioedit.setHorarioAlumnos(newa);
        repositoryHor.save(horarioedit);
        return "redirect:lista";
    }

}
