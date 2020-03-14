package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.preference.PowerPreference;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.capture.MapActivity2;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme.EnigmeRecycler;


public class Scanner extends AppCompatActivity {

    private Button btn, btn2;
    private TextView tv;
    private POI mPOI;

    public static final String TAG = "Scanner";
    private String ENIGME;

    private HashMap<String, POI> mPOIHashMap;


    private static final String SHARED_PREFS = "SharedPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_scanner);

        btn = (Button) this.findViewById(R.id.btn);
        tv = findViewById(R.id.text_view);


        mPOIHashMap= PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);

        this.btn = (Button) this.findViewById(R.id.btn);
        this.btn2 = (Button) this.findViewById(R.id.btn2);


        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();


            for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
                mPOI = e.getValue();

                if (extras != null) {

                    String choix = extras.getString("Trésor");

                    Log.d(TAG, "Choix: " + choix);

                    if (mPOI.getId().equals(choix)) {
                        mPOI.setCapture(1);
                    } else {
                        mPOI.setCapture(0);
                    }
                }
            }
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Map.Entry<String, POI> e : mPOIHashMap.entrySet())
                {
                    String g=e.getKey();
                    mPOI=e.getValue();
                }
                PowerPreference.getDefaultFile().setMap("POIHash", mPOIHashMap);
                Intent map = new Intent(view.getContext(), MapActivity2.class);
                startActivity(map);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "saveData");
                PowerPreference.getDefaultFile().setMap("POIHash", mPOIHashMap);
                Intent map = new Intent(view.getContext(), EnigmeRecycler.class);
                map.putExtra("Trésor", "Retour aux énigmes");
                startActivity(map);
            }
        });
    }


    public void saveData(HashMap<String, POI> testHashMap) {

        SharedPreferences mSharedPreferences = this.getSharedPreferences(SHARED_PREFS,
                this.MODE_PRIVATE);
        SharedPreferences.Editor editorKey = mSharedPreferences.edit();

        Gson gson=new Gson();
        String jsonMap = gson.toJson(testHashMap);

        editorKey.putString("KeyPOI", jsonMap);

        editorKey.apply();
    }

    public HashMap<String, POI> loadDataPOI(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, this.MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("KeyPOI", null);

        Type token = new TypeToken<HashMap<String, POI>>() {}.getType();

        mPOIHashMap = gson.fromJson(json, token);

        return mPOIHashMap;
    }
}