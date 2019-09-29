package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class POIOnlyView extends SurfaceView implements SurfaceHolder.Callback
{

    private SurfaceHolder mSurfaceHolder;
    public static final String TAG="POIOnlyView";

    private MoteurVirtuel mMoteurVirtuel;
    private MainThread mThread;
    private SensorMatrix mSensorMatrix;


    public POIOnlyView(Context context) {

        super(context);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder.addCallback(this);

        Constantes.CURRENT_CONTEXT = context;

        mSensorMatrix=new SensorMatrix();
        mSensorMatrix.register();

        mThread=new MainThread(mSurfaceHolder, this);

        mMoteurVirtuel = new MoteurVirtuel(context);

        setFocusable(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mMoteurVirtuel.recieveTouch(event);

        return true;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mThread = new MainThread(getHolder(), this);
        mThread.setRunning(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        mMoteurVirtuel.update();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        try{
            mThread.setRunning(false);
            mThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw (Canvas canvas)
    {
        super.draw(canvas);
        mMoteurVirtuel.draw(canvas);
    }

    public void update() {
        mMoteurVirtuel.update();
    }
}