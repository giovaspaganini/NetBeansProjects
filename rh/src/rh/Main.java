package rh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import rh.modelo.BancoDados;

public class Main {
    public static void main(String[] args) throws SQLException {     
        Connection c = BancoDados.createConnection();
        
        PreparedStatement stm = c.prepareStatement(
        "insert into cargos "
                + "(descricao, gratificacao) "
                + "values ('java developer', "
                + "3000)"
        );
        stm.execute();
        
                
        
        /*new TelaCargo().setVisible(true);
        
     Funcionario f = new Funcionario("Pedro", 
                2500, 
                new Dependente("Lucas", "Filho"), 
                new Endereco("Rua Tal", "St Tal", "Mhs", "GO"), 
                new Cargo("Gerente", 400));
        
        Dependente d = new Dependente("Joana", "Esposa");//instaciacao declarativa
        
        f.getDependentes().add(d);//passada por referencia
        f.getDependentes().add(new Dependente("Lia", "Filha"));//instaciacao anonima
        
        f.getEndereco().add(new Endereco("Rua Fulano", "St Ciclano", "Gyn", "GO"));       
        
        Venda v = new Venda(1, "18-08-2017", 5.0, new Itens(2.0, new Produto("Mouse", 10.0)));
        
        v.getItem().add(new Itens(2, new Produto("Teclado", 10.0)));                
        v.getItem().add(new Itens(1, new Produto("Pendrive", 15.0)));
        
        //System.out.println(f);
        System.out.println(v);*/
    }    
}
