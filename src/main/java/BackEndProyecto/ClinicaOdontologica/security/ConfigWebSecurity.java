package BackEndProyecto.ClinicaOdontologica.security;

import BackEndProyecto.ClinicaOdontologica.repository.UsuarioRepository;
import BackEndProyecto.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class ConfigWebSecurity {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/post_turnos.html").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/post_odontologos.html", "/get_odontologos.html", "/post_pacientes.html", "/get_pacientes.html","/get_turnos.html").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(form ->
                    form.successHandler(successHandler())
                )
                .logout(withDefaults())
                .sessionManagement(session -> {
                    session.sessionFixation().migrateSession();
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                            .invalidSessionUrl("/login")
                            .maximumSessions(1)
                            .expiredUrl("/login")
                            .sessionRegistry(sessionRegistry());
                })
                .httpBasic(basic -> {});
        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return  new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            response.sendRedirect("/index.html");
        });
    }
}
