package BackEndProyecto.ClinicaOdontologica.repository;

import BackEndProyecto.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnosRepository extends JpaRepository<Turno, Long> {
}
