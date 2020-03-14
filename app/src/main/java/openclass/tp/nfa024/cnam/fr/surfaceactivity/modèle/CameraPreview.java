package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

import openclass.tp.nfa024.cnam.fr.surfaceactivity.constantes.Constantes;

import static android.content.Context.WINDOW_SERVICE;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{

    SurfaceHolder mSurfaceHolder;
    SurfaceView mSurfaceView;
    private Camera mCamera;
    private final String TAG = "TresorViewBitmap: ";
    private Camera.CameraInfo mInfo;
    private Display mDisplay;
    private boolean flashLightStatus;


    public CameraPreview(Context context)
    {
        super(context);
        mSurfaceHolder=getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceView=new SurfaceView(context);

        Constantes.CURRENT_CONTEXT=context;

        setFocusable(true);
    }

    public CameraPreview(Context context, SurfaceView filtered)
    {
        super(context);
        mSurfaceView=filtered;

        mSurfaceHolder=getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        WindowManager mWindowManager=(WindowManager) context.getSystemService(WINDOW_SERVICE);
        mDisplay=mWindowManager.getDefaultDisplay();

        Log.i(TAG, "Constructeur");

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        try {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);

            Log.i(TAG, "Surface en train de se créer");

            Camera.Parameters params = mCamera.getParameters();
            List<String> focusModes = params.getSupportedFocusModes();

            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO))
        {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            mCamera.setParameters(params);
        }

            mCamera.setPreviewDisplay(holder);
            params.setPreviewSize(mSurfaceView.getWidth(), mSurfaceView.getHeight());
            mCamera.startPreview();

        }catch (Exception e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void ledFlashOut(){

        Camera.Parameters params = mCamera.getParameters();

        Log.i("info", "torch is turn off!");
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCamera.setParameters(params);
    }


    public void ledFlashIn(){

        Camera.Parameters params = mCamera.getParameters();

        Log.i("info", "torch is turn on!");
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCamera.setParameters(params);
        mCamera.startPreview();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                Log.i(TAG, "SurfaceChanged");
            }
        }

        int orientation=getCameraCorrectOrientation();

        mCamera.setDisplayOrientation(orientation);
        mCamera.getParameters().setRotation(orientation);

        Camera.Parameters params= mCamera.getParameters();
        Camera.Size size=getOptimalPreviewSize(width, height);

        if(size.width==mSurfaceView.getWidth() && size.height==mSurfaceView.getHeight()){
            params.setPreviewSize(size.width, size.height);
            mCamera.setParameters(params);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        if (mCamera != null)
            mCamera.release();
        mCamera = null;
    }

    public int getCameraCorrectOrientation()
    {
        Camera.CameraInfo info=new Camera.CameraInfo();
        Camera.getCameraInfo(
                Camera.CameraInfo.CAMERA_FACING_BACK, info);

        int degrees=getWindowRotation();

        return (info.orientation - degrees + 360) % 360;

    }

    public int getWindowRotation()
    {
        int rotation=mDisplay.getRotation();

        switch(rotation){
            case Surface.ROTATION_0: return 0;
            case Surface.ROTATION_90: return 90;
            case Surface.ROTATION_180: return 180;
            case Surface.ROTATION_270: return 270;
            default: return 0;
        }
    }

    public Camera.Size getOptimalPreviewSize(int width, int height)
    {
        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        for (Camera.Size size : sizes) {
            if (Math.abs(size.width - width) / width < 0.1 &&
                    Math.abs(size.height - height) / height < 0.1) {
                return size;
            }
        }
        return sizes.get(0);
    }
}



