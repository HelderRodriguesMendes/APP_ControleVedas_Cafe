package helderrodrigues.testePratico.myapplication.viewModel;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import helderrodrigues.testePratico.myapplication.R;
import helderrodrigues.testePratico.myapplication.model.database.produtoRepository.VendaRepository;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;
import helderrodrigues.testePratico.myapplication.model.entity.Venda;

public class PedidoViewModel extends AndroidViewModel {

    private List<Pedido> PEDIDOS = new ArrayList<>();

    public PedidoViewModel(@NonNull Application application) {
        super(application);
    }

    public List<Pedido> getPedido(String qtd, String desc, Produto produto){
        if(qtd.equals("")){
            Toast.makeText(getApplication(), getApplication().getString(R.string.txtValidacaoQtdPedido1)
                            + " " + produto.getNome()
                            + " " + getApplication().getString(R.string.txtValidacaoQtdPedido2),
                    Toast.LENGTH_LONG).show();
        }else{
            Pedido pedido = new Pedido();
            pedido.setProduto(produto);
            pedido.setQuantidade(Integer.parseInt(qtd));
            pedido.setObservacao(desc);
            pedido.setValorTotal(produto.getPreco() * Integer.parseInt(qtd));

            PEDIDOS.add(pedido);
        }
        return PEDIDOS;
    }


}
