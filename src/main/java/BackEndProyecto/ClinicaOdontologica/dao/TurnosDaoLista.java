package BackEndProyecto.ClinicaOdontologica.dao;

import BackEndProyecto.ClinicaOdontologica.model.Odontologo;
import BackEndProyecto.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TurnosDaoLista implements iDao<Turno>{
    private static final Logger logger= Logger.getLogger(TurnosDaoLista.class);
    private List<Turno> turnos = new ArrayList<>();


    @Override
    public Turno guardar(Turno turno) {
        PacienteDAOH2 daoPaciente = new PacienteDAOH2();
        OdontologoDaoH2 daoOdontologo = new OdontologoDaoH2();
        turno.setPaciente(daoPaciente.buscarPorID(turno.getPaciente().getId()));
        turno.setOdontologo(daoOdontologo.buscarPorID(turno.getOdontologo().getId()));
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorID(Integer id) {
        for(Turno turno: turnos) {
            if(turno.getId().equals(id)) {
                return turno;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        for(Turno turno1: turnos) {
            if (turno1.equals(turno)) {
                turno1 = turno;
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        Turno turno = buscarPorID(id);
        for(Turno turno1: turnos) {
            if (turno1.equals(turno)) {
                turnos.remove(turno1);
            }
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
