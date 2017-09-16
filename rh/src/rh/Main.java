package rh;

import java.sql.SQLException;
import rh.modelo.CargoDAO;
import rh.negocio.Cargo;

public class Main {
    public static void main(String[] args) throws SQLException {     
        
        System.out.println(CargoDAO.retrieve(5));
        
        
//        Cargo a1 = new Cargo("SW Tester", 300);
//        CargoDAO.create(a1);
//        
//        Cargo a2 = new Cargo("Analista DBA", 4600);
//        CargoDAO.create(a2);
//        
//        System.out.println(a1);
//        System.out.println(a2);
        
        
        
        
        
        
        
        
        
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
