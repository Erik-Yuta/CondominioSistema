package com.condominio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/condominio";
    private static final String DB_USER = "root"; // Atualize com seu usuário MySQL
    private static final String DB_PASSWORD = ""; // Atualize com sua senha MySQL

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Certifique-se de ter o conector MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void inserirUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> buscarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT id, nome, email FROM usuarios";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void atualizarUsuario(Usuario usuario) {
        String query = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setInt(3, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerUsuario(int id) {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
