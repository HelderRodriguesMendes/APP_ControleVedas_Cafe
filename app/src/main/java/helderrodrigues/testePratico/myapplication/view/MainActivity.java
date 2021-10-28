package helderrodrigues.testePratico.myapplication.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import helderrodrigues.testePratico.myapplication.R;
import helderrodrigues.testePratico.myapplication.model.utils.Adapter;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;
import helderrodrigues.testePratico.myapplication.model.utils.RecyclerItemClickListener;
import helderrodrigues.testePratico.myapplication.viewModel.PedidoViewModel;
import helderrodrigues.testePratico.myapplication.viewModel.ProdutoViewModel;

public class MainActivity extends AppCompatActivity {

    private ProdutoViewModel produtoViewModel;
    private PedidoViewModel pedidoViewModel;
    private List<Produto> list;
    private RecyclerView recyclerView;
    private List<Pedido> PEDIDOS = new ArrayList<>();
    private TextView txtIdQtdCarrinho;
    private CircleImageView imageView;


    private int qtdItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        txtIdQtdCarrinho = findViewById(R.id.textQtdItemCarrinho);
        imageView = findViewById(R.id.idCarrinho);

        txtIdQtdCarrinho.setText("");

        produtoViewModel = new ViewModelProvider(this).get(ProdutoViewModel.class);
        pedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        listProdutos();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtIdQtdCarrinho.getText().toString().equals("")){
                    Toast.makeText(getApplication(), getString(R.string.txtValidacaoCarrinho), Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, CarrinhoActivity.class);
                    intent.putExtra("PEDIDOS", (Serializable) PEDIDOS);
                    startActivity(intent);
                }
            }
        });

    }

    public void listProdutos(){
        list = new ArrayList<>();
        list = produtoViewModel.getProdutos();
        if(list.isEmpty()){
            produtoViewModel.salvar();
            list = produtoViewModel.getProdutos();
            Adapter adapter = new Adapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //Gerenciador de Layout
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }else{
            Adapter adapter = new Adapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); //Gerenciador de Layout
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Produto produto = list.get(position);
                showAlert(produto);
            }

            @Override
            public void onLongItemClick(View view, int position) {}

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        }));
    }

    public void showAlert(Produto produto){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(getString(R.string.txtTitleAlertDialogPedido) + " " + produto.getNome() + "?");

        alert.setCancelable(false);

        EditText txtQtd = new EditText(MainActivity.this);
        EditText txtDescricao = new EditText(MainActivity.this);

        txtQtd.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtQtd.setHint(getString(R.string.txtHintQtdAlertDialogPedido));

        txtDescricao.setHint(getString(R.string.txtHintObsAlertDialogPedido));

        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(txtQtd);
        linearLayout.addView(txtDescricao);
        alert.setView(linearLayout);

        alert.setNegativeButton(getString(R.string.txtBtnNegatAlertDialogCancel), null);
        alert.setPositiveButton(getString(R.string.txtBtnPositAlertDialogPedido), new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                String qtd = txtQtd.getText().toString();
                String desc = txtDescricao.getText().toString();
                PEDIDOS = pedidoViewModel.getPedido(qtd, desc, produto);
                qtdItens = 0;
                PEDIDOS.forEach(p -> {
                    qtdItens += p.getQuantidade();
                    p.getProduto().getNome();
                    p.getValorTotal();
                });
                txtIdQtdCarrinho.setText(String.valueOf(qtdItens));
            }
        });
        alert.create().show();
    }


}