package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.Funcionario;
import rh.negocio.Dependente;
import rh.negocio.Endereco;
/**
 *
 * @author user
 */
public class FuncionarioDAO {
        
    //C . R . U . D. MOTHERF*CKER
    
    public static int create(Funcionario f) throws SQLException{
        //retorna uma conexao valida
        Connection conn = BancoDados.createConnection();
        
        for (Dependente dependente:f.getDependentes()){
            dependente.setPk(DependenteDAO.create(dependente));
        }
        
        for (Endereco endereco:f.getEndereco()){
            endereco.setPk(EnderecoDAO.create(endereco));
        }
        
        f.getCargo().setPk(CargoDAO.create(f.getCargo()));
        
        
        //retorna uma assertiva de insercao para ser complementada (?) e que tambem é capaz de retornar chaves primarias(RETURN_GENERATED_KEYS)
        PreparedStatement stm = conn.prepareStatement(
                    "INSERT INTO funcionarios (fk_cargo, fk_dependente, nome, salario) VALUES (?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
        
        //complementa a primeira interrogação (?) com a descrição que vem de cargo
        stm.setInt(1, f.getCargo().getPk());
        stm.setInt(2, f.getDependentes().get(0).getPk());
        stm.setString(3, f.getNome());
        stm.setDouble(4, f.getSalario());
        
        //executa o comando no BD
        stm.execute();
        //retorna um conjunto de resultados que contem a chave primaria
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();//coloca o resultset em uma posicao valida
        f.setPk(rs.getInt(1));//recupera o valor da chave na primeira coluna (getInt 1º)
        
        //fecha a assertiva
        stm.close();                   
        //retorna chave primaria
        return f.getPk();
    }
    
    public static Funcionario retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM funcionarios WHERE pk_funcionario = ?"
            );
        
        stm.setInt(1, pk);
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        
        rs.next();
        
        Funcionario f = new Funcionario(rs.getString("nome"), 
                rs.getDouble("salario"), 
                DependenteDAO.retrieve(rs.getInt("fk_dependente")), 
                CargoDAO.retrieve(rs.getInt("fk_cargo"))
            );
        
        return f;
    }
    
        public static ArrayList<Funcionario> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM fucionarios");     
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();     
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        while(rs.next()){
            funcionarios.add(new Funcionario(rs.getString("nome"),
                    rs.getDouble("salario"),
                    DependenteDAO.retrieve(rs.getInt("fk_dependente")),
                    CargoDAO.retrieve(rs.getInt("fk_cargo"))
                    ));
        }        
        return funcionarios;        
    }    
}
