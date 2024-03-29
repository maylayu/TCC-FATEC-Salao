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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author yukii
 */
public class Agendamentos extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;

    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;


            
       
    /**
     * Creates new form Agendamentos
     */
    public Agendamentos(){
        initComponents();
        
        conexao = ModuloConexao.conector();  
        pesquisar_servico();
        pesquisar_cliente();
        pesquisar_funcionario();
        
        try{
        //String sql = "SELECT cd_atendimento as Código, SERVICOS_cd_servico as Serviço, CLIENTES_cd_cliente as Cliente, FUNCIONARIOS_cd_funcionario as Funcionário, dt_atendimento as Data FROM ORDEMSERVICO";
        String sql = "SELECT o.cd_atendimento as Código, s.nm_servico as Serviço, c.nm_cliente as Cliente, f.nm_funcionario as Funcionário, date_format(o.dt_atendimento, '%d/%m/%Y') as Data, time_format(o.hr_atendimento, '%H:%i') as Hora "
                + "FROM ORDEMSERVICO AS o JOIN SERVICOS AS s on o.SERVICOS_cd_servico = s.cd_servico "
                + "JOIN CLIENTES AS c ON o.CLIENTES_cd_cliente = c.cd_cliente "
                + "JOIN FUNCIONARIOS AS f ON o.FUNCIONARIOS_cd_funcionario = f.cd_funcionario;";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        tblAgendamento.setModel(DbUtils.resultSetToTableModel(rs));
        
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        } 
        
            
    }
    private void read(){
        conexao = ModuloConexao.conector();  
        pesquisar_servico();
        pesquisar_cliente();
        pesquisar_funcionario();
        
        try{
        //String sql = "SELECT cd_atendimento as Código, SERVICOS_cd_servico as Serviço, CLIENTES_cd_cliente as Cliente, FUNCIONARIOS_cd_funcionario as Funcionário, dt_atendimento as Data FROM ORDEMSERVICO";
        String sql = "SELECT o.cd_atendimento as Código, s.nm_servico as Serviço, c.nm_cliente as Cliente, f.nm_funcionario as Funcionário, date_format(o.dt_atendimento, '%d/%m/%Y') as Data, time_format(o.hr_atendimento, '%H:%i') as Hora "
                + "FROM ORDEMSERVICO AS o JOIN SERVICOS AS s on o.SERVICOS_cd_servico = s.cd_servico "
                + "JOIN CLIENTES AS c ON o.CLIENTES_cd_cliente = c.cd_cliente "
                + "JOIN FUNCIONARIOS AS f ON o.FUNCIONARIOS_cd_funcionario = f.cd_funcionario;";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        tblAgendamento.setModel(DbUtils.resultSetToTableModel(rs));
        
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        } 
    }
    
    private void pesquisar_servico(){
        conexao = ModuloConexao.conector();
        String sql = "select cd_servico, nm_servico from SERVICOS";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){
            String servico = rs.getString("nm_servico");
            cboAgeSer.addItem(servico); 

            }        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }         
    }
    
    private void pesquisar_cliente(){
        conexao = ModuloConexao.conector();
        String sql = "select cd_cliente, nm_cliente from CLIENTES";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){
            String cliente = rs.getString("nm_cliente");
            cboAgeCli.addItem(cliente);  
            }        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }        
    }
    
    private void pesquisar_funcionario(){
        conexao = ModuloConexao.conector();        
        String sql = "select cd_funcionario, nm_funcionario from FUNCIONARIOS";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
                    
            while(rs.next()){
                String funcionario = rs.getString("nm_funcionario");
                cboAgeFun.addItem(funcionario);
            }          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }       
    }
    
    
        
    private void pesquisar_combo(){
        conexao = ModuloConexao.conector();
        String sql = "select cd_servico, nm_servico from SERVICOS";
        String sql2 = "select cd_cliente, nm_cliente from CLIENTES";
        String sql3 = "select cd_funcionario, nm_funcionario from FUNCIONARIOS";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            pst2 = conexao.prepareStatement(sql2);
            rs2 = pst.executeQuery();
            pst3 = conexao.prepareStatement(sql3);
            rs3 = pst.executeQuery();

            while(rs.next()){
            String servico = rs.getString("nm_servico");
            cboAgeSer.addItem(servico);
            }   
            while(rs2.next()){
                String cliente = rs2.getString("nm_cliente");
                cboAgeCli.addItem(cliente);
            }
            while(rs3.next()){
                String funcionario = rs3.getString("nm_funcionario");
                cboAgeFun.addItem(funcionario);
            }
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }       
    }
            	
	
