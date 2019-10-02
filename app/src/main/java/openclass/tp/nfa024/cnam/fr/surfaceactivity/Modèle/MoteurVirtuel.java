package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.preference.PowerPreference;

import java.util.HashMap;
import java.util.Map;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur.Scanner;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.CURRENT_CONTEXT;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.beatlesString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.napoleonString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.raphaelString;
import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.vendomeString;


public class MoteurVirtuel extends View {

    private POIObject mPOIObject;
    private HashMap<String, POI> mPOIHashMap;
    private Point mPoint;
    private POI mPOI;
    private Rect mRect;
    private SensorMatrix mSensorMatrix;
    private Intent i;


    public static final String TAG = "mMoteurVirtuel";

    private long frameTime;


    public MoteurVirtuel(Context context) {

        super(context);

        mRect = new Rect(100, 100, 200, 200);
        mPOIObject = new POIObject(mRect, Color.BLUE);
        mPoint = new Point(Constantes.SCREEN_WIDTH / 2, 3 * Constantes.SCREEN_HEIGHT / 4);
        mPOIObject.update(mPoint);
        mPOI = new POI();
        mPOIHashMap= PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);

        Constantes.CURRENT_CONTEXT = context;

        mSensorMatrix = new SensorMatrix();
        mSensorMatrix.register();

        frameTime = System.currentTimeMillis();
    }


    public void update() {

        if (frameTime < Constantes.INIT_TIME)
            frameTime = Constantes.INIT_TIME;
        int elapedTime = (int) (System.currentTimeMillis() - frameTime);
        frameTime = System.currentTimeMillis();


        mPOIObject.update(mPoint);
    }


    public void recieveTouch(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (mPOIObject.getRectangle().contains((int) event.getX(), (int) event.getY())) {

                    for (Map.Entry<String, POI> e : mPOIHashMap.entrySet())
                    {
                        mPOI=e.getValue();
                        if(mPOI.getTag()==2) {
                            mPOI.setCapture(1);
                            i = new Intent(CURRENT_CONTEXT, Scanner.class);
                            i.putExtra("Trésor", mPOI.getId());
                        }
                    }
                    CURRENT_CONTEXT.startActivity(i);
                    break;
                }
        }
    }


    public void draw (Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.TRANSPARENT);

        double r = mSensorMatrix.orientationAngles[1];
        double s = mSensorMatrix.orientationAngles[0];

        if (r <100 && r > 0)
        {
            mPOIObject.draw(canvas);
        }

        for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
            mPOI = e.getValue();

            if (mPOI.getTag() == 2) {

                switch (mPOI.getId()) {

                    case vendomeString:
                        if (r < mPOI.getMax()-100 && r > mPOI.getMin()-100)
                        {
                            mPOIObject.draw(canvas);
                        }
                        break;

                    case napoleonString:
                        if (r < 100 && r >0)
                        {
                            mPOIObject.draw(canvas);
                        }
                        break;

                    case beatlesString:
                        if (r < mPOI.getMax() + 50 && r > mPOI.getMin() + 50)
                        {
                            mPOIObject.draw(canvas);
                        }
                        break;

                    case raphaelString:
                        if (s < mPOI.getMax() && s > mPOI.getMin())
                        {
                            mPOIObject.draw(canvas);
                        }
                        break;
                }

            }
        }
    }
}