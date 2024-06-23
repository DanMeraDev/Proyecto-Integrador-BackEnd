package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import BackEndProyecto.ClinicaOdontologica.entity.Turno;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import BackEndProyecto.ClinicaOdontologica.service.PacienteService;
import BackEndProyecto.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteAux = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologoAux = odontologoService.buscarPorId(turno.getPaciente().getId());
        if(pacienteAux.isPresent() && odontologoAux.isPresent()) {
            turno.setPaciente(pacienteAux.get());
            turno.setOdontologo(odontologoAux.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return  ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorID(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<Turno> turnoBusqueda = turnoService.buscarPorID(turno.getId());
        if(turnoBusqueda.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok().body("Turno actualizado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBusqueda = turnoService.buscarPorID(id);
        if(turnoBusqueda.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Turno eliminado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }

}
