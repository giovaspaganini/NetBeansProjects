/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rh.negocio.EnderecoFuncionario;
import rh.negocio.Funcionario;

/**
 * Realiza as resposabilidades comportamentais necessárias para a persistencia de um funcionário no banco de dados
 * @author L
 */
public class FuncionarioDAO {
    
    public static int create(Funcionario f) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        String sql = "INSERT INTO funcionarios(fk_cargo, nome, cpf) VALUES (?,?,?)";
   
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, f.getCargo().getPk());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        
        int pkf = rs.getInt("pk_funcionario");
        
        f.setPk_funcionario(pkf);
        
        for (EnderecoFuncionario aux : f.getEndereco()) {
            aux.setFk_funcionario(pkf);
            FuncionarioEnderecoDAO.create(aux);
        }
        
        return pkf;
    }
    
    public static Funcionario retrieve(int pk_funcionario) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        String sql = "SELECT * FROM funcionarios WHERE pk_funcionario = ?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, pk_funcionario);
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();
        
        rs.next();
        
        Funcionario f = new Funcionario(
                pk_funcionario,
                rs.getString("nome"), 
                rs.getString("cpf"),
                CargoDAO.retrieve(rs.getInt("fk_cargo"))
        );
        
        f.setEndereco(FuncionarioEnderecoDAO.retrieveAll(pk_funcionario));
        
        return f;
    }
    
    public static ArrayList<Funcionario> retrieveAll(int pk_funcionario) throws SQLException {
        Connection conn = BancoDados.createConnection();
                
        String sql = "SELECT * FROM funcionarios";        
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.execute();
        
        ResultSet rs = stm.getResultSet();
        
        ArrayList<Funcionario> aux = new ArrayList<>();
        
        while (rs.next()) {
            Funcionario faux = new Funcionario(
                rs.getInt("pk_funcionario"),
                rs.getString("nome"), 
                rs.getString("cpf"),
                CargoDAO.retrieve(rs.getInt("fk_cargo"))
        );
        
        faux.setEndereco(FuncionarioEnderecoDAO.retrieveAll(pk_funcionario));
        
        aux.add(faux);
        
        }       
        
        return aux;
    }
    
    public static void update(Funcionario f) throws SQLException {
        Connection conn = BancoDados.createConnection();
        
        String sql = "UPDATE funcionarios SET fk_cargo=?, nome=?, cpf=? WHERE pk_funcionario=?";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, f.getCargo().getPk());
        stm.setString(2, f.getNome());
        stm.setString(3, f.getCpf());
        stm.setInt(4, f.getPk_funcionario());
        
        stm.execute();
        
        for (EnderecoFuncionario aux : f.getEndereco()) {
            if (!aux.isSync()) {
                FuncionarioEnderecoDAO.update(aux);
            }
        }
        
    }
    
    public static void delete(Funcionario f) throws SQLException {
        if (f.getPk_funcionario()==0){
            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
        }

        String sql = "delete from funcionarios where pk_funcionario=?";
        
        Connection conn = BancoDados.createConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        stm.setInt(1, f.getPk_funcionario());       
        stm.execute();
        stm.close();      
    }
    
}






















//package rh.modelo;
// v1.0
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import rh.negocio.Funcionario;
//import rh.negocio.Dependente;
//import rh.negocio.Endereco;
///**
// *
// * @author user
// */
//public class FuncionarioDAO {
//    public static int create(Funcionario f) throws SQLException{        
//        Connection conn = BancoDados.createConnection();
//        
//        for (Dependente d:f.getDependentes()){
//            d.setPk(DependenteDAO.create(d));
//        }
//        
//        for (Endereco e:f.getEndereco()){
//            e.setPk_endereco(FuncionarioEnderecoDAO.create(e));
//        }
//        
//        f.getCargo().setPk(CargoDAO.create(f.getCargo()));        
//        
//        PreparedStatement stm = conn.prepareStatement(
//                    "INSERT INTO funcionarios (fk_cargo, fk_dependente, nome, salario) VALUES (?,?,?,?)",
//                        PreparedStatement.RETURN_GENERATED_KEYS
//                );        
//        
//        stm.setInt(1, f.getCargo().getPk());
//        stm.setInt(2, f.getDependentes().get(0).getPk());
//        stm.setString(3, f.getNome());
//        stm.setDouble(4, f.getSalario());        
//        
//        stm.execute();
//
//        ResultSet rs = stm.getGeneratedKeys();
//        
//        rs.next();
//        f.setPk(rs.getInt(1));        
//        
//        stm.close();
//        
//        return f.getPk();
//    }
//    
//    public static Funcionario retrieve(int pk) throws SQLException{
//        Connection conn = BancoDados.createConnection();
//        PreparedStatement stm = conn.prepareStatement(
//                    "SELECT * FROM funcionarios WHERE pk_funcionario = ?"
//            );
//        
//        stm.setInt(1, pk);
//        stm.execute();
//        
//        ResultSet rs = stm.getResultSet();     
//        
//        rs.next();
//        
//        Funcionario f = new Funcionario(rs.getString("nome"), 
//                rs.getDouble("salario"), 
//                DependenteDAO.retrieve(rs.getInt("fk_dependente")), 
//                CargoDAO.retrieve(rs.getInt("fk_cargo"))
//            );
//        
//        return f;
//    }
//    
//    public static ArrayList<Funcionario> retrieveAll() throws SQLException{
//        ArrayList<Funcionario> aux = new ArrayList<>();
//        Connection conn = BancoDados.createConnection();
//
//        String sql = "SELECT * FROM funcionarios";
//
//        ResultSet rs = conn.createStatement().executeQuery(sql);
//
//        while (rs.next()){
//            Funcionario f = new Funcionario(
//                    rs.getInt("pk_funcionario"), 
//                    rs.getString("nome"),
//                    rs.getDouble("salario"),
//                    DependenteDAO.retrieve(rs.getInt("fk_dependente")),
//                    FuncionarioEnderecoDAO.retrieve(rs.getInt("fk_endereco")),
//                    CargoDAO.retrieve(rs.getInt("fk_cargo"))
//            );
//
//            aux.add(f);
//        }
//        return aux;             
//    }
//    
//    public static void update(Funcionario f) throws SQLException{
//        if (f.getPk()==0){
//            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
//        }
//        
//        String sql = "UPDATE funcionarios SET fk_cargo=?, fk_dependente=?, nome=?, salario=? where pk_funcionario=?";
//        
//        Connection conn = BancoDados.createConnection();
//        PreparedStatement stm = conn.prepareStatement(sql);
//        
//        stm.setInt(1, f.getCargo().getPk());
//        stm.setInt(2, f.getDependentes().get(0).getPk());
//        stm.setString(3, f.getNome());
//        stm.setDouble(4, f.getSalario());
//        
//        stm.execute();
//        stm.close();
//    }
//    
//    
//    public static void delete(Funcionario f) throws SQLException{
//        if (f.getPk()==0){
//            throw new SQLException("Objeto não persistido ainda ou com a chave primária não configurada");
//        }
//
//        String sql = "DELETE FROM funcionarios WHERE pk_cargo=?";
//        
//        Connection conn = BancoDados.createConnection();
//        PreparedStatement stm = conn.prepareStatement(sql);
//        
//        stm.setInt(1, f.getPk());       
//        stm.execute();
//        stm.close();        
//    }
//}
