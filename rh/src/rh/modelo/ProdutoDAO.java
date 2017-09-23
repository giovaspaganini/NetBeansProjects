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
    public static int create(Produto p) throws SQLException{        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(
                    "INSERT INTO produtos (descricao, valor_unitario) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );        
        
        stm.setString(1, p.getDescricao());
        stm.setDouble(2, p.getValorUnitario());        
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        p.setPk(rs.getInt(1));
        stm.close();                   
        
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
                rs.getDouble("valor_unitario")
        );
    }
    
    public static ArrayList<Produto> retrieveAll() throws SQLException{
        ArrayList<Produto> aux = new ArrayList<>();        
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT * FROM produtos";
              
        ResultSet rs = conn.createStatement().executeQuery(sql);
        
        while (rs.next()){
            Produto p = new Produto(
                    rs.getInt("pk_produto"), 
                    rs.getString("descricao"),
                    rs.getDouble("valor_unitario")
            );
            aux.add(p);
        }        
        return aux;
    }
    
    public static void update(Produto p) throws SQLException{
        if (p.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }
        
        String sql = "UPDATE produtos SET set descricao=?, valor_unitario=? where pk_produto=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setString(1, p.getDescricao());
        stm.setDouble(2, p.getValorUnitario());
        stm.setInt(3, p.getPk());
        
        stm.execute();
        stm.close();
    }
    
    public static void delete(Produto p) throws SQLException{
        if (p.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "DELETE FROM produtos where pk_produto=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, p.getPk());       
        stm.execute();
        stm.close();        
    }
}
