package helderrodrigues.testePratico.myapplication.viewModel;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import helderrodrigues.testePratico.myapplication.model.database.produtoRepository.ProdutoRepository;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;
import helderrodrigues.testePratico.myapplication.view.MainActivity;

public class ProdutoViewModel extends AndroidViewModel {
    private ProdutoRepository produtoRepository;
    private List<Produto> produtos;
    private Application application;


    public ProdutoViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }


    public void salvar(){
        produtoRepository = new ProdutoRepository(application.getBaseContext());
        produtoRepository.salvar();
    }

    public List<Produto> getProdutos(){
        produtoRepository = new ProdutoRepository(application.getBaseContext());
        return produtoRepository.getProdutos();
    }
}
