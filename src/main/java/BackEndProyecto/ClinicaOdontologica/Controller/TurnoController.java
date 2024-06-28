package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.dto.TurnoDTO;
import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import BackEndProyecto.ClinicaOdontologica.entity.Turno;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import BackEndProyecto.ClinicaOdontologica.service.PacienteService;
import BackEndProyecto.ClinicaOdontologica.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@Tag(name = "Controller de Turnos", description = "Este endpoint nos permite operar con Turnos")

public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Operation(summary = "nos permite registrar un objeto turno", description = "devuelve el objeto completo")
    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteAux = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologoAux = odontologoService.buscarPorId(turno.getPaciente().getId());
        if(pacienteAux.isPresent() && odontologoAux.isPresent()) {
            turno.setPaciente(pacienteAux.get());
            turno.setOdontologo(odontologoAux.get());
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "nos permite visualizar a todos los objetos turno", description = "devuelve el objeto completo")
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTodosLosTurnos() {
        return  ResponseEntity.ok(turnoService.listarTurnos());
    }

    @Operation(summary = "nos permite visualizar un objeto turno mediante id", description = "devuelve el objeto completo")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TurnoDTO>> buscarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorID(id));
    }

    @Operation(summary = "nos permite actualizar un objeto turno", description = "devuelve el objeto completo")
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) {
        Optional<TurnoDTO> turnoBusqueda = turnoService.buscarPorID(turno.getId());
        if(turnoBusqueda.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok().body("Turno actualizado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }

    @Operation(summary = "nos permite eliminar un objeto turno", description = "devuelve el objeto completo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBusqueda = turnoService.buscarPorID(id);
        if(turnoBusqueda.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Turno eliminado con éxito");
        }
        return ResponseEntity.badRequest().body("Turno no encontrado");
    }

}
