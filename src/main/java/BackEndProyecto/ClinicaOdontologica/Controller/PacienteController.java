package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import BackEndProyecto.ClinicaOdontologica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@Tag(name = "Controller de Pacientes", description = "Este endpoint nos permite operar con Pacientes")

public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


//    @GetMapping
//    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
//        //vamos a pasar la solicitud atraves del http, osea va a ir en la url
//        Paciente paciente= pacienteService.buscarPorCorreo(email);
//        model.addAttribute("nombre",paciente.getNombre());
//        model.addAttribute("apellido",paciente.getApellido());
//        return "index";
//        //return pacienteService.buscarPorCorreo(email);
//    }
    @Operation(summary = "nos permite registrar un objeto paciente", description = "devuelve el objeto completo")
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @Operation(summary = "nos permite actualizar un objeto paciente", description = "devuelve el objeto completo")
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //Necesitamos primeramente validar si existe o no
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok().body("Paciente actualizado con exito");
        }
      return ResponseEntity.badRequest().body("Paciente no encontrado");
    }


    @Operation(summary = "nos permite visualizar a todos los objetos paciente", description = "devuelve el objeto completo")
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @Operation(summary = "nos permite visualizar un objeto paciente mediante un id", description = "devuelve el objeto completo")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @Operation(summary = "nos permite eliminar un objeto odontologo", description = "elimina el objeto completo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);

        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente eliminado con exito");
        } else {
            throw new ResourceNotFoundException("No existe ese id :"+ id);
        }
    }
}