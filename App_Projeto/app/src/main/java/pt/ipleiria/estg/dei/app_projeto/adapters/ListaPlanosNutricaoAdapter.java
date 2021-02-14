package pt.ipleiria.estg.dei.app_projeto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class ListaPlanosNutricaoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<PlanosNutricao> planosNutricaos;

    private String currentIP = "";

    public ListaPlanosNutricaoAdapter(Context context, ArrayList<PlanosNutricao> planosNutricaos) {
        this.context = context;
        this.planosNutricaos = planosNutricaos;
        currentIP = Singleton.getInstance(context).getIPInput();
    }

    @Override
    public int getCount() {
        return planosNutricaos.size();
    }

    @Override
    public Object getItem(int position) {
        return planosNutricaos.get(position);
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
            convertView = layoutInflater.inflate(R.layout.activity_item_planosnutricao, null);

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(planosNutricaos.get(position));
        return convertView;
    }

    public void refresh(ArrayList<PlanosNutricao> planosNutricaos) {
        this.planosNutricaos = planosNutricaos;
        notifyDataSetChanged();
    }



    private class ViewHolderLista{
        private TextView segunda;
        private TextView terca;
        private TextView quarta;
        private TextView quinta;
        private TextView sexta;
        private TextView sabado;
        private TextView semana;
        public ViewHolderLista(View convertView){
            /*segunda = convertView.findViewById(R.id.tvSegunda);
            terca = convertView.findViewById(R.id.tvTerca);
            quarta = convertView.findViewById(R.id.tvQuarta);
            quinta = convertView.findViewById(R.id.tvQuinta);
            sexta = convertView.findViewById(R.id.tvSexta);
            sabado = convertView.findViewById(R.id.tvSabado);*/
            semana = convertView.findViewById(R.id.tvSemanaNut);
        }

        public void update(PlanosNutricao planosNutricao){
            /*segunda.setText(planosNutricao.getSegunda()+"");
            terca.setText(planosNutricao.getTerca()+"");
            quarta.setText(planosNutricao.getQuarta()+"");
            quinta.setText(planosNutricao.getQuinta()+"");
            sexta.setText(planosNutricao.getSexta()+"");
            sabado.setText(planosNutricao.getSabado()+"");*/
            semana.setText(planosNutricao.getSemana());
        }
    }
}
