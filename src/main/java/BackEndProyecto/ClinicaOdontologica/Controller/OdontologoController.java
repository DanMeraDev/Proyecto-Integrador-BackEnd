package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.dao.OdontologoDaoH2;
import BackEndProyecto.ClinicaOdontologica.model.Odontologo;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarUnOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(Odontologo odontologo) {
        Odontologo odontologoBusqueda = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBusqueda!=null) {
            odontologoService.actualizarOdontologo(odontologoBusqueda);
            return ResponseEntity.ok().body("Se actualizó correctamente");
        }
        return ResponseEntity.badRequest().body("Odontólogo no encontrado");
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarOdontologo(int id) {
        Odontologo odontologoBusqueda = odontologoService.buscarPorId(id);
        if(odontologoBusqueda!=null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok().body("Odontólogo eliminado exitosamente");
        }
        return ResponseEntity.badRequest().body("Odontólogo no encontrado");
    }
}
