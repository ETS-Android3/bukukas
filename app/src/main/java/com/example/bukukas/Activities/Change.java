package com.example.bukukas.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bukukas.Fragment.CashFlow;
import com.example.bukukas.Fragment.Pemasukan;
import com.example.bukukas.Fragment.Pengeluaran;
import com.example.bukukas.Fragment.Setting;
import com.example.bukukas.R;

public class Change extends AppCompatActivity {

    Bundle bundle;
    String menu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        bundle = getIntent().getExtras();
        menu = bundle.getString("menu","");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        switch (menu){
            case "setting":
                fragment = new Setting();
                break;

            case "pemasukan":
                fragment = new Pemasukan();
                break;

            case "pengeluaran":
                fragment = new Pengeluaran();
                break;


            case "flow":
                fragment = new CashFlow();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + menu);
        }

        if(!menu.equals("") && menu.length() > 0){
            fragmentTransaction.replace(R.id.change_fragment,fragment);
            fragment.setArguments(bundle);
            fragmentTransaction.commit();
        }

    }
}