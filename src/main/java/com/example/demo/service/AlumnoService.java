package com.example.demo.service;

import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AlumnoService implements UserDetailsService {
    @Autowired
    private AlumnoRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Alumno al = repo.findByEmail(username);
       List<GrantedAuthority> roles = new ArrayList<>();
       roles.add(new SimpleGrantedAuthority("USER"));
       UserDetails userDet = new User(al.getEmail(),al.getRut(),roles);  // usuario->Email , contraseña->Rut
       return userDet;

    }
}
