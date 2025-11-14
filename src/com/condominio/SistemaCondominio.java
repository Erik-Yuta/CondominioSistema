package com.condominio;

import java.awt.EventQueue;

public class SistemaCondominio {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CondominioFrame frame = new CondominioFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
