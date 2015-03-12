package com.example.dario.asados;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dario.asados.logic.ResultEntry;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<ResultEntry> items;

    public ItemAdapter(Context context, List<ResultEntry> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // Set data into the view.
        TextView deudor = (TextView) rowView.findViewById(R.id.deudor);
        TextView acreedor = (TextView) rowView.findViewById(R.id.acreedor);
        TextView valor = (TextView) rowView.findViewById(R.id.valor);

        ResultEntry item = this.items.get(position);
        deudor.setText(item.getDeudor());
        acreedor.setText(item.getAcreedor());
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        valor.setText(df.format(item.getValue()));
        return rowView;
    }

}