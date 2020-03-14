package openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.ImageTableau;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.capture.MapActivity2;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.POIHash;
import uk.co.senab.photoview.PhotoViewAttacher;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.alexandre_le_grand;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.archimede;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.beatlesString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.bonaparte;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.cambaceres;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.couronne;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.josephine;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.karl_marx;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.marlon_brando;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.napoleonString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.oscar_wilde;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.pythagore;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.raphaelString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes.vendomeString;

public class Enigme extends AppCompatActivity implements OnClickableAreaClickedListener {

    private String loadGame;
    private HashMap<String, POI> mPOIHashMap;
    private HashMap<Integer, ImageTableau> mImageHash;


    private static final String TAG = "Enigme";
    private int mScore;
    private String ENIGME, choix;


    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private ClickableAreasImage mClickableAreasImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_layout);

        mScore = 0;

        mImageHash= POIHash.getImageHash();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            loadGame = (String) extras.get("Load Game");
            choix=(String) extras.get("image");

            if (loadGame != null) {
            //    mPOIHashMap = mScanner.loadDataPOI(this);
            }
            else if(choix!=null){
                Log.d(TAG, "Choix: " + choix);
                switch(choix) {
                    case "Vendôme":
                        Toast.makeText(this, "Vendôme", Toast.LENGTH_SHORT).show();
                        zoomImage(R.drawable.vendome);
                        break;
                    case "Napoléon":
                        Toast.makeText(this, "Napoléon", Toast.LENGTH_SHORT).show();
                        zoomImage(R.drawable.napoleon);
                        break;

                    case "Beatles":
                        Toast.makeText(this, "Beatles", Toast.LENGTH_SHORT).show();
                        zoomImage(R.drawable.beatles);
                        break;

                    case "Raphaël":
                        Toast.makeText(this, "Raphaël", Toast.LENGTH_SHORT).show();
                        zoomImage(R.drawable.raphael);
                        break;
                }
            }
        }

  //      this.configureAndShowMainFragment();
    }


    private void zoomImage(int i) {

        setContentView(R.layout.image_layout);

        mScore = 0;
        mImageView=findViewById(R.id.imageView);
        mImageView.setImageResource(i);
        mAttacher = new PhotoViewAttacher(mImageView);
        mClickableAreasImage = new ClickableAreasImage(mAttacher, this);
        List<ClickableArea> clickableAreaList = getClickableAreasList();
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

             //   mScanner.saveData(mPOIHashMap);

                Intent game = new Intent(Enigme.this, MapActivity2.class);
                game.putExtra(ENIGME, tab.getNomTableau());
                startActivity(game);
            }
        }
    }


    private void desactiveBouton() {

        mPOIHashMap= PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);

        for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
            POI mPOI = e.getValue();

            if (mPOI.isCapture() == 1){
                switch(mPOI.getId()) {

                    case "Vendôme":
                 //       btnVendome.setEnabled(false);
                        break;

                    case "Napoléon":
                   //     btnNapoleon.setEnabled(false);
                        break;

                    case "Beatles":
                     //   btnBeatles.setEnabled(false);
                        break;

                    case "Raphaël":
                       // btnRaphael.setEnabled(false);
                        break;
                }
            }
        }
    }

    public static List<ClickableArea> getClickableAreasList() {

        List<ClickableArea> clickableAreas = new ArrayList<>();

        clickableAreas.add(new ClickableArea(1080, 400, 80, 50, new ImageTableau(couronne, vendomeString, 0)));

        clickableAreas.add(new ClickableArea(380, 380, 50, 80, new ImageTableau(josephine, napoleonString, 0)));
        clickableAreas.add(new ClickableArea(650, 340, 50, 80, new ImageTableau(cambaceres, napoleonString, 0)));
        clickableAreas.add(new ClickableArea(64, 340, 40, 100, new ImageTableau(bonaparte, napoleonString, 0)));

        clickableAreas.add(new ClickableArea(70, 90, 60, 40, new ImageTableau(marlon_brando, beatlesString, 0)));
        clickableAreas.add(new ClickableArea(400, 45, 40, 40, new ImageTableau(karl_marx, beatlesString, 0)));
        clickableAreas.add(new ClickableArea(160, 100, 40, 40, new ImageTableau(oscar_wilde, beatlesString, 0)));

        clickableAreas.add(new ClickableArea(1140, 710, 100, 70, new ImageTableau(archimede, raphaelString, 0)));
        clickableAreas.add(new ClickableArea(340, 690, 80, 100, new ImageTableau(pythagore, raphaelString, 0)));
        clickableAreas.add(new ClickableArea(380, 480, 50, 120, new ImageTableau(alexandre_le_grand, raphaelString, 0)));


        return clickableAreas;}

    }

        /**

         private void configureAndShowMainFragment(){

         mEnigmeFragment=(EnigmeFragment)getSupportFragmentManager().
         findFragmentById(R.id.imageView);

         if(mEnigmeFragment==null) {
         mEnigmeFragment=new EnigmeFragment();
         getSupportFragmentManager().beginTransaction()
         .add(R.id.imageView, mEnigmeFragment)
         .commit();
         }
         }

         */


