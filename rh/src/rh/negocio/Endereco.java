/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.negocio;

/**
 *
 * @author L
 */
public class Endereco {
    private int pk_endereco;
    private int fk_funcionario;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
    }
    
    public Endereco(int pk_endereco, int fk_funcionario, String logradouro, String bairro, String cidade, String estado) {
        this.pk_endereco = pk_endereco;
        this.fk_funcionario = fk_funcionario;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco(int fk_funcionario, String logradouro, String bairro, String cidade, String estado) {
        this.fk_funcionario = fk_funcionario;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPk_endereco() {
        return pk_endereco;
    }

    public void setPk_endereco(int pk_endereco) {
        this.pk_endereco = pk_endereco;
    }

    public int getFk_funcionario() {
        return fk_funcionario;
    }

    public void setFk_funcionario(int fk_funcionario) {
        this.fk_funcionario = fk_funcionario;
    }    

    @Override
    public String toString() {
        return "Endereco{" + "pk_endereco=" + pk_endereco + ", fk_funcionario=" + fk_funcionario + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + '}';
    }
   
}
