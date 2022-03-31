/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.salao.dal;

import java.sql.*;

/**
 *
 * @author yukii
 */
public class ModuloConexao {
    //Metodo que conecta com o banco
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //a linha abaixo chama o driver importado
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando informações referentes ao banco
        String url = "jdbc:mysql://localhost:3306/dbsalao";
        String user = "root";
        String password = "mayla";
        //Estabelecendo conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            
            //System.out.println(e);
            return null;
        }
    }
}