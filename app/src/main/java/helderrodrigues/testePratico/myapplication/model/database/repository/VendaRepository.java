package helderrodrigues.testePratico.myapplication.model.database.repository;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import helderrodrigues.testePratico.myapplication.model.database.Database;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Venda;

public class VendaRepository {
    private Context context;

    public VendaRepository(Context context) {
        this.context = context;
    }

    public int getDesconto(String cupom){
        return Database.getInstance(context).getPorcentagemDesconto(cupom);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void salvarVenda(Venda venda){
        venda.getPedidos().forEach(p -> {
            p.setPedidoid(salvarPedido(p));
        });

        venda.setVendaId(Database.getInstance(context).salvarVenda(venda));
        salvarpedidosCarrinho(venda);
    }

    private Long salvarPedido(Pedido pedido){
        return Database.getInstance(context).salvarPedido(pedido);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void salvarpedidosCarrinho(Venda venda){
        venda.getPedidos().forEach(p -> {
            Database.getInstance(context).salvarpedidosCarrinho(venda.getVendaId(), p.getPedidoid());
        });
    }
}
