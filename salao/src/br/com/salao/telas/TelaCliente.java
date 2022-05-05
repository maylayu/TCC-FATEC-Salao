/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.salao.telas;

import br.com.salao.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author yukii
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void read() {
        String sql = "select * from CLIENTES where nm_cliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

            /*if (rs.next()) {
                txtCliNome.setText(rs.getString(2));
                txtCliTel.setText(rs.getString(3));
                txtCliDtnasc.setText(rs.getString(4));

                //ifel + ctrl+space
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
                //as linhas abaixo limpam os campos
                txtCliNome.setText(null);
                txtCliTel.setText(null);
                txtCliDtnasc.setText(null);
            }*/
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void create() {
        String sql = "INSERT INTO CLIENTES(nm_cliente, tel_cliente, dt_nasc_cliente)VALUES (?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            //pst.setString(1, txtCliId.getText());
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliTel.getText());
            pst.setString(3, txtCliDtnasc.getText());

            //validando campos obrigatórios
            //txtCliId.getText().isEmpty())
            if ((txtCliNome.getText().isEmpty()) || (txtCliTel.getText().isEmpty())
                    || (txtCliDtnasc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    //txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliTel.setText(null);
                    txtCliDtnasc.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void update() {
        String sql = "UPDATE CLIENTES SET nm_cliente=?, tel_cliente=?, dt_nasc_cliente=? WHERE cd_cliente=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliTel.getText());
            pst.setString(3, txtCliDtnasc.getText());
            pst.setString(4, txtCliId.getText());

            //validando campos obrigatórios
            if ((txtCliId.getText().isEmpty()) || (txtCliNome.getText().isEmpty()) || (txtCliTel.getText().isEmpty())
                    || (txtCliDtnasc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliTel.setText(null);
                    txtCliDtnasc.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void delete() {
        int confirma = JOptionPane.showConfirmDialog(null, "Quer mesmo excluir esse cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM CLIENTES WHERE cd_cliente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                    txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliTel.setText(null);
                    txtCliDtnasc.setText(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        //txtCliId.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliTel.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliDtnasc.setText(tblClientes.getModel().getValueAt(setar, 3).toString());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCliId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCliTel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCliDtnasc = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(740, 560));
        setVisible(true);
        getContentPane().setLayout(null);

        txtCliId.setBackground(new java.awt.Color(204, 204, 255));
        txtCliId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtCliId);
        txtCliId.setBounds(130, 300, 400, 20);

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 300, 110, 30);

        txtCliNome.setBackground(new java.awt.Color(204, 204, 255));
        txtCliNome.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtCliNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtCliNome);
        txtCliNome.setBounds(170, 330, 360, 20);

        jLabel6.setBackground(new java.awt.Color(204, 0, 204));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nome:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 330, 110, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel8.setText("Gerenciar Clientes");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 0, 320, 40);

        jLabel7.setBackground(new java.awt.Color(204, 0, 204));
        jLabel7.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Telefone:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(80, 360, 110, 30);

        txtCliTel.setBackground(new java.awt.Color(204, 204, 255));
        txtCliTel.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtCliTel.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCliTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliTelActionPerformed(evt);
            }
        });
        getContentPane().add(txtCliTel);
        txtCliTel.setBounds(190, 360, 340, 20);

        jLabel9.setBackground(new java.awt.Color(204, 0, 204));
        jLabel9.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Data de Nascimento:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(80, 390, 230, 30);

        txtCliDtnasc.setBackground(new java.awt.Color(204, 204, 255));
        txtCliDtnasc.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtCliDtnasc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCliDtnasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliDtnascActionPerformed(evt);
            }
        });
        getContentPane().add(txtCliDtnasc);
        txtCliDtnasc.setBounds(310, 390, 220, 20);

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
        btnUsuCreate.setBounds(180, 440, 70, 60);

        btnUsuUpdate.setBackground(java.awt.Color.lightGray);
        btnUsuUpdate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate);
        btnUsuUpdate.setBounds(280, 440, 70, 60);

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
        btnUsuDelete.setBounds(380, 440, 70, 60);

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliPesquisar);
        txtCliPesquisar.setBounds(90, 50, 390, 40);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/lupinha2.png"))); // NOI18N
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(490, 50, 40, 40);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 100, 440, 120);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, 0, 734, 522);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void txtCliTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliTelActionPerformed

    private void txtCliDtnascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliDtnascActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliDtnascActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chamando o metodo logar
        delete();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed

    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        read();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnPesquisar;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliDtnasc;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTel;
    // End of variables declaration//GEN-END:variables
}
