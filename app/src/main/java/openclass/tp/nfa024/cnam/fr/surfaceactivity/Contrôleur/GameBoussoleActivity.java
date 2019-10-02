package openclass.tp.nfa024.cnam.fr.surfaceactivity.Contrôleur;

import android.hardware.Camera;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.preference.PowerPreference;

import java.util.HashMap;
import java.util.Map;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.CameraPreview;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POI;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.POIOnlyView;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.SensorMatrix;
import openclass.tp.nfa024.cnam.fr.surfaceactivity.R;

import static openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle.Constantes.napoleonString;


public class GameBoussoleActivity extends AppCompatActivity {

    private SurfaceView filtered;
    private CameraPreview mCameraPreview;
    private Camera mCamera;
    public static final String TAG="GameBoussoleActivity";
    private POIOnlyView mPOIOnlyView;
    private POI mPOI;
    private SensorMatrix mSensorMatrix;
    public boolean isLightOn;
    private HashMap<String, POI> mPOIHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.boussole_ra);

        DisplayMetrics ds=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);
        Constantes.SCREEN_WIDTH=ds.widthPixels;
        Constantes.SCREEN_HEIGHT=ds.heightPixels;


        filtered=new SurfaceView(this);
        mCameraPreview=new CameraPreview(this, filtered);

        mPOIOnlyView=new POIOnlyView(this);
        mPOIHashMap=PowerPreference.getDefaultFile().getMap("POIHash", HashMap.class, String.class, POI.class);


        for (Map.Entry<String, POI> g : mPOIHashMap.entrySet()) {
            mPOI=g.getValue();

            Log.d(TAG, "POI: " + mPOI.getId());
            Log.d(TAG, "POI tag: " + mPOI.getTag());
            Log.d(TAG, "POI latitude: " + mPOI.getLatitude());
        }

        final FrameLayout layout = this.findViewById(R.id.surface_preview);
        layout.addView(mCameraPreview);
        layout.addView(mPOIOnlyView);

        for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
            mPOI = e.getValue();

            if (mPOI.getTag() == 2) {

                if (mPOI.getId().contains(napoleonString)) {
                    layout.removeView(mPOIOnlyView);
                }
            }
        }


        ImageButton btn = findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                for (Map.Entry<String, POI> e : mPOIHashMap.entrySet()) {
                    mPOI = e.getValue();

                    if (mPOI.getTag() == 2) {

                        if (mPOI.getId().contains(napoleonString)) {

                            if (isLightOn) {
                                mCameraPreview.ledFlashIn();
                                layout.addView(mPOIOnlyView);
                                isLightOn = false;
                            } else {
                                mCameraPreview.ledFlashOut();
                                layout.removeView(mPOIOnlyView);
                                isLightOn = true;
                            }
                        }
                    }
                }
            }
        });



        mSensorMatrix=new SensorMatrix();
        mSensorMatrix.register();

    }

    public static Camera getCameraInstance()
    {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            Log.d(TAG, "Could not open camera");
        }
        return c;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mCamera = getCameraInstance();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        mSensorMatrix.pause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mSensorMatrix.pause();
    }
}
