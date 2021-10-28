package helderrodrigues.testePratico.myapplication.viewModel;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import helderrodrigues.testePratico.myapplication.R;
import helderrodrigues.testePratico.myapplication.model.database.produtoRepository.VendaRepository;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Venda;

public class VendaViewModel extends AndroidViewModel {

    private VendaRepository vendaRepository;
    private Application application;
    private int valorDesconto =0;
    private double desconto = 0;

    public VendaViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double getValorTotalCompra(List<Pedido> PEDIDOS){
        return  PEDIDOS.stream().mapToDouble(Pedido::getValorTotal).sum();
    }

    public double verificarDesconto(double valorTotal, String cupom){
        if(!cupom.equals("")){
            valorDesconto = getDesconto(cupom);
            desconto = valorDesconto / 100.0;
            valorTotal -= (desconto * valorTotal);
        }

        return valorTotal;
    }

    public int getDesconto(String cupom){
        vendaRepository = new VendaRepository(application.getBaseContext());
        return vendaRepository.getDesconto(cupom);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void salvarVenda(Venda venda){
        vendaRepository = new VendaRepository(application.getBaseContext());
        vendaRepository.salvarVenda(venda);
    }
}
