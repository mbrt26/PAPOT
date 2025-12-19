/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.linxsi.modelo.touch.turno;

/**
 *
 * @author Edgar García
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Turno_DAO {

    private Connection connection;

    public Turno_DAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Turno_DTO turnoDTO) throws SQLException {
        String sql = "INSERT INTO tblTurno (id, nombre, estado) VALUES (?, ?, ?)";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, turnoDTO.getId());
            statement.setString(2, turnoDTO.getName());
            statement.setString(3, turnoDTO.getState());
            return statement.execute();
        }
    }

    public Turno_DTO read(int id) throws SQLException {
        String sql = "SELECT * FROM tblTurno WHERE id = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("nombre");
                    String estado = resultSet.getString("estado");
                    return new Turno_DTO(id, name, estado);
                } else {
                    return new Turno_DTO();
                }
            }
        }
    }

    public boolean update(Turno_DTO turnoDTO) throws SQLException {
        String sql = "UPDATE turnos SET nombre = ?, estado = ? WHERE id = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, turnoDTO.getName());
            statement.setString(2, turnoDTO.getState());
            statement.setInt(3, turnoDTO.getId());
            return statement.execute();
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tblTurno WHERE id = ?";
        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.execute();
        }
    }

    public List<Turno_DTO> findAll() throws SQLException {
        String sql = "SELECT * FROM tblTurno";
        List<Turno_DTO> turnoDTOs = new ArrayList<>();
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String estado = resultSet.getString("estado");
                turnoDTOs.add(new Turno_DTO(id, name, estado));
            }
        }
        return turnoDTOs;
    }

    public List<Turno_DTO> findAllOrderedByName() throws SQLException {
        String sql = "SELECT * FROM tblTurno ORDER BY nombre";
        List<Turno_DTO> turnoDTOs = new ArrayList<>();
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String estado = resultSet.getString("estado");
                turnoDTOs.add(new Turno_DTO(id, name, estado));
            }
        }
        return turnoDTOs;
    }
}
