package helderrodrigues.testePratico.myapplication.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helderrodrigues.testePratico.myapplication.R;
import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Venda;
import helderrodrigues.testePratico.myapplication.viewModel.VendaViewModel;

public class CarrinhoActivity extends AppCompatActivity {

    private List<Pedido> PEDIDOS = new ArrayList<>();
    private double valorTotalCompra = 0;
    private VendaViewModel vendaViewModel;

    private TextView txtValorTotalCompra;
    private EditText txtCupomDesc;
    private CheckBox boxCartao;
    private CheckBox boxBoleto;
    private CheckBox boxPix;
    private Button button;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtValorTotalCompra = findViewById(R.id.idTxtValorTotal);
        boxCartao = findViewById(R.id.idFormaPag_cartao);
        txtCupomDesc = findViewById(R.id.txtIdCupomDesc);
        boxBoleto = findViewById(R.id.idFormaPag_pix);
        boxPix = findViewById(R.id.idFormaPag_boleto);
        button = findViewById(R.id.idBTN);

        vendaViewModel = new ViewModelProvider(this).get(VendaViewModel.class);
        PEDIDOS = (List<Pedido>) getIntent().getExtras().getSerializable("PEDIDOS");

        valorTotalCompra = vendaViewModel.getValorTotalCompra(PEDIDOS);
        txtValorTotalCompra.setText(getString(R.string.valorReal) + " " + String.valueOf(valorTotalCompra));

        validarFormasPagamentos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validacoes()){
                    Venda venda = getVenda(PEDIDOS, valorTotalCompra, txtCupomDesc.getText().toString());
                    showAlert(venda);
                }
            }
        });
    }

    private Venda getVenda(List<Pedido> pedidos, double valorTotalCompra, String cupom){
        Venda venda = new Venda();
        venda.setPedidos(pedidos);
        venda.setValorTotalCompra(vendaViewModel.verificarDesconto(valorTotalCompra, cupom));
        venda.setCupomDesconto(cupom);
        venda.setFormaPagamento(getFormaPag());

        return venda;
    }

    private String getFormaPag(){
        String formaPag = "";

        if(boxCartao.isChecked()){
            formaPag = getString(R.string.txtFormaPag_cartao);
        }else if(boxBoleto.isChecked()){
            formaPag = getString(R.string.txtFormaPag_boleto);
        }else if(boxPix.isChecked()){
            formaPag = getString(R.string.txtFormaPag_pix);
        }
        return formaPag;
    }

    private boolean validacoes(){
        boolean ok = true;
        if(!boxCartao.isChecked() && !boxBoleto.isChecked() && !boxPix.isChecked()){
            ok = false;
            Toast.makeText(getApplication(), getString(R.string.txtValidacoesCarrinhoFormaPag), Toast.LENGTH_LONG).show();
        }else if(!txtCupomDesc.getText().toString().equals("")){
            if(vendaViewModel.getDesconto(txtCupomDesc.getText().toString()) == 0){
                Toast.makeText(this, getApplication().getString(R.string.txtvalidacaoCupomDesc), Toast.LENGTH_LONG).show();
                ok = false;
            }
        }
        return ok;
    }

    private void validarFormasPagamentos(){
        boxCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(boxCartao.isChecked()){
                    boxPix.setEnabled(false);
                    boxBoleto.setEnabled(false);
                }else{
                    boxPix.setEnabled(true);
                    boxBoleto.setEnabled(true);
                }
            }
        });

        boxPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(boxPix.isChecked()){
                    boxCartao.setEnabled(false);
                    boxBoleto.setEnabled(false);
                }else{
                    boxCartao.setEnabled(true);
                    boxBoleto.setEnabled(true);
                }
            }
        });

        boxBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(boxBoleto.isChecked()){
                    boxCartao.setEnabled(false);
                    boxPix.setEnabled(false);
                }else{
                    boxCartao.setEnabled(true);
                    boxPix.setEnabled(true);
                }
            }
        });
    }

    public void showAlert(Venda venda){
        AlertDialog.Builder alert = new AlertDialog.Builder(CarrinhoActivity.this);
        alert.setTitle(getString(R.string.txtTitleAlertDialogCarrinho));
        alert.setMessage(getString(R.string.txtMessageAlertDialogCarrinho1) + " " + getString(R.string.valorReal) + " " + venda.getValorTotalCompra() + "\n" + getString(R.string.txtMessageAlertDialogCarrinho2) + " " + venda.getFormaPagamento());
        alert.setCancelable(false);

        alert.setNegativeButton(getString(R.string.txtBtnNegatAlertDialogCancel), null);
        alert.setPositiveButton(getString(R.string.txtBtnPositAlertDialogCarrinho), new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vendaViewModel.salvarVenda(venda);
                Toast.makeText(CarrinhoActivity.this, getString(R.string.txtVendaFinalizadaSucess), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CarrinhoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alert.create().show();
    }
}