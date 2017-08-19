package rh.negocio;
import java.util.ArrayList;

public class Venda {    
    private double numero;
    private String data;
    private double desconto;
        
    private ArrayList<Itens> item = new ArrayList<>();    
    private Produto produto;

    public Venda(double numero, String data, Itens item) {
        this.numero = numero;
        this.data = data;
        getItem().add(item);        
    }
    
    public void addItem(Itens item){
        getItem().add(item);
    }

    public Venda(double numero, String data, double desconto, Itens item) {
        this.numero = numero;
        this.data = data;
        this.desconto = desconto;
        getItem().add(item);        
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ArrayList<Itens> getItem() {
        if (item==null){
            item = new ArrayList<>();                  
        }
        return item;
    }

    public void setItem(ArrayList<Itens> item) {
        this.item = item;
    }
    
    public double calculeDesconto(){
        return this.calculeValorTotal()*this.desconto/100;
    }
    
    public double calculeValorTotal(){
        double valorTotal = 0;
        for (Itens item1 : item) {
            valorTotal += item1.calculeTotal();
        }
        return valorTotal;
    }
    
    public double calculeValorLiquido(){
        return this.calculeValorTotal()-this.calculeDesconto();
    }

    @Override
    public String toString() {
        String aux;
        aux = "\nVenda numero: " + numero + "\nData: " + data;
        
        for (Itens avatar:item){
            aux = aux.concat("\n" + avatar);
        }
        
        return aux.concat("\n===========================\nValor Líquido: R$" + this.calculeValorLiquido() + "\nDesconto: R$" + this.calculeDesconto() + "\nValor Total: R$" + (this.calculeValorTotal()));
    }    
    
}
