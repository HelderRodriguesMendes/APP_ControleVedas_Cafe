package helderrodrigues.testePratico.myapplication.model.entity;

import java.io.Serializable;
import java.util.List;

public class Venda implements Serializable {
    private List<Pedido> pedidos;
    private Long vendaId;
    private double valorTotalCompra;
    private String cupomDesconto;
    private String formaPagamento;

    public Long getVendaId() {
        return vendaId;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public String getCupomDesconto() {
        return cupomDesconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setVendaId(Long vendaId) {
        this.vendaId = vendaId;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setValorTotalCompra(double valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }

    public void setCupomDesconto(String cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
