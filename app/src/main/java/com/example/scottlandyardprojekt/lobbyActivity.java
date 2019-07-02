package com.example.scottlandyardprojekt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.RelativeLayout.TRUE;

public class lobbyActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> spielerListe = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lobby);
            TextView wait = findViewById(R.id.WaitText);
            ListView sList = findViewById(R.id.spielerListe);
            RadioGroup rad = findViewById(R.id.radig);
            rad.setVisibility(View.INVISIBLE);
            RadioButton r1 = findViewById(R.id.radioButton2);
            RadioButton r2 = findViewById(R.id.radioButton3);
            RadioButton r3 = findViewById(R.id.radioButton4);
            RadioButton r4 = findViewById(R.id.radioButton5);
            RadioButton r5 = findViewById(R.id.radioButton6);
            RadioButton r6 = findViewById(R.id.radioButton7);
            Button readyButton = findViewById(R.id.gameStart);


            //Daten aus dem Intent holen

            final Bundle pack = getIntent().getExtras();
            String spielName = pack.getString("sName");
            String passWort = pack.getString("pWort");
            String spitzName = pack.getString("pName");
            String rolle = pack.getString("rolle");

            //Text ändern, um Warten auszudrücken


            if (rolle.equals("admin")) {
                wait.setText("Warten auf Mitspieler");


            }

            /// TODO: 01.06.2019 Mit server verbinden, einloggen, checken obs passt, warten bis alle da sind

            //Testliste erstellen
            spielerListe.add(spitzName);
            //Hier dan die spitznamen der anderen spieler
            spielerListe.add("Günther");
            spielerListe.add("Olaf");
            spielerListe.add("Hauke");
            spielerListe.add("Cemal");
            spielerListe.add("Norman");

            ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, spielerListe);
            sList.setAdapter(arrayadapter);
            wait.setText("Warten auf Admin");

            // Wenn Admin, dann die Liste ausblenden und Stattdessen Radiobuttons einfügen, und den Text auf "Wähle Mr.X" ändern

            if (rolle.equals("admin")) {
                sList.setVisibility(View.INVISIBLE);
                rad.setVisibility(View.VISIBLE);
                wait.setText("Bitte wähle Mr.X");

                r1.setText(spielerListe.get(0));
                r2.setText(spielerListe.get(1));
                r3.setText(spielerListe.get(2));
                r4.setText(spielerListe.get(3));
                r5.setText(spielerListe.get(4));
                r6.setText(spielerListe.get(5));

            }
            readyButton.setOnClickListener(this);

        } catch(Exception exception){
            System.out.println("lobby onCreate" + exception.getMessage());
        }

        }

        @Override
        public void onClick (View v){
        try {

            RadioGroup rad = findViewById(R.id.radig);

            final Bundle pack = getIntent().getExtras();
            String rolle = pack.getString("rolle");

            switch (rolle) {

                // den Admin Mr x wählen lassen. Toast falls keine Auswahl getroffen
                case "admin":
                    Context context = getApplicationContext();
                    CharSequence text;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;

                    int checkedButton = rad.getCheckedRadioButtonId();
                    System.out.println("lobby button " + checkedButton);


                    if (rad.getCheckedRadioButtonId() == -1) {
                        text = "Wähle Mr.X!";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                       // Integer mrx = rad.getCheckedRadioButtonId();
                        //das hier funktioniert nicht, weil es ist irgendwie nicht ButtonID 1 sondern 2131230856, TODO Das hier reparieren
                       // String mrxName = spielerListe.get(mrx);

                        // TODO Randomnumbergenerator, generiert zufällige startpositionen aus der Liste aller Stationen. Prüft, dass keine Position doppelt vergeben wurde

                        // randomnumbergenerator
                        //ArrayList<> ranKo = new ArrayList();
                        //for(int i=5;i<6;i++) {
                        //int r = "randomnumber"
                        //"String" newKoo = ListOfStations.get(r);
                        //if(ranko.contains(newKoo)){
                        //continue;
                        // }else{
                        // ranko.add(newKoo);
                        //
                        //}
                        Intent startMap = new Intent(this, MapActivity.class);
                        // startMap.putExtra("mrx",mrx);
                        //startMap.putExtra("mrxName",mrxName);
                        //startMap.putExtra("rolle", "admin");
                        //startMap.putExtra ("liste",spielerListe);
                        startActivity(startMap);
                    }


                    break;
                case "player":

                    //Startet Mapactivity
                    Intent startMap = new Intent(this, MapActivity.class);
                    //startMap.putExtra("rolle","player");
                    startActivity(startMap);

            }
        } catch (Exception e) {
            System.out.println("lobby onClick" + e.getMessage());
        }
    }
}