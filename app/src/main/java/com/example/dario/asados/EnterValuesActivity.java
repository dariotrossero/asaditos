package com.example.dario.asados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.dario.asados.logic.Contenedor;

import java.util.ArrayList;


public class EnterValuesActivity extends ActionBarActivity {
    ArrayList<EditText[]> arreglo = new ArrayList<EditText[]>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setTitle("¿Cúanto pagó cada uno?");
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int comensales = Integer.parseInt(message);
        Log.i("comensales", message);


        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        for (int i = 0; i < comensales; i++) {

            LinearLayout ll1 = new LinearLayout(this);
            ll1.setOrientation(LinearLayout.HORIZONTAL);

            ll.addView(ll1);

            EditText cb = new EditText(this);
            cb.setHint("Participante " + (i + 1));


            ll1.addView(cb);

            EditText cb1 = new EditText(this);
            cb1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            cb1.setHint("¿Cuanto puso?");
            int maxLength = 5;
            cb1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            ll1.addView(cb1);
            arreglo.add(new EditText[]{cb, cb1});

        }
        this.setContentView(sv);

        Button continue_btn = new Button(this);
        continue_btn.setText("Calcular");
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        para.gravity = Gravity.BOTTOM;
        continue_btn.setLayoutParams(para);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(v);
            }
        });
        ll.addView(continue_btn);

    }

    public void process(View view) {
        Intent intent = new Intent(this, Results.class);
        ArrayList<Contenedor> array_to_proccess = new ArrayList<Contenedor>();
        int i = 1;
        boolean abort=false;
        float price;
        for (EditText[] et : arreglo) {
            if (et[1].getText().toString().matches("")) {
                Toast.makeText(this, "Ingresá un numero", Toast.LENGTH_SHORT).show();
                abort = true;
                break;
            } else
                price = Float.parseFloat(et[1].getText().toString());
            String name = et[0].getText().toString();
            if (name.matches(""))
                name = "Participante " + i;

            array_to_proccess.add(new Contenedor(name, price));
            Log.i("Info:", name + "," + et[1].getText().toString());
            i++;
        }

        if (! abort) {
            Bundle informacion = new Bundle();

        informacion.putSerializable("lista", array_to_proccess);
        intent.putExtras(informacion);

        startActivity(intent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_values, menu);
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
