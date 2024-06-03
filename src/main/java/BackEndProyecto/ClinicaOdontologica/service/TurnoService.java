package BackEndProyecto.ClinicaOdontologica.service;

import BackEndProyecto.ClinicaOdontologica.dao.TurnosDaoH2;
import BackEndProyecto.ClinicaOdontologica.dao.TurnosDaoLista;
import BackEndProyecto.ClinicaOdontologica.dao.iDao;
import BackEndProyecto.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao = new TurnosDaoH2();
    }
    public Turno guardarTurno(Turno turno) {
        return turnoiDao.guardar(turno);
    }
    public Turno buscarPorID(int id) {
        return turnoiDao.buscarPorID(id);
    }
    public List<Turno> listarTurnos() {
        return turnoiDao.buscarTodos();
    }
    public void actualizarTurno(Turno turno) {
        turnoiDao.actualizar(turno);
    }
    public void eliminarTurno(int id) {
        turnoiDao.eliminar(id);
    }
}
