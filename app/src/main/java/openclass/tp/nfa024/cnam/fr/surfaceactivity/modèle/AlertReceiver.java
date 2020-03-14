package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.capture.GameBoussoleActivity;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.napoleonString;


public class AlertReceiver extends BroadcastReceiver {


    public static final String TAG = "Alert Receiver";
    private static final String PROX_ALERT_INTENT =
            "openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.AlertReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "C'est bon");

        switch (intent.getAction()) {

            case PROX_ALERT_INTENT:
            String key = LocationManager.KEY_PROXIMITY_ENTERING;
            boolean entering = intent.getBooleanExtra(key, false);

            Log.d(TAG, entering + ": booléen");

            if (entering) {
                Toast.makeText(context, "Vous approchez du trésor !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, GameBoussoleActivity.class);
                i.putExtra("POI", napoleonString);
                context.startActivity(i);
                Log.d(TAG, "Entrée dans le périmètre");

            } else {
                Log.d(TAG, "Sortie du périmètre");
                Toast.makeText(context, "Sortie du périmètre", Toast.LENGTH_LONG).show();
            }
            break;

        }

        /**
         Toast.makeText(context, "Entrée dans le périmètre", Toast.LENGTH_SHORT).show();

         if (intent.getData() != null)
         Log.v(TAG, intent.getData().toString());

         Bundle extras = intent.getExtras();
         if (extras != null) {
         Log.v("", "Message: " + extras.getString("message"));
         Log.v("", "Entering? " + extras.getBoolean(LocationManager.KEY_PROXIMITY_ENTERING));
         }
         */
    }

    }