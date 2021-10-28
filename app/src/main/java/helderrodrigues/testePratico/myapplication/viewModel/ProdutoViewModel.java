package helderrodrigues.testePratico.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import helderrodrigues.testePratico.myapplication.model.database.repository.ProdutoRepository;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;

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
