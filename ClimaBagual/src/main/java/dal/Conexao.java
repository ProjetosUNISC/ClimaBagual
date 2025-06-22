package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/" + "banco_clima";
    private final String usuario = "root";
    private final String senha = "";//aqui no lab, "" (VAZIO)
    private Connection conexao;

    //construtor privado
    private Conexao() throws ClassNotFoundException, SQLException {

        Class.forName(driver);//carrega o driver para o JAVA
        //Abre a conexão
        conexao = (Connection) DriverManager.getConnection(url, usuario, senha);

    }

    //Padrão de Projeto Singleton (apenas uma instancia do objeto POR THREAD)
    private static final ThreadLocal<Conexao> instanciaThreadLocal = new ThreadLocal<>();

    public static Conexao getInstance() {
        Conexao instancia = instanciaThreadLocal.get();
        if (instancia == null) {
            try {
                instancia = new Conexao();
                instanciaThreadLocal.set(instancia);
                System.out.println("nova conexao thread " + Thread.currentThread().getId() + " Tempo: " + System.currentTimeMillis());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    public PreparedStatement preparar(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }

    public ResultSet consultar(String sql) throws SQLException {
        PreparedStatement st = preparar(sql);
        ResultSet rs = st.executeQuery();
        return rs;
    }

}
