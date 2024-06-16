package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarUnOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorId(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBusqueda = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBusqueda.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok().body("Se actualizó correctamente");
        }
        return ResponseEntity.badRequest().body("Odontólogo no encontrado");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontologoBusqueda = odontologoService.buscarPorId(id);
        if(odontologoBusqueda.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok().body("Odontólogo eliminado exitosamente");
        }
        return ResponseEntity.badRequest().body("Odontólogo no encontrado");
    }
}
