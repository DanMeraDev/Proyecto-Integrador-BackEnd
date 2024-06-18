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
        Usuario usuario = new Usuario("Daniel", "dan32", "dm375211@gmail.com", passCifrado, UsuarioRole.ROLE_ADMIN);
        System.out.println("pass cifrado: " + passCifrado);
        usuarioRepository.save(usuario);
    }
}
