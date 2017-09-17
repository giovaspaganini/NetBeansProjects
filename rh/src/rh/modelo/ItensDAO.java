package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Itens;

/**
 *
 * @author user
 */
public class ItensDAO {
    
    //C . R . U . D. MOTHERF*CKER
    
    public static int create(Itens i) throws SQLException{
        //retorna uma conexao valida
        Connection conn = 
                BancoDados.createConnection();
        //retorna uma assertiva de insercao para ser complementada (?) e que tambem é capaz de retornar chaves primarias(RETURN_GENERATED_KEYS)
        PreparedStatement stm = 
                conn.prepareStatement(
                    "INSERT INTO itens (fk_produto, quantidade) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
        
        //complementa a primeira interrogação (?) com a descrição que vem de cargo
        stm.set(1, i.getProduto());
        stm.setDouble(2, i.getQuantidade());
        
        //executa o comando no BD
        stm.execute();
        //retorna um conjunto de resultados que contem a chave primaria
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();//coloca o resultset em uma posicao valida
        i.setPk(rs.getInt(1));//recupera o valor da chave na primeira coluna (getInt 1º)
        
        //fecha a assertiva
        stm.close();                   
        //retorna chave primaria
        return i.getPk();
    }
    
    public static Itens retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM itens WHERE pk_item = ?"
         );
        
        stm.setInt(1, pk);
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        
        rs.next();
        
        return new Itens(rs.getInt("pk_item"),
        rs.getDouble("fk_produto"),
        rs.getDouble("quantidade"));
    }
    
        public static ArrayList<Itens> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM itens");     
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        ArrayList<Itens> itens = new ArrayList<>();
        
        while(rs.next()){
            itens.add(new Itens(rs.getInt("pk_item"),
                    rs.getString("fk_produto"),
                    rs.getDouble("quantidade")));
        }        
        return itens;        
    }    
}
