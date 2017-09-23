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
public class Dependente {
    private int pk;
    private String nome;
    private String parentesco;

    public Dependente() {
    }
    
    public Dependente(int pk, String nome, String parentesco) {
        this.pk = pk;
        this.nome = nome;
        this.parentesco = parentesco;
    }

    public Dependente(String nome, String parentesco) {
        this.nome = nome;
        this.parentesco = parentesco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }    

    @Override
    public String toString() {
        return "Dependente{" + "nome=" + nome + ", parentesco=" + parentesco + '}';
    }
}
