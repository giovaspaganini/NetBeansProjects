package rh.negocio;
import java.util.ArrayList;
public class Funcionario {    
    private String nome;
    private double salario;    
    private ArrayList<Dependente> dependentes;
    private ArrayList<Endereco> endereco = new ArrayList<>();
    private Cargo cargo;

    public Funcionario(String nome, 
            double salario, 
            Dependente dependente, 
            Endereco endereco, 
            Cargo cargo) {
        
        this.nome = nome;
        this.salario = salario;
        getDependentes().add(dependente);
        this.endereco.add(endereco);
        this.cargo = cargo;
    }

    public Funcionario(String nome, double salario, ArrayList<Endereco> endereco, Cargo cargo) {
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public ArrayList<Dependente> getDependentes() {
        if (dependentes==null){
            dependentes = new ArrayList<>();                  
        }

        return dependentes;
    }

    private void setDependentes(ArrayList<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public ArrayList<Endereco> getEndereco() {
        return endereco;
    }

    private void setEndereco(ArrayList<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public double calculeSalario(){
        return this.salario + this.cargo.getGratificacao();
    }

    @Override
    public String toString() {
        String aux;
        
        aux = "Funcionario{" + "nome=" + nome + ", salario=" + salario + ", cargo=" + cargo + "}";
        
        /**
        for (int i=0; i<endereco.size(); i++){
            aux += "\n" + endereco.get(i);
        }
        */
        
        for (Endereco avatar:endereco){
            aux = aux.concat("\n" + avatar);
        }
        
        for (Dependente avatar:dependentes){
            aux = aux.concat("\n" + avatar);
        }    
                       
        return aux.concat("Salario Bruto=" + calculeSalario());
    }

}
