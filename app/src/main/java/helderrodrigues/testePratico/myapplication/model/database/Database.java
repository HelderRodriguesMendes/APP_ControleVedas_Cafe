package helderrodrigues.testePratico.myapplication.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import helderrodrigues.testePratico.myapplication.model.entity.Pedido;
import helderrodrigues.testePratico.myapplication.model.entity.Produto;
import helderrodrigues.testePratico.myapplication.model.entity.Venda;

public class Database extends SQLiteOpenHelper {

    private static Database INSTANCE;

    private static final String DB_NAME = "produtos.db";
    private static final int BD_VERSION = 1;

    public static Database getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new Database(context);
        }
        return INSTANCE;
    }

    private Database(@Nullable Context context) {
        super(context, DB_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE produtos(produtoid INTEGER  primary key, nome varchar(30), imagem varchar(300), preco double)");
        db.execSQL("CREATE TABLE pedidos(pedidoid INTEGER primary key, produto_Id INTEGER, quantidade INTEGER, observacao varchar(300), valorTotal DOUBLE, FOREIGN KEY (produto_Id) references produtos(produtoid))");
        db.execSQL("CREATE TABLE vendas(vendaId INTEGER primary key, valorTotalCompra DOUBLE, cupomDesconto VARVHAR(50), formaPagamento varchar(50))");
        db.execSQL("CREATE TABLE pedidosCarrinho (venda_Id INTEGER, pedido_id INTEGER, FOREIGN KEY (venda_Id) references vendas(vendaId), FOREIGN KEY (pedido_id) references pedidos(pedidoid))");
        db.execSQL("CREATE TABLE descontos(descontoId INTEGER primary key, porcentagem INTEGER, codigo VARCHAR(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvarProduto(Produto produto){
        Long produtoId = Long.valueOf(0);
        SQLiteDatabase db = getWritableDatabase();

        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("imagem", produto.getImagem());
            values.put("preco", produto.getPreco());
            produtoId = db.insertOrThrow("produtos", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (db.isOpen()){
                db.endTransaction();
            }
        }
    }

    public void salvarDesconto(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("porcentagem", 5);
            values.put("codigo", "X5Y5Z");
            db.insertOrThrow("descontos", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (db.isOpen()){
                db.endTransaction();
            }
        }
    }

    public List<Produto> getProdutos(){

        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos;", null);

        try {
            if(cursor.moveToFirst()){
                do{
                    Produto p = new Produto();
                    p.setProdutoId(cursor.getLong(cursor.getColumnIndex("produtoid")));
                    p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    p.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));
                    p.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                    produtos.add(p);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (cursor == null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return produtos;
    }

    public int getPorcentagemDesconto(String cupom){
        int porcentagem = 0;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT porcentagem FROM descontos where codigo = " + "'"+cupom + "'" , null);
        try {
            if(cursor.moveToFirst()){
                do {
                    porcentagem = cursor.getInt(cursor.getColumnIndex("porcentagem"));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (cursor == null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return porcentagem;
    }

    public Long salvarPedido(Pedido pedido){
        Long pedidoId = Long.valueOf(0);

        SQLiteDatabase db = getWritableDatabase();

        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("produto_Id", pedido.getProduto().getProdutoId());
            values.put("quantidade", pedido.getQuantidade());
            values.put("observacao", pedido.getObservacao());
            values.put("valorTotal", pedido.getValorTotal());
            pedidoId = db.insertOrThrow("pedidos", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (db.isOpen()){
                db.endTransaction();
            }
        }
        return pedidoId;
    }

    public Long salvarVenda(Venda venda){
        Long vendaId = Long.valueOf(0);

        SQLiteDatabase db = getWritableDatabase();

        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("valorTotalCompra", venda.getValorTotalCompra());
            values.put("cupomDesconto", venda.getCupomDesconto());
            values.put("formaPagamento", venda.getFormaPagamento());
            vendaId = db.insertOrThrow("vendas", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (db.isOpen()){
                db.endTransaction();
            }
        }
        return vendaId;
    }

    public void salvarpedidosCarrinho(Long idVenda, Long idPedido){
        SQLiteDatabase db = getWritableDatabase();

        try{
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("venda_Id", idVenda);
            values.put("pedido_id", idPedido);
            db.insertOrThrow("pedidosCarrinho", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("SQLite", e.getMessage(), e);
        }finally {
            if (db.isOpen()){
                db.endTransaction();
            }
        }
    }

}
