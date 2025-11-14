package com.condominio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioDialog extends JDialog {

    private JTextField textNome;
    private JTextField textEmail;
    private boolean confirmed = false;
    private Usuario usuario;
    private boolean isEditMode;

    public UsuarioDialog() {
        this(new Usuario("", ""), false);
    }

    public UsuarioDialog(Usuario usuario, boolean isEditMode) {
        this.usuario = usuario;
        this.isEditMode = isEditMode;
        setTitle(isEditMode ? "Editar Usuário" : "Adicionar Usuário");
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 80, 25);
        getContentPane().add(lblNome);

        textNome = new JTextField(usuario.getNome());
        textNome.setBounds(100, 10, 160, 25);
        getContentPane().add(textNome);
        textNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 50, 80, 25);
        getContentPane().add(lblEmail);

        textEmail = new JTextField(usuario.getEmail());
        textEmail.setBounds(100, 50, 160, 25);
        getContentPane().add(textEmail);
        textEmail.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(10, 100, 100, 25);
        getContentPane().add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuario.setNome(textNome.getText());
                usuario.setEmail(textEmail.getText());
                if (isEditMode) {
                    Conexao.atualizarUsuario(usuario); // Atualiza no banco de dados
                } else {
                    Conexao.inserirUsuario(usuario); // Salva no banco de dados
                }
                confirmed = true;
                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(160, 100, 100, 25);
        getContentPane().add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
