package com.example.mynfcemulatorapp;

import android.content.ComponentName;
import android.nfc.NfcAdapter;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            try {
                // Fontos: a csomagnévnek és az osztálynak pontosnak kell lennie
                ComponentName componentName = new ComponentName(
                        "com.example.mynfcemulatorapp",
                        "com.example.mynfcemulatorapp.MyHceService"
                );
                CardEmulation cardEmulation = CardEmulation.getInstance(nfcAdapter);
                cardEmulation.setPreferredService(this, componentName);
                Log.d("HCE", "Szolgáltatás prioritás beállítva.");
            } catch (Exception e) {
                Log.e("HCE", "Hiba a prioritás beállításakor: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            CardEmulation cardEmulation = CardEmulation.getInstance(nfcAdapter);
            cardEmulation.unsetPreferredService(this);
        }
    }

     */
}