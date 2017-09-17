package rh.negocio;
public class Produto {
    private int pk;
    private String descricao;
    private double valorUnitario;

    public Produto(String descricao, double valorUnitario) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }
    
    public Produto(int pk, String descricao, double valorUnitario) {
        this.pk = pk;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }
    

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {        
        return pk + descricao + " R$:" + valorUnitario;               
    }
    
}
