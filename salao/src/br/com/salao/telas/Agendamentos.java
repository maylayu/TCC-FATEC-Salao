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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import br.com.salao.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author yukii
 */
public class Agendamentos extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
            
       
    /**
     * Creates new form Agendamentos
     */
    public Agendamentos() {
        initComponents();
        conexao = ModuloConexao.conector();
        String sql = "select cd_servico, nm_servico from SERVICOS";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            //pst.setString(1, txtAgePesquisar.getText() + "%");
            //cboAgeSer.setModel((DbUtils.resultSetToNestedList(rs)));
            cboAgeSer.addItem(rs.getObject(2));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }
    private void pesquisar_servico(){
        String sql = "select id_servico, nm_servico from SERVICOS";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){
            cboAgeSer.addItem(rs.getObject(2));

            }        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
           
            
    }
    
    public void read(){
    String sql = "select * from ORDEMSERVICO where SERVICOS_cd_servico=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAgePesquisar.getText() + "%");
            rs = pst.executeQuery();
            
            tblAgendamento.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            /*
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAgeId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtAgeSer.setText(rs.getString(2));
                txtAgeCli.setText(rs.getString(3));
                txtAgeFun.setText(rs.getString(4));
                txtAgeData.setText(rs.getString(5));

                //combo box
                cboAgeSer.setSelectedItem(rs.getString(5));

                //ifel + ctrl+space
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                //as linhas abaixo limpam os campos
                txtAgeSer.setText(null);
                txtAgeCli.setText(null);
                txtAgeFun.setText(null);
                txtAgeData.setText(null);
                cboAgeSer.setSelectedItem(rs.getString(5));
                

                                //combo box
                //cboUsuPerfil.setSelectedItem(null);
            }*/

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void create() {
        String sql = "INSERT INTO ORDEMSERVICO(SERVICOS_cd_servico, CLIENTES_cd_cliente, FUNCIONARIOS_cd_funcionario, dt_atendimento)VALUES (?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAgeSer.getText());
            pst.setString(2, txtAgeCli.getText());
            pst.setString(3, txtAgeFun.getText());
            pst.setString(4, txtAgeData.getText());

            //pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

            //validando campos obrigatórios
            if ((txtAgeSer.getText().isEmpty()) || (txtAgeCli.getText().isEmpty())
                    || (txtAgeFun.getText().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    txtAgeId.setText(null);
                    txtAgeSer.setText(null);
                    txtAgeCli.setText(null);
                    txtAgeFun.setText(null);
                    txtAgeData.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void update() {
        String sql = "UPDATE ORDEMSERVICO SET SERVICOS_cd_servico=?, CLIENTES_cd_cliente=?, FUNCIONARIOS_cd_funcionario=?, dt_atendimento=? WHERE cd_atendimento=?";
        try {

            pst = conexao.prepareStatement(sql);
            //pst.setString(1, cboAgeSer.getSelectedText());
            //pst.setString(2, cboAgeCli.getSelectedText());
            //pst.setString(3, cboAgeFun.getSelectedText());
            pst.setString(4, txtAgeData.getText());
            pst.setString(5, txtAgeId.getText());


           
            //validando campos obrigatórios
            if ((txtAgeId.getText().isEmpty()) || (txtAgeSer.getText().isEmpty()) || (txtAgeCli.getText().isEmpty())
                    || (txtAgeFun.getText().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtAgeId.setText(null);
                    txtAgeSer.setText(null);
                    txtAgeCli.setText(null);
                    txtAgeFun.setText(null);
                    txtAgeData.setText(null);

                    
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }
    
    private void delete() {
        String sql = "DELETE FROM ORDEMSERVICO WHERE cd_atendimento=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAgeId.getText());

            if ((txtAgeSer.getText().isEmpty()) || (txtAgeCli.getText().isEmpty()) || (txtAgeFun.getText().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Quer mesmo excluir esse cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        txtAgeId.setText(null);
                        txtAgeSer.setText(null);
                        txtAgeCli.setText(null);
                        txtAgeFun.setText(null);
                        txtAgeData.setText(null);

                    }
                } else {

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setar_campos() {
        int setar = tblAgendamento.getSelectedRow();
        //txtFunId.setText(tblFuncionarios.getModel().getValueAt(setar, 1).toString());
        txtAgeSer.setText(tblAgendamento.getModel().getValueAt(setar, 1).toString());
        txtAgeCli.setText(tblAgendamento.getModel().getValueAt(setar, 2).toString());
        txtAgeFun.setText(tblAgendamento.getModel().getValueAt(setar, 3).toString());
        txtAgeData.setText(tblAgendamento.getModel().getValueAt(setar, 3).toString());


        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        btnAgeCreate = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgendamento = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtAgeId = new javax.swing.JTextField();
        txtAgePesquisar = new javax.swing.JTextField();
        btnAgeDelete = new javax.swing.JButton();
        btnAgeUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboAgeFun = new javax.swing.JComboBox<>();
        cboAgeCli = new javax.swing.JComboBox<>();
        cboAgeSer = new javax.swing.JComboBox<>();
        txtAgeFun = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAgeData = new javax.swing.JTextField();
        txtAgeSer = new javax.swing.JTextField();
        txtAgeCli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Agendamentos");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(128, 36));
        setPreferredSize(new java.awt.Dimension(740, 560));
        getContentPane().setLayout(null);

        jLabel7.setBackground(new java.awt.Color(204, 0, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 1));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Data:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(60, 390, 70, 30);

        btnAgeCreate.setBackground(java.awt.Color.lightGray);
        btnAgeCreate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnAgeCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/create.png"))); // NOI18N
        btnAgeCreate.setToolTipText("Adicionar!");
        btnAgeCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnAgeCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgeCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAgeCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgeCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgeCreate);
        btnAgeCreate.setBounds(190, 450, 70, 60);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/lupinha2.png"))); // NOI18N
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(496, 50, 40, 40);

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 1));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Código:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 240, 110, 30);

        tblAgendamento.setBackground(new java.awt.Color(204, 204, 255));
        tblAgendamento.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tblAgendamento.setForeground(new java.awt.Color(0, 0, 1));
        tblAgendamento.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAgendamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAgendamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAgendamento);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 90, 390, 120);

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 1));
        jLabel8.setText("Agendar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 0, 280, 40);

        txtAgeId.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeId.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtAgeId.setForeground(new java.awt.Color(0, 0, 1));
        txtAgeId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeId);
        txtAgeId.setBounds(120, 240, 90, 30);

        txtAgePesquisar.setBackground(new java.awt.Color(204, 204, 255));
        txtAgePesquisar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtAgePesquisar.setForeground(new java.awt.Color(0, 0, 1));
        txtAgePesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgePesquisarActionPerformed(evt);
            }
        });
        txtAgePesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAgePesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtAgePesquisar);
        txtAgePesquisar.setBounds(40, 40, 390, 40);

        btnAgeDelete.setBackground(java.awt.Color.lightGray);
        btnAgeDelete.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnAgeDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/delete.png"))); // NOI18N
        btnAgeDelete.setToolTipText("Remover!");
        btnAgeDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnAgeDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgeDelete.setPreferredSize(new java.awt.Dimension(70, 70));
        btnAgeDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgeDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgeDelete);
        btnAgeDelete.setBounds(390, 450, 70, 60);

        btnAgeUpdate.setBackground(java.awt.Color.lightGray);
        btnAgeUpdate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnAgeUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/update.png"))); // NOI18N
        btnAgeUpdate.setToolTipText("Alterar");
        btnAgeUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnAgeUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgeUpdate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnAgeUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgeUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgeUpdate);
        btnAgeUpdate.setBounds(290, 450, 70, 60);

        jLabel6.setBackground(new java.awt.Color(204, 0, 204));
        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 1));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cliente:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(60, 330, 75, 30);

        cboAgeFun.setBackground(new java.awt.Color(204, 204, 255));
        cboAgeFun.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboAgeFun.setForeground(new java.awt.Color(51, 51, 51));
        cboAgeFun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionário" }));
        cboAgeFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAgeFunActionPerformed(evt);
            }
        });
        getContentPane().add(cboAgeFun);
        cboAgeFun.setBounds(160, 360, 260, 30);

        cboAgeCli.setBackground(new java.awt.Color(204, 204, 255));
        cboAgeCli.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboAgeCli.setForeground(new java.awt.Color(51, 51, 51));
        cboAgeCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes" }));
        cboAgeCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAgeCliActionPerformed(evt);
            }
        });
        getContentPane().add(cboAgeCli);
        cboAgeCli.setBounds(160, 330, 260, 30);

        cboAgeSer.setBackground(new java.awt.Color(204, 204, 255));
        cboAgeSer.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboAgeSer.setForeground(new java.awt.Color(51, 51, 51));
        cboAgeSer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serviços" }));
        cboAgeSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAgeSerActionPerformed(evt);
            }
        });
        cboAgeSer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboAgeSerKeyReleased(evt);
            }
        });
        getContentPane().add(cboAgeSer);
        cboAgeSer.setBounds(160, 300, 260, 30);

        txtAgeFun.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeFun.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtAgeFun.setForeground(new java.awt.Color(0, 0, 1));
        txtAgeFun.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeFunActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeFun);
        txtAgeFun.setBounds(230, 220, 400, 30);

        jLabel9.setBackground(new java.awt.Color(204, 0, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 1));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Funcionária:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(40, 360, 110, 30);

        txtAgeData.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeData.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtAgeData.setForeground(new java.awt.Color(0, 0, 1));
        txtAgeData.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeDataActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeData);
        txtAgeData.setBounds(160, 390, 260, 30);

        txtAgeSer.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeSer.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtAgeSer.setForeground(new java.awt.Color(0, 0, 1));
        txtAgeSer.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeSerActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeSer);
        txtAgeSer.setBounds(230, 240, 400, 30);

        txtAgeCli.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeCli.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtAgeCli.setForeground(new java.awt.Color(0, 0, 1));
        txtAgeCli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeCliActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeCli);
        txtAgeCli.setBounds(230, 270, 400, 30);

        jLabel10.setBackground(new java.awt.Color(204, 0, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 1));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Serviço:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 300, 110, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -30, 734, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgeCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgeCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnAgeCreateActionPerformed

    private void tblAgendamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAgendamentoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblAgendamentoMouseClicked

    private void txtAgeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeIdActionPerformed

    private void txtAgePesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgePesquisarActionPerformed
        read();
    }//GEN-LAST:event_txtAgePesquisarActionPerformed

    private void txtAgePesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgePesquisarKeyReleased
        read();
    }//GEN-LAST:event_txtAgePesquisarKeyReleased

    private void btnAgeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgeDeleteActionPerformed
        // chamando o metodo logar
        delete();
    }//GEN-LAST:event_btnAgeDeleteActionPerformed

    private void btnAgeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgeUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnAgeUpdateActionPerformed

    private void txtAgeFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeFunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeFunActionPerformed

    private void txtAgeCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeCliActionPerformed

    private void txtAgeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeDataActionPerformed

    private void txtAgeSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeSerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeSerActionPerformed

    private void cboAgeFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeFunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAgeFunActionPerformed

    private void cboAgeCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAgeCliActionPerformed

    private void cboAgeSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeSerActionPerformed
        // TODO add your handling code here:
        String sqll = "select nm_servico FROM ORDEMSERVICO";
        String sql = "select nm_servico from SERVICOS";
        try {
            pst = conexao.prepareStatement(sql);
            //pst.setString(1, txtAgePesquisar.getText() + "%");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            pst = conexao.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.next()) {
                //Object nextElement = rs.next().nextElement();
                cboAgeSer.addItem(rs.getString(sqll));
                cboAgeSer.addItem(sql);

            }
            cboAgeSer.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(Agendamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
            //pst.setString(1, cboAgeSer.getText()+ "%");
            //cboAgeSer.setModel(pesquisar_servico());
        
    }//GEN-LAST:event_cboAgeSerActionPerformed

    private void cboAgeSerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAgeSerKeyReleased
        // TODO add your handling code here:
        pesquisar_servico();
        
    }//GEN-LAST:event_cboAgeSerKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgeCreate;
    private javax.swing.JButton btnAgeDelete;
    private javax.swing.JButton btnAgeUpdate;
    private javax.swing.JLabel btnPesquisar;
    private javax.swing.JComboBox<String> cboAgeCli;
    private javax.swing.JComboBox<String> cboAgeFun;
    private javax.swing.JComboBox<Object> cboAgeSer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAgendamento;
    private javax.swing.JTextField txtAgeCli;
    private javax.swing.JTextField txtAgeData;
    private javax.swing.JTextField txtAgeFun;
    private javax.swing.JTextField txtAgeId;
    private javax.swing.JTextField txtAgePesquisar;
    private javax.swing.JTextField txtAgeSer;
    // End of variables declaration//GEN-END:variables
}
