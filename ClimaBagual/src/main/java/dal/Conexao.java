package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexao {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3307/banco_clima?serverTimezone=UTC";
    private final String usuario = "root";
    private final String senha = "";  // Senha do banco (atualmente vazia)
    private Connection conexao;

    // Construtor privado
    private Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);  // Carrega o driver JDBC
        conexao = DriverManager.getConnection(url, usuario, senha);  // Abre a conexão
    }

    // Padrão de Projeto Singleton (apenas uma instância por thread)
    private static final ThreadLocal<Conexao> instanciaThreadLocal = new ThreadLocal<>();

    // Retorna a instância da conexão
    public static Conexao getInstance() {
        Conexao instancia = instanciaThreadLocal.get();
        if (instancia == null) {
            try {
                instancia = new Conexao();
                instanciaThreadLocal.set(instancia);
                System.out.println("Nova conexão para a thread " + Thread.currentThread().getId() + " Tempo: " + System.currentTimeMillis());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    // Verifica se a conexão está fechada e recria se necessário
    void verificarConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            // Recria a conexão caso ela tenha sido fechada
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão recriada!");
        }
    }

    // Prepara a consulta SQL
    public PreparedStatement preparar(String sql) throws SQLException {
        verificarConexao();  // Verifica se a conexão está aberta antes de preparar a consulta
        return conexao.prepareStatement(sql);
    }

    // Realiza a consulta no banco
    public ResultSet consultar(String sql) throws SQLException {
        PreparedStatement st = preparar(sql);
        return st.executeQuery();
    }

    // Retorna a conexão
    public Connection getConexao() {
        return conexao;
    }

    // Fecha a conexão (apenas quando necessário)
    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
            System.out.println("Conexão fechada.");
        }
    }
}