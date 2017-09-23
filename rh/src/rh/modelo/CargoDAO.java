package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
        public static ArrayList<Cargo> retrieveAll() throws SQLException{
            
            ArrayList<Cargo> aux = new ArrayList<>();
            Connection conn = BancoDados.createConnection();
            
            String sql = "SELECT * FROM cargos";
            
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while (rs.next()){
                Cargo c = new Cargo(rs.getInt("pk_cargo"), 
                        rs.getString("descricao"),
                        rs.getDouble("gratificacao"));
                
                aux.add(c);
            }
            
            
            return aux;
        }
        
        public static ArrayList<Cargo> retrieveAll(double gratificacaoInicial, double gratificacaoFinal) throws SQLException{
        ArrayList<Cargo> aux = new ArrayList<>();
        String sql = "select * from cargos where gratificacao>=? and gratificacao<=?";
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDouble(1, gratificacaoInicial);
        stm.setDouble(2, gratificacaoFinal);
        stm.execute();
        
        ResultSet rs = stm.getResultSet();
        while (rs.next()){
            Cargo c = new Cargo(rs.getInt("pk_cargo"), 
                                rs.getString("descricao"),
                                rs.getDouble("gratificacao"));
            aux.add(c);
        }
        return aux;
    }
        
    public static void update(Cargo c) throws SQLException{
        if (c.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }
        
        String sql = "update cargos set descricao=?, gratificacao=? where pk_cargo=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setString(1, c.getDescricao());
        stm.setDouble(2, c.getGratificacao());
        stm.setInt(3, c.getPk());
    }
    
    public static void delete(Cargo c) throws SQLException{
        if (c.getPk()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }
        
        String sql = "delete from cargos where pk_cargo=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, c.getPk());
        stm.execute();
        stm.close();
    }
            
            
            
//        Connection conn = BancoDados.createConnection();
//        PreparedStatement stm = conn.prepareStatement("SELECT * FROM cargos");     
//        
//        stm.execute();
//        
//        ResultSet rs = stm.getResultSet();     
//        ArrayList<Cargo> cargos = new ArrayList<>();
//        
//        while(rs.next()){
//            cargos.add(new Cargo(rs.getInt("pk_cargo"),
//                    rs.getString("descricao"),
//                    rs.getDouble("gratificacao")));
//        }        
//        return cargos;       
    
}
