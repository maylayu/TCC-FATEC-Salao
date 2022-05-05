/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
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
public class TelaFuncionario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaFuncionario() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    private void read() {
        String sql = "select * from FUNCIONARIOS where nm_funcionario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFunPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblFuncionarios.setModel(DbUtils.resultSetToTableModel(rs));

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
        String sql = "INSERT INTO FUNCIONARIOS(cd_cpf_funcionario, nm_funcionario, tel_funcionario, dt_nasc_func)VALUES (?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFunCpf.getText());
            pst.setString(2, txtFunNome.getText());
            pst.setString(3, txtFunTel.getText());
            pst.setString(4, txtFunDtnasc.getText());

            //validando campos obrigatórios
            //txtCliId.getText().isEmpty())
            if ((txtFunCpf.getText().isEmpty())||(txtFunNome.getText().isEmpty()) || (txtFunTel.getText().isEmpty())
                    || (txtFunDtnasc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    //txtCliId.setText(null);
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunTel.setText(null);
                    txtFunDtnasc.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void update() {
        String sql = "UPDATE FUNCIONARIOS SET cd_cpf_funcionario=?, nm_funcionario=?, tel_funcionario=?, dt_nasc_func=? WHERE cd_funcionario=?";
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtFunCpf.getText());
            pst.setString(2, txtFunNome.getText());
            pst.setString(3, txtFunTel.getText());
            pst.setString(4, txtFunDtnasc.getText());
            pst.setString(5, txtFunId.getText());

            //validando campos obrigatórios
            if ((txtFunId.getText().isEmpty()) || (txtFunCpf.getText().isEmpty()) || (txtFunNome.getText().isEmpty()) || (txtFunTel.getText().isEmpty())
                    || (txtFunDtnasc.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtFunId.setText(null);
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunTel.setText(null);
                    txtFunDtnasc.setText(null);
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
            String sql = "DELETE FROM FUNCIONARIOS WHERE cd_funcionario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtFunId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                    txtFunId.setText(null);
                    txtFunCpf.setText(null);
                    txtFunNome.setText(null);
                    txtFunTel.setText(null);
                    txtFunDtnasc.setText(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void setar_campos() {
        int setar = tblFuncionarios.getSelectedRow();
        //txtFunId.setText(tblFuncionarios.getModel().getValueAt(setar, 1).toString());
        txtFunCpf.setText(tblFuncionarios.getModel().getValueAt(setar, 1).toString());
        txtFunNome.setText(tblFuncionarios.getModel().getValueAt(setar, 2).toString());
        txtFunTel.setText(tblFuncionarios.getModel().getValueAt(setar, 3).toString());
        txtFunDtnasc.setText(tblFuncionarios.getModel().getValueAt(setar, 4).toString());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtFunPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        txtFunId = new javax.swing.JTextField();
        txtFunNome = new javax.swing.JTextField();
        txtFunTel = new javax.swing.JTextField();
        txtFunDtnasc = new javax.swing.JTextField();
        btnUsuDelete = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFunCpf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Funcionarios");
        setMinimumSize(new java.awt.Dimension(128, 36));
        setNormalBounds(new java.awt.Rectangle(0, 0, 104, 0));
        setPreferredSize(new java.awt.Dimension(740, 560));
        setVisible(true);
        getContentPane().setLayout(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel8.setText("Gerenciar Funcionarios");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 0, 370, 40);

        txtFunPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunPesquisarActionPerformed(evt);
            }
        });
        txtFunPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFunPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtFunPesquisar);
        txtFunPesquisar.setBounds(90, 50, 390, 40);

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFuncionarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFuncionarios);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 100, 440, 120);

        txtFunId.setBackground(new java.awt.Color(204, 204, 255));
        txtFunId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFunId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtFunId);
        txtFunId.setBounds(130, 270, 400, 20);

        txtFunNome.setBackground(new java.awt.Color(204, 204, 255));
        txtFunNome.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtFunNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFunNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtFunNome);
        txtFunNome.setBounds(170, 330, 360, 20);

        txtFunTel.setBackground(new java.awt.Color(204, 204, 255));
        txtFunTel.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtFunTel.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFunTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunTelActionPerformed(evt);
            }
        });
        getContentPane().add(txtFunTel);
        txtFunTel.setBounds(190, 360, 340, 20);

        txtFunDtnasc.setBackground(new java.awt.Color(204, 204, 255));
        txtFunDtnasc.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        txtFunDtnasc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFunDtnasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunDtnascActionPerformed(evt);
            }
        });
        getContentPane().add(txtFunDtnasc);
        txtFunDtnasc.setBounds(310, 390, 220, 20);

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

        btnUsuRead.setBackground(java.awt.Color.lightGray);
        btnUsuRead.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuRead);
        btnUsuRead.setBounds(540, 210, 70, 60);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/lupinha2.png"))); // NOI18N
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(490, 50, 40, 40);

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 270, 110, 30);

        jLabel6.setBackground(new java.awt.Color(204, 0, 204));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nome:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 330, 110, 30);

        jLabel7.setBackground(new java.awt.Color(204, 0, 204));
        jLabel7.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Telefone:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(80, 360, 110, 30);

        jLabel9.setBackground(new java.awt.Color(204, 0, 204));
        jLabel9.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Data de Nascimento:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(80, 390, 230, 30);

        jLabel10.setBackground(new java.awt.Color(204, 0, 204));
        jLabel10.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CPF:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(60, 300, 110, 30);

        txtFunCpf.setBackground(new java.awt.Color(204, 204, 255));
        txtFunCpf.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFunCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFunCpfActionPerformed(evt);
            }
        });
        getContentPane().add(txtFunCpf);
        txtFunCpf.setBounds(150, 300, 380, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 734, 522);

        getAccessibleContext().setAccessibleName("Funcionarios");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFunPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunPesquisarActionPerformed

    }//GEN-LAST:event_txtFunPesquisarActionPerformed

    private void txtFunPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFunPesquisarKeyReleased
        read();
    }//GEN-LAST:event_txtFunPesquisarKeyReleased

    private void tblFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionariosMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblFuncionariosMouseClicked

    private void txtFunIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFunIdActionPerformed

    private void txtFunNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFunNomeActionPerformed

    private void txtFunTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFunTelActionPerformed

    private void txtFunDtnascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunDtnascActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFunDtnascActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chamando o metodo logar
        delete();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // TODO add your handling code here:
        read();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void txtFunCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFunCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFunCpfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnPesquisar;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTextField txtFunCpf;
    private javax.swing.JTextField txtFunDtnasc;
    private javax.swing.JTextField txtFunId;
    private javax.swing.JTextField txtFunNome;
    private javax.swing.JTextField txtFunPesquisar;
    private javax.swing.JTextField txtFunTel;
    // End of variables declaration//GEN-END:variables
}
