package BackEndProyecto.ClinicaOdontologica.repository;

import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
