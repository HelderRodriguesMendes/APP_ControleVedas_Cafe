package helderrodrigues.testePratico.myapplication.model.entity;


import java.io.Serializable;

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long produtoId;
    private String nome;
    private String imagem;
    private Double preco;

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


}
