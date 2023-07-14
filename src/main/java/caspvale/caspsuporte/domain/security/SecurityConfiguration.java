package caspvale.caspsuporte.domain.security;

import caspvale.caspsuporte.atendimento.domain.repository.UsuariosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Hilton
 */
@lombok.AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UsuariosRepository usuariosRepository;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public SSUserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(usuariosRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/public/css/**", "/public/js/**", "/public/imagens/**").permitAll()
                .antMatchers("/").hasAnyAuthority("CLIENTE", "ANALISTA", "ADMINISTRADOR")
                .antMatchers("/public/**").permitAll()
                .antMatchers("/private/css/**").authenticated()
                .antMatchers("/private/js/**").authenticated()
                .antMatchers("/private/atendimento/**").hasAnyAuthority("ADMINISTRADOR", "ANALISTA","CLIENTE")                
                .antMatchers("/mv/atendimento/**").hasAnyAuthority("ADMINISTRADOR", "ANALISTA","CLIENTE")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutUrl("/logout")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                //                .and()
                //                .exceptionHandling().accessDeniedPage("/ff")
                .and().httpBasic();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {     
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(getPasswordEncoder());
    }

}
