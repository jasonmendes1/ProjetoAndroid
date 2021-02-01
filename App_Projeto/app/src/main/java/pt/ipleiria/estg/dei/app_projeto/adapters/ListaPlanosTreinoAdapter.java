package pt.ipleiria.estg.dei.app_projeto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class ListaPlanosTreinoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<PlanosTreino> planosTreinos;

    private String currentIP = "";

    public ListaPlanosTreinoAdapter(Context context, ArrayList<PlanosTreino> planosTreinos) {
        this.context = context;
        this.planosTreinos = planosTreinos;
        currentIP = Singleton.getInstance(context).getIPInput();
    }

    @Override
    public int getCount() {
        return planosTreinos.size();
    }

    @Override
    public Object getItem(int position) {
        return planosTreinos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.activity_planos_treino, null);

        ListaPlanosTreinoAdapter.ViewHolderGrelha viewHolderGrelha = (ListaPlanosTreinoAdapter.ViewHolderGrelha) convertView.getTag();
        if (viewHolderGrelha == null) {
            viewHolderGrelha = new ViewHolderGrelha(convertView);
            convertView.setTag(viewHolderGrelha);
        }
        viewHolderGrelha.update(position, planosTreinos);
        return convertView;
    }

    public class ViewHolderGrelha {
        private ImageView image;
        private TextView DiaSemanaTreino;
        public ViewHolderGrelha(View view) {
            DiaSemanaTreino = view.findViewById(R.id.title);
        }
        public void update(int position, ArrayList<PlanosTreino> planosTreinos){
            PlanosTreino planosTreino = planosTreinos.get(position);
            DiaSemanaTreino.setText(planosTreino.getDia_treino());
        }
    }
}
