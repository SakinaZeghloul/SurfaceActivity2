package openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.preference.PowerPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

public class Accueil_nouveauJeu extends AppCompatActivity {


    private Button nouveauJeu, chargerJeu;
    private HashMap<String, POI> mPOIHashMap;
    private Scanner mScanner;
    private POI mPOI;


    private static final String TAG = "Accueil_nouveau_jeu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_accueil);

        this.nouveauJeu = (Button) this.findViewById(R.id.btn);
        this.chargerJeu = (Button) this.findViewById(R.id.btn2);

        mPOI = new POI();

        mPOIHashMap = new HashMap<>();


        nouveauJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPOIHashMap=readFile();
                PowerPreference.getDefaultFile().setMap("POIHash", mPOIHashMap);

                for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
                    mPOI=e.getValue();

                    Log.d(TAG, "POI: " + mPOI.getId());
                    Log.d(TAG, "POI tag: " + mPOI.getTag());
                    Log.d(TAG, "POI latitude: " + mPOI.getLatitude());
                }
                Intent map = new Intent(v.getContext(), Enigme.class);
                startActivity(map);
            }
        });


        chargerJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPOIHashMap=PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);
                Intent i = new Intent(v.getContext(), Enigme.class);
                String loadGame = "Chargement des données";
                i.putExtra("Load Game", loadGame);
                startActivity(i);
            }
        });
    }

    public String readJsonFile() {

        String json = null;
        try {
            InputStream is = getAssets().open("POI.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            Log.d(TAG, "Json: " + json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }


    public HashMap<String, POI> readFile(){

        String json=readJsonFile();

        try {

            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("POI");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject poi = jsonArray.getJSONObject(i);

                System.out.println(poi);

                String ID=poi.getString("id");
                int tag=poi.getInt("tag");
                double latitude=poi.getDouble("latitude");
                double longitude=poi.getDouble("longitude");
                int capture=poi.getInt("capture");
                int min=poi.getInt("min");
                int max=poi.getInt("max");

                mPOIHashMap.put(ID, new POI(ID, tag, latitude, longitude, capture, min, max));

            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return mPOIHashMap;
    }
}
