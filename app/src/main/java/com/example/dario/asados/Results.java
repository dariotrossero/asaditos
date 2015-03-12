package com.example.dario.asados;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dario.asados.logic.Contenedor;
import com.example.dario.asados.logic.Proccesor;
import com.example.dario.asados.logic.ResultEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Results extends ActionBarActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("La repartija");
        setContentView(R.layout.activity_results);
        ArrayList<Contenedor> madd = (ArrayList<Contenedor>) getIntent().getSerializableExtra("lista");
        for (Contenedor temp : madd) {
            Log.i("nombre:", temp.getNombre());
            Log.i("valor:", String.valueOf(temp.getValue()));
        }
        Proccesor proccesor = new Proccesor();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float total = proccesor.getTotal(madd);
        float cadaUno = proccesor.getPromedio(madd);


        TextView tv = (TextView) findViewById(R.id.total);
        TextView tv1 = (TextView) findViewById(R.id.promedio);
        ArrayList<ResultEntry> resultado = proccesor.calculate(madd);
        tv.setText("La joda salió: "+df.format(total));
        tv1.setText("Cada uno pone: "+ df.format(cadaUno));
        Log.i("Total", df.format(total));
        Log.i("Cada uno", df.format(cadaUno));
        this.listView = (ListView) findViewById(R.id.listView);
        this.listView.setAdapter(new ItemAdapter(this, resultado));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
