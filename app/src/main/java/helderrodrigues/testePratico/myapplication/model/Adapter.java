package helderrodrigues.testePratico.myapplication.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import helderrodrigues.testePratico.myapplication.R;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;
import helderrodrigues.testePratico.myapplication.model.utils.DownloadImageCoffee;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private List<Produto> listProdutos;
    private String value;

    public Adapter(List<Produto> produtos){
        this.listProdutos = produtos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewlist, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        Produto produto = listProdutos.get(position);

        holder.nome.setText(produto.getNome());
        holder.valor.setText(value + " " + String.valueOf(produto.getPreco()));

        new DownloadImageCoffee((ImageView) holder.foto).execute(produto.getImagem());
    }

    @Override
    public int getItemCount() {

        return listProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView valor;
        ImageView foto;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewNomeListAdapter);
            valor = itemView.findViewById(R.id.textViewValorListAdapter);
            foto = itemView.findViewById(R.id.imageViewFotoListAdapter);

            value = valor.getText().toString();
            valor.setText("");
        }
    }
}

