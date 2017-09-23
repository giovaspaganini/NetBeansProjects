package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Endereco;

/**
 *
 * @author user
 */
public class EnderecoDAO {
    public static int create(Endereco e) throws SQLException{

    Connection conn = BancoDados.createConnection();

    PreparedStatement stm = 
            conn.prepareStatement("INSERT INTO enderecos (fk_funcionario, logradouro, bairro, cidade, estado) VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
    
    stm.setInt(1, e.getFk_funcionario());
    stm.setString(2, e.getLogradouro());
    stm.setString(3, e.getBairro());
    stm.setString(4, e.getCidade());
    stm.setString(5, e.getEstado());

    stm.execute();

    ResultSet rs = stm.getGeneratedKeys();
    rs.next();
    e.setPk_endereco(rs.getInt(1));

    stm.close();

    return e.getPk_endereco();
}

public static Endereco retrieve(int pk) throws SQLException{
    Connection conn = BancoDados.createConnection();
    PreparedStatement stm = conn.prepareStatement("SELECT * FROM enderecos WHERE pk_endereco = ?");
    stm.setInt(1, pk);

    stm.execute();

    ResultSet rs = stm.getResultSet();

    rs.next();

    return new Endereco(rs.getInt("pk_endereco"),
            rs.getInt("fk_funcionario"),
            rs.getString("logradouro"),
            rs.getString("bairro"),
            rs.getString("cidade"),
            rs.getString("estado")
    );
}



public static ArrayList<Endereco> retrieveAll(int fk_funcionario) throws SQLException{    
    ArrayList<Endereco> aux = new ArrayList<>();
    
    Connection conn = BancoDados.createConnection();
    String sql = "SELECT * FROM enderecos where fk_funcionario = ?";
    
    PreparedStatement stm = conn.prepareStatement("SELECT * FROM endereco WHERE pk_endereco = ?");
    stm.setInt(1, fk_funcionario);

    stm.execute();

    ResultSet rs = stm.getResultSet();    

    while(rs.next()){
        Endereco e = new Endereco(rs.getInt("pk_endereco"),
                rs.getInt("fk_funcionario"),
                rs.getString("logradouro"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado")
        );
        
        aux.add(e);
    }

    return aux;
    }    
}
