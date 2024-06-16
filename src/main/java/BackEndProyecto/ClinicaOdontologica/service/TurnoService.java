package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.entity.Turno;
import BackEndProyecto.ClinicaOdontologica.repository.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TurnoService {
    @Autowired
    public TurnosRepository turnosRepository;

    public Turno guardarTurno(Turno turno) {
        return turnosRepository.save(turno);
    }
    public Optional<Turno> buscarPorID(Long id) {
        return turnosRepository.findById(id);
    }
    public List<Turno> listarTurnos() {
        return turnosRepository.findAll();
    }
    public void actualizarTurno(Turno turno) {
        turnosRepository.save(turno);
    }
    public void eliminarTurno(Long id) {
        turnosRepository.deleteById(id);
    }
}
