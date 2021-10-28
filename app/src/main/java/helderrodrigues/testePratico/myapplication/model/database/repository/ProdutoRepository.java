package helderrodrigues.testePratico.myapplication.model.database.repository;


import android.content.Context;

import java.util.List;

import helderrodrigues.testePratico.myapplication.model.database.Database;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;

public class ProdutoRepository {
    private Context context;
    Database db;

    public ProdutoRepository(Context context) {
        this.context = context;
    }

    public void salvar(){
        Produto p1 = new Produto();
        p1.setNome("Café Brigadeiro");
        p1.setImagem("https://www.selecoes.com.br/wp-content/uploads/2019/04/iStock-584884010-1-760x450.jpg");
        p1.setPreco(3.50);
        Database.getInstance(context).salvarProduto(p1);

        Produto p2 = new Produto();
        p2.setNome("Café Clássico");
        p2.setImagem("https://baristawave.com/wp-content/uploads/2020/04/ESPRESSO-360x302.jpg");
        p2.setPreco(1.50);
        Database.getInstance(context).salvarProduto(p2);

        Produto p3 = new Produto();
        p3.setNome("Café Macchiato");
        p3.setImagem("https://baristawave.com/wp-content/uploads/2020/04/macchiato-360x302.jpg");
        p3.setPreco(4.50);
        Database.getInstance(context).salvarProduto(p3);

        Produto p4 = new Produto();
        p4.setNome("Café Affogato");
        p4.setImagem("https://baristawave.com/wp-content/uploads/2020/04/affogato.jpg");
        p4.setPreco(5.00);
        Database.getInstance(context).salvarProduto(p4);

        Produto p5 = new Produto();
        p5.setNome("Café Cappuccino");
        p5.setImagem("https://s2.glbimg.com/AUKvBV_FmtzIJ0RWTbsHwzk19CQ=/0x0:450x406/984x0/smart/filters:strip_icc()/s.glbimg.com/po/rc/media/2012/06/13/15/38/41/141/Cappuccino_01.jpg");
        p5.setPreco(5.00);
        Database.getInstance(context).salvarProduto(p5);

        Produto p6 = new Produto();
        p6.setNome("Café Mini-Cappuccino");
        p6.setImagem("https://baristawave.com/wp-content/uploads/2020/04/tyler-nix-qJ8N1w8eivU-unsplash-360x330.jpg");
        p6.setPreco(2.50);
        Database.getInstance(context).salvarProduto(p6);

        Produto p7 = new Produto();
        p7.setNome("Café Au Lait");
        p7.setImagem("https://baristawave.com/wp-content/uploads/2020/04/Cafe-au-lait-with-butter-croissant-370x247.jpg");
        p7.setPreco(6.00);
        Database.getInstance(context).salvarProduto(p7);

        Produto p8 = new Produto();
        p8.setNome("Café Americano");
        p8.setImagem("https://perfectdailygrind.com/pt/wp-content/uploads/sites/5/2021/04/PDG-Brasil-Headers-1.png");
        p8.setPreco(5.00);
        Database.getInstance(context).salvarProduto(p8);

        Produto p9 = new Produto();
        p9.setNome("Café Espresso");
        p9.setImagem("https://loucodocafe.com.br/wp-content/uploads/2019/09/cafe-expresso-02-e1568084083582.jpg");
        p9.setPreco(3.00);
        Database.getInstance(context).salvarProduto(p9);

        Produto p10 = new Produto();
        p10.setNome("Café Latte");
        p10.setImagem("https://upload.wikimedia.org/wikipedia/commons/2/2b/Latte_macchiato.jpg");
        p10.setPreco(5.00);
        Database.getInstance(context).salvarProduto(p10);

        Produto p11 = new Produto();
        p11.setNome("Café Mocha");
        p11.setImagem("https://thumbs.dreamstime.com/b/hot-cafe-mocha-coffee-20307692.jpg");
        p11.setPreco(7.00);
        Database.getInstance(context).salvarProduto(p11);

        Produto p12 = new Produto();
        p12.setNome("Café Macchiato de Caramelo");
        p12.setImagem("https://www.delonghi.com/Global/recipes/Coffee/caramel_macchiato.png");
        p12.setPreco(8.00);
        Database.getInstance(context).salvarProduto(p12);

        Produto p13 = new Produto();
        p13.setNome("Café Irish");
        p13.setImagem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8WtYT1nGB7-P1so73qQKBHgTTr9qP4RqgIw&usqp=CAU");
        p13.setPreco(9.00);
        Database.getInstance(context).salvarProduto(p13);

        Produto p14 = new Produto();
        p14.setNome("Café Glacé");
        p14.setImagem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReysa_EbeyP9kwJj92Clho9TgLTXoxaw9bVA&usqp=CAU");
        p14.setPreco(7.00);
        Database.getInstance(context).salvarProduto(p14);

        Produto p15 = new Produto();
        p15.setNome("Café Gelado");
        p15.setImagem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-uOKMfmDqDWsRlCisprfqYMsCd2t0ZARATMN6nt4WasX5ApkSWeP0K1QEGDViQ9jGfg&usqp=CAU");
        p15.setPreco(7.00);
        Database.getInstance(context).salvarProduto(p15);

        Database.getInstance(context).salvarDesconto();
    }

    public List<Produto> getProdutos(){
        return Database.getInstance(context).getProdutos();
    }
}