// essa rotina retorna o id
    private int GetIdServico(String nm_servico){
      conexao = ModuloConexao.conector();   
      int cd_servico = 0;
    
        try{
            //Connection con =  ModuloConexao.conector();
            String sql = "select cd_servico from SERVICOS where nm_servico=?";
            PreparedStatement stm = conexao.prepareStatement(sql);
             stm.setString(1, nm_servico);               
            ResultSet rs = stm.executeQuery();            
            while(rs.next()){             
                cd_servico = rs.getInt("cd_servico");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }   
        return cd_servico;	
        //int v = GetIdServico(cboAgeSer.getSelectedItem().toString());
    }

    private int GetIdCliente(String nm_cliente){
      conexao = ModuloConexao.conector();   
      int cd_cliente = 0;
    
        try{
            //Connection con =  ModuloConexao.conector();
            String sql = "select cd_cliente from CLIENTES where nm_cliente=?";
            PreparedStatement stm = conexao.prepareStatement(sql);
             stm.setString(1, nm_cliente);               
            ResultSet rs = stm.executeQuery();            
            while(rs.next()){             
                cd_cliente = rs.getInt("cd_cliente");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }   
        return cd_cliente;	
        //int v = GetIdServico(cboAgeSer.getSelectedItem().toString());
    }
	// Exemplo de chamada do getId 
    private int GetIdFuncionario(String nm_funcionario){
      conexao = ModuloConexao.conector();   
      int cd_funcionario = 0;
    
        try{
            //Connection con =  ModuloConexao.conector();
            String sql = "select cd_funcionario from FUNCIONARIOS where nm_funcionario=?";
            PreparedStatement stm = conexao.prepareStatement(sql);
             stm.setString(1, nm_funcionario);               
            ResultSet rs = stm.executeQuery();            
            while(rs.next()){             
                cd_funcionario = rs.getInt("cd_funcionario");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }   
        return cd_funcionario;	
        //int v = GetIdServico(cboAgeSer.getSelectedItem().toString());
    }
    
    
    
    private void create() {
        conexao = ModuloConexao.conector();   

        String sql = "INSERT INTO ORDEMSERVICO(SERVICOS_cd_servico, CLIENTES_cd_cliente, FUNCIONARIOS_cd_funcionario, dt_atendimento, hr_atendimento)VALUES (?, ?, ?, ?, ?)";
        String nm_servico = cboAgeSer.getSelectedItem().toString();
        String nm_cliente = cboAgeCli.getSelectedItem().toString();
        String nm_funcionario = cboAgeFun.getSelectedItem().toString();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, GetIdServico(nm_servico));
            pst.setInt(2, GetIdCliente(nm_cliente));
            pst.setInt(3, GetIdFuncionario(nm_funcionario));
            
            String dta = txtAgeData.getText();
            LocalDate id = LocalDate.parse(dta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            java.sql.Date data = java.sql.Date.valueOf(id);
            pst.setDate(4, data);
            
            String hora = txtAgeHora.getText();
            LocalTime id2 = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            java.sql.Time hor = java.sql.Time.valueOf(id2);
            pst.setTime(5, hor);
            //pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

            //validando campos obrigatórios
            if ((cboAgeSer.getSelectedItem().toString().isEmpty()) || (cboAgeCli.getSelectedItem().toString().isEmpty())
                    || (cboAgeFun.getSelectedItem().toString().isEmpty()) || (txtAgeData.getText().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Esse confirma se alguma coisa foi inserida mesmo
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Agendamento criado com sucesso!");
                    txtAgeId.setText(null);
                    cboAgeSer.setSelectedItem(null);
                    cboAgeCli.setSelectedItem(null);
                    cboAgeFun.setSelectedItem(null);
                    txtAgeData.setText(null);
                    txtAgeHora.setText(null);
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }

    }
    
    private void update() {
        String sql = "UPDATE ORDEMSERVICO SET SERVICOS_cd_servico=?, CLIENTES_cd_cliente=?, FUNCIONARIOS_cd_funcionario=?, dt_atendimento=?, hr_atendimento=? WHERE cd_atendimento=?";
        String nm_servico = cboAgeSer.getSelectedItem().toString();
        String nm_cliente = cboAgeCli.getSelectedItem().toString();
        String nm_funcionario = cboAgeFun.getSelectedItem().toString();


        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, GetIdServico(nm_servico));
            pst.setInt(2, GetIdCliente(nm_cliente));
            pst.setInt(3, GetIdFuncionario(nm_funcionario));
            
            String dta = txtAgeData.getText();
            LocalDate id = LocalDate.parse(dta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            java.sql.Date data = java.sql.Date.valueOf(id);
            pst.setDate(4, data);
            
            String hora = txtAgeHora.getText();
            LocalTime id2 = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            java.sql.Time hor = java.sql.Time.valueOf(id2);
            pst.setTime(5, hor);
            
            pst.setString(6, txtAgeId.getText());


           
            //validando campos obrigatórios
            if ((cboAgeSer.getSelectedItem().toString().isEmpty()) || (cboAgeCli.getSelectedItem().toString().isEmpty())
                    || (cboAgeFun.getSelectedItem().toString().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                //abaixo irá atualizar a tabela com os dados do formulario
                int adicionado = pst.executeUpdate();
                //Dessa vez confirma a alteração 
                if (adicionado > 0) {
                    //
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    txtAgeId.setText(null);
                    cboAgeSer.setSelectedItem(null);
                    cboAgeCli.setSelectedItem(null);
                    cboAgeFun.setSelectedItem(null);
                    txtAgeData.setText(null);
                    txtAgeHora.setText(null);

                    
                    //cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");

        }

    }
    
    private void delete() {
        String sql = "DELETE FROM ORDEMSERVICO WHERE cd_atendimento=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAgeId.getText());


            if ((cboAgeSer.getSelectedItem().toString().isEmpty()) || (cboAgeCli.getSelectedItem().toString().isEmpty())
                    || (cboAgeFun.getSelectedItem().toString().isEmpty()) || (txtAgeData.getText().isEmpty()) || (txtAgeData.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");

            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Quer mesmo excluir esse agendamento?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                    txtAgeId.setText(null);
                    cboAgeSer.setSelectedItem(null);
                    cboAgeCli.setSelectedItem(null);
                    cboAgeFun.setSelectedItem(null);
                    txtAgeData.setText(null);
                    txtAgeHora.setText(null);

                    }
                } else {
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com internet.");
        }

    }

    public void setar_campos() {
        int setar = tblAgendamento.getSelectedRow();
        txtAgeId.setText(tblAgendamento.getModel().getValueAt(setar, 0).toString());
        cboAgeSer.setSelectedItem(tblAgendamento.getModel().getValueAt(setar, 1).toString());
        cboAgeCli.setSelectedItem(tblAgendamento.getModel().getValueAt(setar, 2).toString());
        cboAgeFun.setSelectedItem(tblAgendamento.getModel().getValueAt(setar, 3).toString());
        txtAgeData.setText(tblAgendamento.getModel().getValueAt(setar, 4).toString());
        txtAgeHora.setText(tblAgendamento.getModel().getValueAt(setar, 5).toString());
        
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgendamento = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtAgeId = new javax.swing.JTextField();
        btnAgeDelete = new javax.swing.JButton();
        btnAgeUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboAgeFun = new javax.swing.JComboBox<>();
        cboAgeCli = new javax.swing.JComboBox<>();
        cboAgeSer = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAgeData = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAgeHora = new javax.swing.JFormattedTextField();
        btnPesquisar = new javax.swing.JLabel();
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
        jLabel7.setText("Hora:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(270, 400, 70, 30);

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

        jLabel5.setBackground(new java.awt.Color(204, 0, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 1));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Código:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 240, 110, 30);

        tblAgendamento = new javax.swing.JTable();
        tblAgendamento = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblAgendamento.setBackground(new java.awt.Color(204, 204, 255));
        tblAgendamento.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tblAgendamento.setForeground(new java.awt.Color(0, 0, 1));
        tblAgendamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Serviço", "Cliente", "Funcionário", "Data", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAgendamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblAgendamento.getTableHeader().setResizingAllowed(false);
        tblAgendamento.getTableHeader().setReorderingAllowed(false);
        tblAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAgendamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAgendamento);
        if (tblAgendamento.getColumnModel().getColumnCount() > 0) {
            tblAgendamento.getColumnModel().getColumn(0).setResizable(false);
            tblAgendamento.getColumnModel().getColumn(1).setResizable(false);
            tblAgendamento.getColumnModel().getColumn(2).setResizable(false);
            tblAgendamento.getColumnModel().getColumn(3).setResizable(false);
            tblAgendamento.getColumnModel().getColumn(4).setResizable(false);
            tblAgendamento.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 670, 150);

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 1));
        jLabel8.setText("Agendar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 10, 130, 40);

        txtAgeId.setEditable(false);
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
        txtAgeId.setBounds(160, 240, 40, 30);

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
        jLabel6.setBounds(70, 320, 75, 40);

        cboAgeFun.setBackground(new java.awt.Color(204, 204, 255));
        cboAgeFun.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboAgeFun.setForeground(new java.awt.Color(51, 51, 51));
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
        cboAgeCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAgeCliActionPerformed(evt);
            }
        });
        getContentPane().add(cboAgeCli);
        cboAgeCli.setBounds(160, 320, 260, 30);

        cboAgeSer.setBackground(new java.awt.Color(204, 204, 255));
        cboAgeSer.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        cboAgeSer.setForeground(new java.awt.Color(51, 51, 51));
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
        cboAgeSer.setBounds(160, 280, 260, 30);

        jLabel9.setBackground(new java.awt.Color(204, 0, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 1));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Funcionário:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 360, 110, 30);

        jLabel10.setBackground(new java.awt.Color(204, 0, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 1));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Serviço:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(50, 280, 110, 30);

        txtAgeData.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeData.setForeground(new java.awt.Color(0, 0, 1));
        try {
            txtAgeData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtAgeData.setText("");
        txtAgeData.setToolTipText("");
        txtAgeData.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAgeData.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtAgeData.setSelectionColor(new java.awt.Color(0, 0, 153));
        txtAgeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeDataActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeData);
        txtAgeData.setBounds(160, 400, 110, 30);

        jLabel11.setBackground(new java.awt.Color(204, 0, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 1));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Data:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(70, 400, 70, 30);

        txtAgeHora.setBackground(new java.awt.Color(204, 204, 255));
        txtAgeHora.setForeground(new java.awt.Color(0, 0, 1));
        try {
            txtAgeHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtAgeHora.setToolTipText("");
        txtAgeHora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAgeHora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAgeHora.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtAgeHora.setSelectionColor(new java.awt.Color(0, 0, 153));
        txtAgeHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeHoraActionPerformed(evt);
            }
        });
        getContentPane().add(txtAgeHora);
        txtAgeHora.setBounds(340, 400, 80, 30);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/lupinha2.png"))); // NOI18N
        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(330, 10, 40, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/salao/icones/backPages.jpg"))); // NOI18N
        jLabel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel1ComponentShown(evt);
            }
        });
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

    private void btnAgeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgeDeleteActionPerformed
        // chamando o metodo logar
        delete();
    }//GEN-LAST:event_btnAgeDeleteActionPerformed

    private void btnAgeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgeUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnAgeUpdateActionPerformed

    private void cboAgeFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeFunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAgeFunActionPerformed

    private void cboAgeCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAgeCliActionPerformed

    private void cboAgeSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgeSerActionPerformed
        
        
    }//GEN-LAST:event_cboAgeSerActionPerformed

    private void cboAgeSerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAgeSerKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboAgeSerKeyReleased

    private void jLabel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel1ComponentShown
        // TODO add your handling code here:
        txtAgeId.setText(null);
        cboAgeSer.setSelectedItem(null);
        cboAgeCli.setSelectedItem(null);
        cboAgeFun.setSelectedItem(null);
        txtAgeData.setText(null);
    }//GEN-LAST:event_jLabel1ComponentShown

    private void txtAgeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeDataActionPerformed

    private void txtAgeHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeHoraActionPerformed

    private void btnPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseClicked
        read();
    }//GEN-LAST:event_btnPesquisarMouseClicked


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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAgendamento;
    private javax.swing.JFormattedTextField txtAgeData;
    private javax.swing.JFormattedTextField txtAgeHora;
    private javax.swing.JTextField txtAgeId;
    // End of variables declaration//GEN-END:variables
}
