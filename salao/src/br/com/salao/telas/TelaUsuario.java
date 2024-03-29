/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.salao.telas;

import java.sql.*;
import br.com.salao.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author yukii
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void read() {
        String sql = "select * from USUARIOS where cd_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(4));
                //combo box
                cboUsuPerfil.setSelectedItem(rs.getString(5));

                //ifel + ctrl+space
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                //as linhas abaixo limpam os campos
                txtUsuNome.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
                //combo box
                //cboUsuPerfil.setSelectedItem(null);
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void create() {
        String sql = "INSERT INTO USUARIOS(cd_usuario, nm_usuario, login, senha, perfil)VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());

            //validando campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty())
                    || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Usuário já cadastrado!");
        }

    }

    private void update() {
        String sql = "UPDATE USUARIOS SET nm_usuario=?, login=?, senha=?, perfil=? WHERE cd_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuId.getText());

            //validando campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty())
                    || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void delete() {
        
            
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Quer mesmo excluir esse usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM USUARIOS WHERE cd_usuario=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtUsuId.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        txtUsuId.setText(null);
                        txtUsuNome.setText(null);
                        txtUsuLogin.setText(null);
                        txtUsuSenha.setText(null);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para excluir!");
                }
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuId = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuDelete = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Usuários");
        setMinimumSize(new java.awt.Dimension(735, 0));
        getContentPane().setLayout(null);

        jLabel2.setBackground(new java.awt.Color(204, 0, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Senha:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 280, 100, 40);

        jLabel3.setBackground(new java.awt.Color(204, 0, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 230, 80, 40);

        jLabel4.setBackground(new java.awt.Color(204, 0, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Perfil:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(130, 330, 90, 40);

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(130, 130, 100, 40);

        txtUsuLogin.setBackground(new java.awt.Color(204, 204, 255));
        txtUsuLogin.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtUsuLogin.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuLogin);
        txtUsuLogin.setBounds(240, 240, 260, 30);

        txtUsuNome.setBackground(new java.awt.Color(204, 204, 255));
        txtUsuNome.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtUsuNome.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtUsuNome.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuNome);
        txtUsuNome.setBounds(240, 190, 260, 30);

        txtUsuId.setBackground(new java.awt.Color(204, 204, 255));
        txtUsuId.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtUsuId.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtUsuId.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtUsuId.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtUsuId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuId);
        txtUsuId.setBounds(240, 140, 260, 30);

        cboUsuPerfil.setBackground(new java.awt.Color(204, 204, 255));
        cboUsuPerfil.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboUsuPerfil.setForeground(new java.awt.Color(51, 51, 51));
        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(cboUsuPerfil);
        cboUsuPerfil.setBounds(240, 340, 260, 30);

        btnUsuDelete.setBackground(java.awt.Color.lightGray);
        btnUsuDelete.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover!");
        btnUsuDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(70, 70));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuDelete);
        btnUsuDelete.setBounds(440, 400, 70, 60);

        btnUsuRead.setBackground(java.awt.Color.lightGray);
        btnUsuRead.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar!");
        btnUsuRead.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuRead);
        btnUsuRead.setBounds(220, 400, 70, 60);

        btnUsuCreate.setBackground(java.awt.Color.lightGray);
        btnUsuCreate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar!");
        btnUsuCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuCreate);
        btnUsuCreate.setBounds(110, 400, 70, 60);

        btnUsuUpdate.setBackground(java.awt.Color.lightGray);
        btnUsuUpdate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar!");
        btnUsuUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate);
        btnUsuUpdate.setBounds(330, 400, 70, 60);

        jLabel6.setBackground(new java.awt.Color(204, 0, 204));
        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nome:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(130, 180, 100, 40);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Gerenciar Usuários do Sistema");
        jLabel8.setToolTipText("");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(140, 0, 500, 70);

        txtUsuSenha.setBackground(new java.awt.Color(204, 204, 255));
        txtUsuSenha.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtUsuSenha.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(txtUsuSenha);
        txtUsuSenha.setBounds(240, 290, 260, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 760, 630);

        setBounds(0, 0, 690, 558);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuLoginActionPerformed

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void txtUsuIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuIdActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chamando o metodo logar
        delete();

    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // TODO add your handling code here:
        read();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JPasswordField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
