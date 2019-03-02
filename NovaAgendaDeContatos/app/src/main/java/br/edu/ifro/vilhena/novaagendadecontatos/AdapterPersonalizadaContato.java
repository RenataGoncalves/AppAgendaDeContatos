package br.edu.ifro.vilhena.novaagendadecontatos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifro.vilhena.novaagendadecontatos.model.Contato;

public class AdapterPersonalizadaContato extends BaseAdapter {

    private List<Contato> contatos;
    private Activity activity;

    public AdapterPersonalizadaContato(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  this.contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item_lista_de_contatos, parent, false);
        ImageView formularioFoto = view.findViewById(R.id.lista_personalizada_foto);

        TextView formularioEmail = view.findViewById(R.id.lista_personalizada_descricao);
        TextView formularioNome = view.findViewById(R.id.lista_personalizada_nome);

        Contato contato = contatos.get(position);
        formularioNome.setText(contato.getNome());
        formularioEmail.setText(contato.getEmail());

        if (contato.getCaminhoFoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(contato.getCaminhoFoto());
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            formularioFoto.setImageBitmap(bitmapReduzido);
            formularioFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            formularioFoto.setTag(contato.getCaminhoFoto());
        }

        return view;
    }
}
