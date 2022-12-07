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
public class TelaServico extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaServico
     */
    public TelaServico() {
        initComponents();
        conexao = ModuloConexao.conector();

    }
    
    private void read() {
        String sql = "SELECT cd_servico as Código, nm_servico as Nome, vl_preco as Preço from SERVICOS where nm_servico like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSerPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblServicos.setModel(DbUtils.resultSetToTableModel(rs));

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
        String sql = "INSERT INTO SERVICOS(nm_servico, vl_preco)VALUES (?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSerNome.getText());
            pst.setString(2, txtSerValor.getText());
            

            //validando campos obrigatórios
            //txtCliId.getText().isEmpty())
            if ((txtSerNome.getText().isEmpty()) || (txtSerValor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
                    //txtCliId.setText(null);
                    txtSerNome.setText(null);
                    txtSerValor.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void update() {
        String sql = "UPDATE SERVICOS SET nm_servico=?, vl_preco=? WHERE cd_servico=?";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSerNome.getText());
            pst.setString(2, txtSerValor.getText());
            pst.setString(3, txtSerId.getText());
           
            //validando campos obrigatórios
            if ((txtSerId.getText().isEmpty()) || (txtSerNome.getText().isEmpty()) || (txtSerValor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtSerId.setText(null);
                    txtSerNome.setText(null);
                    txtSerValor.setText(null);
                    
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void delete() {
        String sql = "DELETE FROM SERVICOS WHERE cd_servico=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSerId.getText());

            if ((txtSerId.getText().isEmpty()) || (txtSerNome.getText().isEmpty()) || (txtSerValor.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Quer mesmo excluir esse cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        txtSerId.setText(null);
                        txtSerNome.setText(null);
                        txtSerValor.setText(null);
                        }
                } else {

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setar_campos() {
        int setar = tblServicos.getSelectedRow();
        txtSerId.setText(tblServicos.getModel().getValueAt(setar, 0).toString());
        txtSerNome.setText(tblServicos.getModel().getValueAt(setar, 1).toString());
        txtSerValor.setText(tblServicos.getModel().getValueAt(setar, 2).toString());
        
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
        txtSerNome = new javax.swing.JTextField();
        btnSerDelete = new javax.swing.JButton();
        txtSerId = new javax.swing.JTextField();
        txtSerPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnSerUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnSerCreate = new javax.swing.JButton();
        txtSerValor = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Serviços");
        setMinimumSize(new java.awt.Dimension(128, 36));
        setPreferredSize(new java.awt.Dimension(740, 560));
        getContentPane().setLayout(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 1));
        jLabel8.setText("Serviços");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(267, 6, 280, 40);

        txtSerNome.setBackground(new java.awt.Color(204, 204, 255));
        txtSerNome.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtSerNome.setForeground(new java.awt.Color(0, 0, 1));
        txtSerNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSerNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtSerNome);
        txtSerNome.setBounds(169, 311, 370, 30);

        btnSerDelete.setBackground(java.awt.Color.lightGray);
        btnSerDelete.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnSerDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/delete.png"))); // NOI18N
        btnSerDelete.setToolTipText("Remover!");
        btnSerDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnSerDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSerDelete.setPreferredSize(new java.awt.Dimension(70, 70));
        btnSerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSerDelete);
        btnSerDelete.setBounds(386, 440, 70, 60);

        txtSerId.setEditable(false);
        txtSerId.setBackground(new java.awt.Color(204, 204, 255));
        txtSerId.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        txtSerId.setForeground(new java.awt.Color(0, 0, 1));
        txtSerId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtSerId);
        txtSerId.setBounds(136, 270, 40, 30);

        txtSerPesquisar.setBackground(new java.awt.Color(204, 204, 255));
        txtSerPesquisar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        txtSerPesquisar.setForeground(new java.awt.Color(0, 0, 1));
        txtSerPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerPesquisarActionPerformed(evt);
            }
        });
        txtSerPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtSerPesquisar);
        txtSerPesquisar.setBounds(96, 50, 390, 40);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/lupinha2.png"))); // NOI18N
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(496, 50, 40, 40);

        jLabel7.setBackground(new java.awt.Color(204, 0, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 1));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Valor:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(88, 353, 62, 30);

        tblServicos = new javax.swing.JTable();
        tblServicos = new javax.swing.JTable(){
            public boolean isCellEditable(
                int rowIndex, int colIndex){
                return false;
            }
        };
        tblServicos.setBackground(new java.awt.Color(204, 204, 255));
        tblServicos.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tblServicos.setForeground(new java.awt.Color(0, 0, 1));
        tblServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblServicos.getTableHeader().setReorderingAllowed(false);
        tblServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicos);
        if (tblServicos.getColumnModel().getColumnCount() > 0) {
            tblServicos.getColumnModel().getColumn(0).setResizable(false);
            tblServicos.getColumnModel().getColumn(1).setResizable(false);
            tblServicos.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(96, 100, 440, 120);

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 1));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(56, 270, 110, 30);

        btnSerUpdate.setBackground(java.awt.Color.lightGray);
        btnSerUpdate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnSerUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/update.png"))); // NOI18N
        btnSerUpdate.setToolTipText("Alterar");
        btnSerUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnSerUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSerUpdate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSerUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnSerUpdate);
        btnSerUpdate.setBounds(286, 440, 70, 60);

        jLabel6.setBackground(new java.awt.Color(204, 0, 204));
        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 1));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nome:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(88, 310, 75, 30);

        btnSerCreate.setBackground(java.awt.Color.lightGray);
        btnSerCreate.setFont(new java.awt.Font("Corbel Light", 1, 13)); // NOI18N
        btnSerCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/create.png"))); // NOI18N
        btnSerCreate.setToolTipText("Adicionar!");
        btnSerCreate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 3, true));
        btnSerCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSerCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSerCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnSerCreate);
        btnSerCreate.setBounds(186, 440, 70, 60);

        txtSerValor.setBackground(new java.awt.Color(204, 204, 255));
        try {
            txtSerValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSerValor.setToolTipText("");
        txtSerValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSerValor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSerValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerValorActionPerformed(evt);
            }
        });
        getContentPane().add(txtSerValor);
        txtSerValor.setBounds(170, 350, 370, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -30, 734, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerDeleteActionPerformed
        // chamando o metodo logar
        delete();
    }//GEN-LAST:event_btnSerDeleteActionPerformed

    private void txtSerNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerNomeActionPerformed

    private void txtSerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerIdActionPerformed

    private void txtSerPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerPesquisarActionPerformed

    }//GEN-LAST:event_txtSerPesquisarActionPerformed

    private void txtSerPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerPesquisarKeyReleased
        read();
    }//GEN-LAST:event_txtSerPesquisarKeyReleased

    private void tblServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServicosMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblServicosMouseClicked

    private void btnSerUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSerUpdateActionPerformed

    private void btnSerCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerCreateActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnSerCreateActionPerformed

    private void txtSerValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerValorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnPesquisar;
    private javax.swing.JButton btnSerCreate;
    private javax.swing.JButton btnSerDelete;
    private javax.swing.JButton btnSerUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicos;
    private javax.swing.JTextField txtSerId;
    private javax.swing.JTextField txtSerNome;
    private javax.swing.JTextField txtSerPesquisar;
    private javax.swing.JFormattedTextField txtSerValor;
    // End of variables declaration//GEN-END:variables
}
