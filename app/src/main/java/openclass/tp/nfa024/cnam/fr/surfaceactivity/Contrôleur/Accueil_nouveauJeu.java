package openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POIHash;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public class Accueil_nouveauJeu extends AppCompatActivity {


    private Button nouveauJeu, chargerJeu;
    private HashMap<String, POI> mPOIHashMap, hashMapPOI;
    POI mPOI;


    private static final String SHARED_PREFS = "SharedPref";

    private static final String TAG = "Accueil_nouveau_jeu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_accueil);

        this.nouveauJeu = (Button) this.findViewById(R.id.btn);
        this.chargerJeu = (Button) this.findViewById(R.id.btn2);

        mPOI = new POI();
        HashMap<String, POI> mPOIHashMap = POIHash.getPOIHash();
        hashMapPOI=new HashMap<>();


        nouveauJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(v.getContext(), Enigme.class);
                startActivity(map);
            }
        });


        chargerJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                Intent i = new Intent(v.getContext(), Enigme.class);
                String loadGame="Chargement des données";
                i.putExtra("Load Game", loadGame);
                startActivity(i);
            }
        });
    }


    public HashMap<String, POI> loadData() {

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(SHARED_PREFS, this.MODE_PRIVATE);

        Gson gson=new Gson();
        String json=sharedPreferences.getString("KeyPOI", null);

        Type token = new TypeToken<HashMap<String,POI>>(){}.getType();

        hashMapPOI=gson.fromJson(json, token);

        if(hashMapPOI==null){
            hashMapPOI=new HashMap<>();
        }
        return hashMapPOI;
    }



}


