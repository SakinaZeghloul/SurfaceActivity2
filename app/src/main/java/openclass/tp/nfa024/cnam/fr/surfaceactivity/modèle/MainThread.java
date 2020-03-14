package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private POIOnlyView mPOIOnlyView;
    private Boolean running;
    public static Canvas mCanvas;
    private final String TAG = "MainThread";
    private boolean mLocked = false;

    public MainThread(SurfaceHolder holder, POIOnlyView pOIView) {
        super();
        this.mSurfaceHolder = holder;
        this.mPOIOnlyView = pOIView;
        Log.d(TAG, "Constructeur");
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    @Override
    public void run() {


        while (running) {

            mCanvas = null;

                try {
                    if(!mLocked) {
                        mCanvas = this.mSurfaceHolder.lockCanvas();
                        mLocked=true;
                        synchronized (mSurfaceHolder) {
                            Thread.sleep(20);
                            this.mPOIOnlyView.update();
                            this.mPOIOnlyView.draw(mCanvas);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                }
             finally {
                        if (mCanvas != null) {
                            try {
                                if (mLocked) {
                                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                                    mLocked = false;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                }
            }
        }
}