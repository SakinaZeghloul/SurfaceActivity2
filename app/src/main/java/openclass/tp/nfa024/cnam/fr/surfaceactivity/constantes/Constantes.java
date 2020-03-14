package openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

public class Constantes {

    public static Context CURRENT_CONTEXT;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static long INIT_TIME;

    public static final String couronne="Couronne";

    public static final String josephine="Joséphine de Beauharnais";
    public static final String cambaceres="Cambacérès";
    public static final String bonaparte="Louis Bonaparte";

    public static final String marlon_brando="Marlon Brando";
    public static final String karl_marx="Karl Marx";
    public static final String oscar_wilde="Oscar Wilde";

    public static final String archimede="Archimède";
    public static final String pythagore="Pythagore";
    public static final String alexandre_le_grand="Alexandre Le Grand";

    public static final String vendomeString="Vendôme";
    public static final String napoleonString="Napoléon";
    public static final String beatlesString="Beatles";
    public static final String raphaelString="Raphaël";


    public static final double LATITUDE_NAPOLEON=48.7974;
    public static final double LONGITUDE_NAPOLEON=2.286200000000008;

    public static final double LATITUDE_BEATLES=48.781279;
    public static final double LONGITUDE_BEATLES=2.220145;

    public static final double LATITUDE_RAPHAEL=48.813056653676583;
    public static final double LONGITUDE_RAPHAEL=2.3068184554676582;

    public static final double LATITUDE_VENDOME=48.866310;
    public static final double LONGITUDE_VENDOME=2.354310;

    public static final LatLng NAPOLEON = new LatLng(LATITUDE_NAPOLEON, LONGITUDE_NAPOLEON);
    public static final LatLng BEATLES = new LatLng(LATITUDE_BEATLES, LONGITUDE_BEATLES);
    public static final LatLng RAPHAEL = new LatLng(LATITUDE_RAPHAEL, LONGITUDE_RAPHAEL);
    public static final LatLng VENDOME = new LatLng(LATITUDE_VENDOME, LONGITUDE_VENDOME);

    public static final long POINT_RADIUS = 1000; // in Meters
    public static final long PROX_ALERT_EXPIRATION = -1;
    public static final String PROX_ALERT_INTENT =
            "openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.AlertReceiver";
}
