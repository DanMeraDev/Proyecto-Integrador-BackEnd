package BackEndProyecto.ClinicaOdontologica.dao;

import BackEndProyecto.ClinicaOdontologica.model.Odontologo;
import BackEndProyecto.ClinicaOdontologica.model.Paciente;
import BackEndProyecto.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnosDaoH2 implements iDao<Turno> {
    private static final Logger logger= Logger.getLogger(TurnosDaoLista.class);
    private static final String SQL_INSERT="INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA_TURNO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM TURNOS  WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM TURNOS";
    private static final String SQL_UPDATE="UPDATE TURNOS SET ODONTOLOGO_ID=?, FECHA_TURNO=? WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM TURNOS WHERE ID=?";

    @Override
    public Turno guardar(Turno turno) {
        logger.info("inciando la operacion de guardado de turno");
        Connection connection = null;
        try{
            PacienteDAOH2 pacienteAux = new PacienteDAOH2();
            Paciente paciente = pacienteAux.guardar(turno.getPaciente());
            OdontologoDaoH2 odontologoAux = new OdontologoDaoH2();
            Odontologo odontologo = odontologoAux.guardar(turno.getOdontologo());
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, paciente.getId());
            psInsert.setInt(2, odontologo.getId());
            psInsert.setDate(3, Date.valueOf(turno.getFecha()));
            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            while(rs.next()) {
                turno.setId(rs.getInt(1));
            }
            logger.info("Turno asignado con éxito");

        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return turno;
    }

    @Override
    public Turno buscarPorID(Integer id) {
        logger.info("inciando la busqueda de un paciente por id: " + id);
        Connection connection = null;
        Paciente paciente = null;
        Odontologo odontologo = null;
        Turno turno = null;
        try{
            connection = BD.getConnection();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1, id);
            ResultSet rs = psSelectOne.executeQuery();
            PacienteDAOH2 pacienteAux = new PacienteDAOH2();
            OdontologoDaoH2 odontologoAux = new OdontologoDaoH2();
            while (rs.next()) {
                paciente = pacienteAux.buscarPorID(rs.getInt(2));
                odontologo = odontologoAux.buscarPorID(rs.getInt(3));
                turno = new Turno(rs.getInt(1), paciente, odontologo, rs.getDate(4).toLocalDate());
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return turno;
    }

    @Override
    public void actualizar(Turno turno) {
        logger.info("iniciando la operacion de actualizar el turno con id:" + turno.getId());
        Connection connection = null;
        OdontologoDaoH2 odontoAux = new OdontologoDaoH2();
        odontoAux.actualizar(turno.getOdontologo());
        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, turno.getOdontologo().getId());
            psUpdate.setDate(2, Date.valueOf(turno.getFecha()));
            psUpdate.setInt(3, turno.getId());
            psUpdate.executeUpdate();
            logger.info("Actualización generada con éxito");
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando la operación de eliminar el turno con id: "+id);
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.execute();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("iniciando la operación de buscar todos los turnos");
        Connection connection = null;
        List<Turno> turnos = new ArrayList<>();
        Turno turno = null;
        Paciente paciente = null;
        Odontologo odontologo = null;
        try{
            connection = BD.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);
            logger.info("turnos buscados correctamente");
            PacienteDAOH2 pacienteDAOH2 = new PacienteDAOH2();
            OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
            while(rs.next()) {
                paciente = pacienteDAOH2.buscarPorID(rs.getInt(2));
                odontologo = odontologoDaoH2.buscarPorID(rs.getInt(3));
                turno = new Turno(rs.getInt(1), paciente, odontologo, rs.getDate(4).toLocalDate());
                turnos.add(turno);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return turnos;
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }
}
