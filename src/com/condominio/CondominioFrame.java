package com.condominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CondominioFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Usuario> usuarios = new ArrayList<>();

    public CondominioFrame() {
        setTitle("Sistema de Condomínio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 542, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(10, 10, 120, 25);
        contentPane.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(140, 10, 120, 25);
        contentPane.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(270, 10, 120, 25);
        contentPane.add(btnExcluir);

        JButton btnAtualizar = new JButton("Atualizar Tabela");
        btnAtualizar.setBounds(400, 10, 120, 25);
        contentPane.add(btnAtualizar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 510, 300);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Email"}
        );

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // Action Listeners
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarUsuarios();
            }
        });

        // Load users from the database at startup
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        usuarios = Conexao.buscarUsuarios(); // Carrega os usuários do banco de dados
        atualizarTabela();
    }

    private void adicionarUsuario() {
        UsuarioDialog dialog = new UsuarioDialog(new Usuario("", ""), false);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Usuario usuario = dialog.getUsuario();
            Conexao.inserirUsuario(usuario); // Salva no banco de dados
            carregarUsuarios(); // Atualiza a lista de usuários e a tabela
        }
    }

    private void editarUsuario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Usuario usuario = usuarios.get(selectedRow);
            UsuarioDialog dialog = new UsuarioDialog(usuario, true);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                Conexao.atualizarUsuario(dialog.getUsuario()); // Atualiza no banco de dados
                carregarUsuarios(); // Atualiza a lista de usuários e a tabela
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
        }
    }

    private void excluirUsuario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Conexao.removerUsuario(id); // Remove do banco de dados
            carregarUsuarios(); // Atualiza a lista de usuários e a tabela
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpar a tabela
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail()});
        }
    }
}
