package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ControllerBasic {


    @RequestMapping({"/","/login"})
    public String login() {
        return "login";
    }


    @RequestMapping("/index")
	public String index() {
		return "index";
	}


	@GetMapping("/mostraralumno")
    public String mostraralumno() {
        return "mostraralumno";
    }


    @RequestMapping("/redirectToinscribir")
    public String redirectToinscribir() {
        return "redirect:inscribir";
    }

    @RequestMapping("/redirectTolista")
    public String redirectTolista() {
        return "redirect:lista";
    }

    @GetMapping("/admin_panel")
    public String admin() {
        return "admin_panel";
    }

    @RequestMapping("/redirectadmin_panel")
    public String redirectToadmin_panel() {
        return "redirect:admin_panel";
    }

    @GetMapping("/user_panel")
    public String user() {
        return "user_panel";
    }

    @RequestMapping("/redirectuser_panel")
    public String redirectTouser_panel() {
        return "redirect:user_panel";
    }

    @GetMapping("/notasalumnos")
    public String notasalumnos() {
        return "notasalumnos";
    }

    @RequestMapping("/redirectnotasalumnos")
    public String redirectTonotasalumnos() {
        return "redirect:notasalumnos";
    }


}
