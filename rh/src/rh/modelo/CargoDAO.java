package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rh.negocio.Cargo;

/**
 *
 * @author user
 */
public class CargoDAO {
    
    //C . R . U . D. MOTHERF*CKER
    
    public static int create(Cargo c) throws SQLException{
        //retorna uma conexao valida
        Connection conn = 
                BancoDados.createConnection();
        //retorna uma assertiva de insercao para ser complementada (?) e que tambem é capaz de retornar chaves primarias(RETURN_GENERATED_KEYS)
        PreparedStatement stm = 
                conn.prepareStatement(
                    "INSERT INTO cargos (descricao, gratificacao) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
        
        //complementa a primeira interrogação (?) com a descrição que vem de cargo
        stm.setString(1, c.getDescricao());
        stm.setDouble(2, c.getGratificacao());
        
        //executa o comando no BD
        stm.execute();
        //retorna um conjunto de resultados que contem a chave primaria
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();//coloca o resultset em uma posicao valida
        c.setPk(rs.getInt(1));//recupera o valor da chave na primeira coluna (getInt 1º)
        
        //fecha a assertiva
        stm.close();                   
        //retorna chave primaria
        return c.getPk();
    }
    
    public static Cargo retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM cargos WHERE pk_cargo = ?"
         );
        
        stm.setInt(1, pk);
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        
        rs.next();
        
        return new Cargo(rs.getInt("pk_cargo"),
        rs.getString("descricao"),
        rs.getDouble("gratificacao"));
    }
}
