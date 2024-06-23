package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.entity.Domicilio;
import BackEndProyecto.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    public void guardarPaciente() {
        Paciente paciente = new Paciente("Daniel", "Mera", "11111", LocalDate.of(2024,6,19), new Domicilio("calle falsa", 123, "La rioja", "Ecuador"), "dm375211@gmail.com" );
        Paciente pacienteguardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L, pacienteguardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId() {
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest() {
        Paciente paciente = new Paciente("Daniel", "Mora", "11111", LocalDate.of(2024,6,19), new Domicilio("calle falsa", 123, "La rioja", "Ecuador"), "dm375211@gmail.com" );
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(1L);
        assertEquals("Mora", pacienteBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        assertEquals(1, pacientes.size());
    }
}
