package BackEndProyecto.ClinicaOdontologica.security;

import BackEndProyecto.ClinicaOdontologica.entity.Usuario;
import BackEndProyecto.ClinicaOdontologica.entity.UsuarioRole;
import BackEndProyecto.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar="admin";
        String passCifrado = bCryptPasswordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("Daniel", "user", "dm375211@gmail.com", bCryptPasswordEncoder.encode("user"), UsuarioRole.ROLE_USER);
        Usuario admin = new Usuario("Daniel", "admin", "dm375211@gmail.com", passCifrado, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);
        usuarioRepository.save(admin);
    }

}
