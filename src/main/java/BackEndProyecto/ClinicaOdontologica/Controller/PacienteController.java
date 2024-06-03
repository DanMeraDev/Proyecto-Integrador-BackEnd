package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.model.Paciente;
import BackEndProyecto.ClinicaOdontologica.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }


//    @GetMapping
//    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
//        //vamos a pasar la solicitud atraves del http, osea va a ir en la url
//        Paciente paciente= pacienteService.buscarPorCorreo(email);
//        model.addAttribute("nombre",paciente.getNombre());
//        model.addAttribute("apellido",paciente.getApellido());
//        return "index";
//        //return pacienteService.buscarPorCorreo(email);
//    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //Necesitamos primeramente validar si existe o no
        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok().body("Paciente actualizado con exito");
        }
      return ResponseEntity.badRequest().body("Paciente no encontrado");
    }


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable int id) {
        Paciente pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado != null) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok().body("Paciente eliminado con exito");
        }
        return ResponseEntity.badRequest().body("Paciente no encontrado");
    }
}