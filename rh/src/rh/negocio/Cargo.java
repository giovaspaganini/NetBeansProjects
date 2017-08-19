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
public class Cargo {
    private String descricao;
    private double gratificacao;

    public Cargo() {
    }

    public Cargo(String descricao, double gratificacao) {
        this.descricao = descricao;
        this.gratificacao = gratificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getGratificacao() {
        return gratificacao;
    }

    public void setGratificacao(double gratificacao) {
        this.gratificacao = gratificacao;
    }

    @Override
    public String toString() {
        return "Cargo{" + "Descricao=" + descricao + ", gratificacao=" + gratificacao + '}';
    }
}
