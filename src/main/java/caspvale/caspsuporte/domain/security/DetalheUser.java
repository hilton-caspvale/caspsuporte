/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.security;

/**
 *
 * @author Hilton
 */
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**0
 *
 * @author Hilton
 */
@lombok.AllArgsConstructor
public class DetalheUser implements UserDetails {

    private final Optional<CaspUsuarios> usuario;

    private final boolean enabled;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final boolean accountNonLocked;
    private final String login;
    private final String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList lista = new ArrayList<>();
        lista.add("ADMINISTRADOR");
        lista.add("ANALISTA");
        lista.add("CLIENTE");
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new CaspUsuarios()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new CaspUsuarios()).getNlogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String teste() {
        return "TESTE";
    }

    
    

}
