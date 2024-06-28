package BackEndProyecto.ClinicaOdontologica.Controller;

import BackEndProyecto.ClinicaOdontologica.entity.Odontologo;
import BackEndProyecto.ClinicaOdontologica.service.OdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@Tag(name = "Controller de Odontologos", description = "Este endpoint nos permite operar con Odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);


    @Operation(summary = "nos permite registrar un objeto odontologo", description = "devuelve el objeto completo")
    @PostMapping
    public ResponseEntity<Odontologo> registrarUnOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @Operation(summary = "nos permite visualizar un objeto odontologo mediante un id", description = "devuelve el objeto completo")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologoPorId(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @Operation(summary = "nos permite visualizar a todos los objetos odontologo", description = "devuelve un array de odontologos")
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @Operation(summary = "nos permite actualizar un objeto odontologo", description = "devuelve el objeto completo")
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBusqueda = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBusqueda.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok().body("Se actualizó correctamente");
        }
        return ResponseEntity.badRequest().body("Odontólogo no encontrado");
    }

    @Operation(summary = "nos permite eliminar un objeto odontologo", description = "elimina el objeto completo")
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
