package caspvale.caspsuporte.domain.security;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.UsuariosRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Transactional
//@lombok.AllArgsConstructor
@Service
public class SSUserDetailsService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;
    
    public SSUserDetailsService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }
    
    /*public SSUserDetailsService(UsuariosRepository usuariosRepository, String teretete ){
        this.usuariosRepository = usuariosRepository;
        this.teretete = teretete;
    */

    @Override
    public UserDetails loadUserByUsername(String nlogin) throws UsernameNotFoundException {
        //System.out.println("loadusername> "+nlogin);
        try {
            Optional<CaspUsuarios> usuario = usuariosRepository.findByNlogin(nlogin);

            if (usuario.isEmpty()) {                
                throw new UsernameNotFoundException("Usuário " + nlogin + " não encontrado!");
            }
            boolean enabled = usuario.get().getSituacaoUsuario().equals("A");
            boolean accountNonExpired = usuario.get().getSituacaoUsuario().equals("A");
            boolean credentialsNonExpired = usuario.get().getSituacaoUsuario().equals("A");
            boolean accountNonLocked = usuario.get().getSituacaoUsuario().equals("A");
            String login = usuario.get().getNlogin();
            String senha = usuario.get().getSenha();
            if(usuario.get().getSenha() == null){
                throw new UsernameNotFoundException("O usuário não possui uma senha ativa!");
            }
            User user = new User(login, senha, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthories(usuario.get()));           
            /*DetalheUser user2 = new DetalheUser(usuario, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, login, senha);
            System.out.println("TESTE*****: "+user2.teste());*/
            return user;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Não foi possível efetuar o login");
        }
    }    

    private Set<GrantedAuthority> getAuthories(CaspUsuarios usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getITipoUsuario().getDescricaoTipoUsuario());
        //GrantedAuthority id = new SimpleGrantedAuthority(usuario.getIUsuario().toString());
        authorities.add(grantedAuthority);
        //authorities.add(id);
        return authorities;
    }
    

}
