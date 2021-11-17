package com.example.bukukas.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bukukas.Models.Databases;
import com.example.bukukas.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Bundle bundle;
    String menu;
    String username, password;
    int id_user;
    Databases db;
    TextView out,in;
    CardView menu_pemasukan, menu_pengeluaran, menu_setting, menu_flow;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        id_user = bundle.getInt("id_user",0);
        username = bundle.getString("username","");
        password = bundle.getString("password","");


        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        month += 1;

        db = new Databases(getApplicationContext());
        double peng = db.getPengeluaran(month,year);
        double pem = db.getPemasukan(month,year);
        out = findViewById(R.id.pengeluaran);
        in = findViewById(R.id.pemasukan);

        out.setText("Pengeluraan : " + changeIDR(peng));
        in.setText("Pemasukan : " + changeIDR(pem));

        menu_pemasukan = findViewById(R.id.menu_pemasukan);
        menu_pengeluaran = findViewById(R.id.menu_pengeluaran);
        menu_setting = findViewById(R.id.menu_setting);
        menu_flow = findViewById(R.id.menu_flow);

        menu_pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = "pemasukan";
                changeMenu();
            }
        });


        menu_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = "pengeluaran";
                changeMenu();
            }
        });


        menu_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = "setting";
                changeMenu();
            }
        });


        menu_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = "flow";
                changeMenu();
            }
        });
    }

    private void changeMenu(){
        Intent i = new Intent(getApplicationContext(),Change.class);
        i.putExtra("id_user",id_user);
        i.putExtra("menu",menu);
        i.putExtra("username",username);
        i.putExtra("password",password);
        startActivity(i);
    }

    private String changeIDR(double nominal){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String nom = kursIndonesia.format(nominal);
        return nom;
    }
}
