 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mainformatica.dal;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Usuario
 */
public class ModuloConexao {
    //metodo responsavem em fazer a conexão com o Banco de Dados
    public static Connection conector() {
         java.sql.Connection conexao = null;
        //A linha abaixo chama o driver que eu importei aqui na biblioteca
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando informações referente ao Banco
        String url = "jdbc:mysql://127.0.0.1/dbloja";
        String user = "loja";
        String password = "123";
        // Estabelecendo a conexao com o Banco
        try {
            Class.forName(driver); // Está linha de comando vai excutar o arquivos do driver
            conexao = DriverManager.getConnection(url, user, password); // Conexao é a variável que foi criada utilizando os parametros url, user e password, que seria o Caminho o Usuário e a senha
            return conexao; // Aqui ele vai armazenar os dados inseridos do Caminho, Usuário e senha e vai retornar a conexao
        } catch (Exception e) {
            //a linha serve de apoio para esclarecer o erro
            //System.out.println("e");
            return null;
        }
    }

    public static Connection connector() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
