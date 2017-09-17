package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Produto;

/**
 *
 * @author user
 */
public class ProdutoDAO {
        
    //C . R . U . D. MOTHERF*CKER
    
    public static int create(Produto p) throws SQLException{
        //retorna uma conexao valida
        Connection conn = 
                BancoDados.createConnection();
        //retorna uma assertiva de insercao para ser complementada (?) e que tambem é capaz de retornar chaves primarias(RETURN_GENERATED_KEYS)
        PreparedStatement stm = 
                conn.prepareStatement(
                    "INSERT INTO produtos (descricao, valor_unitario) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
        
        //complementa a primeira interrogação (?) com a descrição que vem de cargo
        stm.setString(1, p.getDescricao());
        stm.setDouble(2, p.getValorUnitario());
        
        //executa o comando no BD
        stm.execute();
        //retorna um conjunto de resultados que contem a chave primaria
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();//coloca o resultset em uma posicao valida
        p.setPk(rs.getInt(1));//recupera o valor da chave na primeira coluna (getInt 1º)
        
        //fecha a assertiva
        stm.close();                   
        //retorna chave primaria
        return p.getPk();
    }
    
    public static Produto retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM produtos WHERE pk_produto = ?"
         );
        
        stm.setInt(1, pk);
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        
        rs.next();
        
        return new Produto(rs.getInt("pk_produto"),
        rs.getString("descricao"),
        rs.getDouble("valor_unitario"));
    }
    
        public static ArrayList<Produto> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM produtos");     
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        ArrayList<Produto> produtos = new ArrayList<>();
        
        while(rs.next()){
            produtos.add(new Produto(rs.getInt("pk_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("valor_unitario")));
        }        
        return produtos;        
    }
}
