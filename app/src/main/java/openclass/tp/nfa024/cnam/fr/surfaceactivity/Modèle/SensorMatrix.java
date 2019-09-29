package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;



public class SensorMatrix implements SensorEventListener {

    private Display mDisplay;

    float[] mAccelerometerData = new float[3];
    float[] mMagnetometerData = new float[3];

    float[] rotationMatrixAdjusted = new float[9];
    float[] mRotationMatrix = new float[9];

    float[] orientationAngles=new float[3];
    float[] startOrientation=null;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnometer;

    private final String TAG = "mSensorMatrix";

    public float[] getOrientation()
    {
        return orientationAngles;
    }

    public float[] getStartOrientation()
    {
        return startOrientation;
    }

    public void newGame(){
        startOrientation=null;
    }

    public SensorMatrix()
    {
        mSensorManager=(SensorManager)Constantes.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnometer=mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = event.values;
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = event.values;
                break;

            default:
                return;
        }


        switch (event.sensor.getType()) {
            case Surface.ROTATION_0:
                rotationMatrixAdjusted = mRotationMatrix.clone();
                break;

            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_Y,
                        SensorManager.AXIS_MINUS_X, rotationMatrixAdjusted);
                break;

            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_X,
                        SensorManager.AXIS_MINUS_Y, rotationMatrixAdjusted);
                break;

            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_Y,
                        SensorManager.AXIS_X, rotationMatrixAdjusted);
                break;
        }

        if (mAccelerometerData != null && mMagnetometerData != null) {

            boolean succes = SensorManager.getRotationMatrix(mRotationMatrix, rotationMatrixAdjusted, mAccelerometerData,
                    mMagnetometerData);

            if (succes) {
                SensorManager.getOrientation(mRotationMatrix, orientationAngles);


                orientationAngles[0] = (float) Math.toDegrees(orientationAngles[0]);
                orientationAngles[1] = (float) Math.toDegrees(orientationAngles[1]);
                orientationAngles[2] = (float) Math.toDegrees(orientationAngles[2]);

                if (startOrientation == null) {
                    startOrientation = new float[orientationAngles.length];
                    System.arraycopy(orientationAngles, 0, startOrientation, 0, orientationAngles.length);
                }
                /**
                    Log.i(TAG, "Orientation[0]" + getOrientation()[0]);
                    Log.i(TAG, "Orientation[1]" + getOrientation()[1]);
                    Log.i(TAG, "Orientation[2]" + getOrientation()[2]);
                */
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void register(){
        mSensorManager.registerListener(this, mAccelerometer, mSensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagnometer, mSensorManager.SENSOR_DELAY_GAME);
    }

    public void pause()
    {
        mSensorManager.unregisterListener(this);
    }
}
