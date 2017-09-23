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
            conn.prepareStatement("insert into enderecos (logradouro, bairro, cidade, estado) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

    stm.setString(1, e.getLogradouro());
    stm.setString(2, e.getBairro());
    stm.setString(3, e.getCidade());
    stm.setString(4, e.getEstado());

    stm.execute();

    ResultSet rs = stm.getGeneratedKeys();
    rs.next();
    e.setPk(rs.getInt(1));

    stm.close();

    return e.getPk();
}

public static Endereco retrieve(int pk) throws SQLException{
    Connection conn = BancoDados.createConnection();
    PreparedStatement stm = conn.prepareStatement("select * from enderecos where pk_endereco = ?");
    stm.setInt(1, pk);

    stm.execute();

    ResultSet rs = stm.getResultSet();

    rs.next();

    return new Endereco(rs.getInt("pk_endereco"),rs.getString("logradouro"),rs.getString("bairro"),rs.getString("cidade"),rs.getString("estado"));
}



public static ArrayList<Endereco> retrieveAll() throws SQLException{

    Connection conn = BancoDados.createConnection();
    PreparedStatement stm = conn.prepareStatement("select * from enderecos");

    stm.execute();

    ResultSet rs = stm.getResultSet();
    ArrayList<Endereco> enderecos = new ArrayList<>();

    while(rs.next()){
        enderecos.add(new Endereco(rs.getInt("pk_endereco"),rs.getString("logradouro"),rs.getString("bairro"),rs.getString("cidade"),rs.getString("estado")));
    }

    return enderecos;

}    
}
