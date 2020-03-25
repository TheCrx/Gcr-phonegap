package com.example.demo;


import com.example.demo.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //@Autowired
    //private AlumnoService alumnoDetailsService;
    @Autowired
    AuthenticationSuccessHandler successHandler;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
    @Override
    // Configuración de credenciales de inicio de sesion //
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(alumnoDetailsService); //Usar valores de la base de datos, configurado en el service de Alumnos
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("root")
                .roles("USER")
                .and()
                .withUser("adm")
                .password("hola")
                .roles("ADMIN");
        // Lo de arriba son credenciales de pruebas xDD
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    String[] resources = new String[]{
            "/statics/**","/fragments/**"
    };
    String[] paginas = new String[]{ //Agregar las paginas que se pueden acceder logeado
            "/index","/lista", "/inscribir", "/inscribirhorario", "/mostrarhorarios", "/mostraralumnos", "hola", "hola2"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(paginas).access("hasRole('USER') or hasRole('ADMIN')")
                /*.antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/").permitAll()*/

                .and().formLogin().successHandler(successHandler)
                .loginPage("/login")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("pass")
                    //.defaultSuccessUrl("/index",true)
                .and()
                .csrf().disable()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout");

    }

}
