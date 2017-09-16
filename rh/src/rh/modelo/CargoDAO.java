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
        
        Connection conn = 
                BancoDados.createConnection();
        
        PreparedStatement stm = 
                conn.prepareStatement(
                    "INSERT INTO cargos (descricao, gratificacao) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
                
        stm.setString(1, c.getDescricao());
        stm.setDouble(2, c.getGratificacao());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        c.setPk(rs.getInt(1));
        
        stm.close();                   
        
        return 0;
    }

    
}
