package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Clima Bagual
 */
public class Conexao {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/" + "banco_clima";
    private final String usuario = "root";
    private final String senha = "";// "" (VAZIO)
    private Connection conexao;
    
       
    public Conexao(){
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);

            if (e instanceof ClassNotFoundException) {
                JOptionPane.showMessageDialog(
                    null,
                    "Driver do banco de dados não encontrado.\nVerifique se o driver JDBC está corretamente configurado.",
                    "Erro de Driver",
                    JOptionPane.ERROR_MESSAGE
                );
            } else if (e instanceof SQLException) {
                JOptionPane.showMessageDialog(
                    null,
                    "Erro ao conectar com o banco de dados.\nVerifique URL, usuário, senha ou o estado do servidor.",
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }        
    
    }
    
    public Connection getConexao() throws SQLException {
        return conexao;
    }
    
    
}
