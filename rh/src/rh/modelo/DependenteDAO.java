package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Dependente;

/**
 *
 * @author user
 */
public class DependenteDAO {
    public static int create(Dependente d) throws SQLException{

    Connection conn = BancoDados.createConnection();

    PreparedStatement stm = 
            conn.prepareStatement("insert into dependentes (nome, parentesco) values (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

    stm.setString(1, d.getNome());
    stm.setString(2, d.getParentesco());

    stm.execute();

    ResultSet rs = stm.getGeneratedKeys();
    rs.next();
    d.setPk(rs.getInt(1));

    stm.close();

    return d.getPk();
}

public static Dependente retrieve(int pk) throws SQLException{
    Connection conn = BancoDados.createConnection();
    PreparedStatement stm = conn.prepareStatement("select * from dependentes where pk_dependente = ?");
    stm.setInt(1, pk);

    stm.execute();

    ResultSet rs = stm.getResultSet();

    rs.next();

    return new Dependente(rs.getInt("pk_dependente"), rs.getString("nome"), rs.getString("parentesco"));
}


public static ArrayList<Dependente> retrieveAll() throws SQLException{

    Connection conn = BancoDados.createConnection();
    PreparedStatement stm = conn.prepareStatement("select * from dependentes");

    stm.execute();

    ResultSet rs = stm.getResultSet();
    ArrayList<Dependente> dependentes = new ArrayList<>();

    while(rs.next()){
        dependentes.add(new Dependente(rs.getInt("pk_dependente"), rs.getString("nome"),rs.getString("parentesco")));
    }

    return dependentes;

}    
}
