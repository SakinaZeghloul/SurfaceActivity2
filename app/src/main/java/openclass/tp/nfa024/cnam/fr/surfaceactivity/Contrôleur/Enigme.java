package openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.ImageTableau;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POIHash;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;
import uk.co.senab.photoview.PhotoViewAttacher;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.vendomeString;

public class Enigme extends AppCompatActivity implements OnClickableAreaClickedListener {

    private Button btnVendome, btnNapoleon, btnBeatles, btnRaphael;
    private String loadGame;
    private HashMap<String, POI> mPOIHashMap;
    private HashMap<Integer, ImageTableau> mImageHash;
    private Scanner mScanner;
    private static final String TAG = "Enigme";
    private int mScore;
    private String ENIGME, choix;


    private ImageView mImageView;
    private uk.co.senab.photoview.PhotoViewAttacher mAttacher;
    private ClickableAreasImage mClickableAreasImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_layout);

        mScore = 0;
        mScanner = new Scanner();
        mImageHash= POIHash.getImageHash();


        btnVendome = (Button) findViewById(R.id.enigme1);
        btnNapoleon = (Button) findViewById(R.id.enigme2);
        btnBeatles = (Button) findViewById(R.id.enigme3);
        btnRaphael = (Button) findViewById(R.id.enigme4);


        mPOIHashMap = POIHash.getPOIHash();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            loadGame = (String) extras.get("Load Game");

            if (loadGame != null) {
                mPOIHashMap = mScanner.loadDataPOI(this);
            }
        }

        desactiveBouton();

        btnVendome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImage(R.drawable.vendome);
            }
        });

        btnNapoleon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImage(R.drawable.napoleon);
            }
        });


        btnBeatles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImage(R.drawable.beatles);
            }
        });

        btnRaphael.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button: " + btnRaphael.getTag());
                zoomImage(R.drawable.raphael);
            }
        });

    }


    private void zoomImage(int i) {

        setContentView(R.layout.image_layout);

        mScore = 0;

        mImageView = findViewById(R.id.imageview);

        mImageView.setImageResource(i);

        mAttacher = new PhotoViewAttacher(mImageView);

        mClickableAreasImage = new ClickableAreasImage(mAttacher, this);

        List<ClickableArea> clickableAreaList = POIHash.getClickableAreasList();
        mClickableAreasImage.setClickableAreas(clickableAreaList);
    }


    @Override
    public void onClickableAreaTouched(Object o) {
        if (o instanceof ImageTableau) {
            String text = ((ImageTableau) o).getNomPerso();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

            for (Map.Entry<Integer, ImageTableau> e : mImageHash.entrySet()) {

                ImageTableau tableau = e.getValue();
                Log.d(TAG, "Text: " + text);
                Log.d(TAG, "Text Perso: " + tableau.getNomPerso());

                if (tableau.getNomPerso().equals(text)) {
                    mScore++;
                    tableau.setDetecte(mScore);
                    Log.d(TAG, "Score 1: " + mScore);
                }
                lancementActivite();
            }
        }
    }


    private void lancementActivite() {

        for (Map.Entry<Integer, ImageTableau> e : mImageHash.entrySet()) {
            Integer g = e.getKey();
            ImageTableau tab = e.getValue();

            if (tab.getDetecte() == 1 && tab.getNomTableau().equals(vendomeString)) {
                tab.setDetecte(0);
                Intent vendomeIntent = new Intent(Enigme.this, MapActivity2.class);
                vendomeIntent.putExtra(ENIGME, vendomeString);
                startActivity(vendomeIntent);

            } else if (tab.getDetecte() == 3) {

                Log.d(TAG, "Score: " + mScore);
                Log.d(TAG, "Integer: " + e.getValue());
                tab.setDetecte(0);
                Intent game = new Intent(Enigme.this, MapActivity2.class);
                game.putExtra(ENIGME, tab.getNomTableau());
                startActivity(game);
            }
        }
    }


    private void desactiveBouton() {


        for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
            POI mPOI = e.getValue();

            if (mPOI.isCapture() == 1){
                switch(mPOI.getId()) {

                    case "Vendôme":
                        btnVendome.setEnabled(false);
                        break;

                    case "Napoléon":
                        btnNapoleon.setEnabled(false);
                        break;

                    case "Beatles":
                        btnBeatles.setEnabled(false);
                        break;

                    case "Raphaël":
                        btnRaphael.setEnabled(false);
                        break;
                }
            }
        }
    }
}

