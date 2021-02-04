package pt.ipleiria.estg.dei.app_projeto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            convertView = layoutInflater.inflate(R.layout.activity_item_planostreino, null);

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(planosTreinos.get(position));
        return convertView;
    }

    public void refresh(ArrayList<PlanosTreino> planosTreinos) {
        this.planosTreinos = planosTreinos;
        notifyDataSetChanged();
    }



    private class ViewHolderLista{
        private TextView diatreino;
        private TextView pt;
        private TextView semana;
        public ViewHolderLista(View convertView){
            diatreino = convertView.findViewById(R.id.tvDiaTreino);
            pt = convertView.findViewById(R.id.tvPT);
            semana = convertView.findViewById(R.id.tvSemana);
        }

        public void update(PlanosTreino planosTreino){
            diatreino.setText(planosTreino.getDia_treino());
            pt.setText(planosTreino.getid_PT()+"");
            semana.setText(planosTreino.getSemana());
        }
    }
}
