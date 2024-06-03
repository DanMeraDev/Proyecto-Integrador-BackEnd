package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.model.Turno;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import BackEndProyecto.ClinicaOdontologica.service.PacienteService;
import BackEndProyecto.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController() {
        turnoService = new TurnoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        PacienteService pacienteService = new PacienteService();
        OdontologoService odontologoService = new OdontologoService();
        if(pacienteService.buscarPaciente(turno.getPaciente().getId())!=null && odontologoService.buscarPorId(turno.getOdontologo().getId())!=null) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return  ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id) {
        return ResponseEntity.ok(turnoService.buscarPorID(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Turno turnoBusqueda = turnoService.buscarPorID(turno.getId());
        if(turnoBusqueda!=null) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok().body("Turno actualizado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) {
        Turno turnoBusqueda = turnoService.buscarPorID(id);
        if(turnoBusqueda!=null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Turno eliminado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }

}
