package helderrodrigues.testePratico.myapplication.model.entity;

import java.io.Serializable;

public class Pedido implements Serializable {
    private Long pedidoid;
    private Produto produto;
    private int quantidade;
    private String observacao;
    private double valorTotal;

    public Long getPedidoid() {
        return pedidoid;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setPedidoid(Long pedidoid) {
        this.pedidoid = pedidoid;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
