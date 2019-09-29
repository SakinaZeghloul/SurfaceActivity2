package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.BEATLES;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LATITUDE_BEATLES;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LATITUDE_NAPOLEON;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LATITUDE_RAPHAEL;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LATITUDE_VENDOME;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LONGITUDE_BEATLES;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LONGITUDE_NAPOLEON;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LONGITUDE_RAPHAEL;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.LONGITUDE_VENDOME;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.NAPOLEON;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.RAPHAEL;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.VENDOME;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.alexandre_le_grand;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.archimede;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.beatlesString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.bonaparte;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.cambaceres;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.couronne;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.josephine;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.karl_marx;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.marlon_brando;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.napoleonString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.oscar_wilde;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.pythagore;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.raphaelString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.vendomeString;

public class POIHash {

    static HashMap<String, POI> mPOIHash;
    static HashMap<Integer, ImageTableau> mImageHash;
    static List<ClickableArea> clickableAreas;



    static {

        final POI mPOIVendome=new POI(vendomeString, 1, VENDOME, LATITUDE_VENDOME, LONGITUDE_VENDOME, 0, 0, 100);
        final POI mPOINapoleon=new POI(napoleonString, 1, NAPOLEON, LATITUDE_NAPOLEON, LONGITUDE_NAPOLEON, 0, 0, 100);
        final POI mPOIBeatles=new POI(beatlesString, 1, BEATLES, LATITUDE_BEATLES, LONGITUDE_BEATLES, 0, 0, 100);
        final POI mPOIRaphael=new POI(raphaelString, 1, RAPHAEL, LATITUDE_RAPHAEL, LONGITUDE_RAPHAEL, 0, 0, 100);

        mPOIHash=new HashMap<>();

        mPOIHash.put(vendomeString, mPOIVendome);
        mPOIHash.put(napoleonString, mPOINapoleon);
        mPOIHash.put(beatlesString, mPOIBeatles);
        mPOIHash.put(raphaelString, mPOIRaphael);
    }

    static {

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
    }

    static {

        final ImageTableau couronneTab=new ImageTableau(couronne, vendomeString, 0);

        final ImageTableau josephine_de_beauharnais=new ImageTableau(josephine, napoleonString, 0);
        final ImageTableau cambaceresTab=new ImageTableau(cambaceres, napoleonString, 0);
        final ImageTableau louis_bonaparte=new ImageTableau(bonaparte, napoleonString, 0);

        final ImageTableau marlon_brandoTab=new ImageTableau(marlon_brando, beatlesString, 0);
        final ImageTableau karl_marxTab=new ImageTableau(karl_marx, beatlesString, 0);
        final ImageTableau oscar_wildeTab=new ImageTableau(oscar_wilde, beatlesString, 0);

        final ImageTableau archimedeTab=new ImageTableau(archimede, raphaelString, 0);
        final ImageTableau pythagoreTab=new ImageTableau(pythagore, raphaelString, 0);
        final ImageTableau alexandre_le_grandTab=new ImageTableau(alexandre_le_grand, raphaelString, 0);


        mImageHash=new HashMap<>();

        mImageHash.put(1, couronneTab);

        mImageHash.put(2, josephine_de_beauharnais);
        mImageHash.put(3, cambaceresTab);
        mImageHash.put(4, louis_bonaparte);

        mImageHash.put(5, marlon_brandoTab);
        mImageHash.put(6, karl_marxTab);
        mImageHash.put(7, oscar_wildeTab);

        mImageHash.put(8, archimedeTab);
        mImageHash.put(9, pythagoreTab);
        mImageHash.put(10, alexandre_le_grandTab);
    }

    public static HashMap<Integer, ImageTableau> getImageHash(){
        return mImageHash;
    }


    public static HashMap<String, POI> getPOIHash(){
        return mPOIHash;
    }

    public static List<ClickableArea> getClickableAreasList() { return clickableAreas;}
}
