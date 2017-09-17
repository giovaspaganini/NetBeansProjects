package rh.negocio;
public class Itens {
    private int pk;
    private double quantidade;
    private Produto produto;

    public Itens(int pk, double quantidade, Produto produto) {
        this.pk = pk;
        this.quantidade = quantidade;
        this.produto = produto;
    }
    
    public Itens(double quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }    
    
    public double calculeTotal(){
        return this.quantidade * this.produto.getValorUnitario();
    }

    @Override
    public String toString() {
        return "=============================\n" + quantidade + " x " + produto + "\nValor Total: " + calculeTotal() + "\n";
    }       
}
