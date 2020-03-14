package openclass.tp.nfa024.cnam.fr.surfaceactivity.modèle;

import com.google.android.gms.maps.model.LatLng;

public class POI {

    private String id;
    private double latitude;
    private double longitude;
    private int capture;
    private int tag;
    private LatLng mLatLng;
    private int max;
    private int min;


    public POI(String id, int tag, LatLng latlng, double latitude, double longitude,
               int capture, int min, int max){
        super();
        this.id=id;
        this.tag=tag;
        this.mLatLng=latlng;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capture=capture;
        this.min=min;
        this.max=max;
    }

    public POI(String id, int tag, double latitude, double longitude,
               int capture, int min, int max){
        super();
        this.id=id;
        this.tag=tag;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capture=capture;
        this.min=min;
        this.max=max;
    }

    public POI(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTag(){ return tag; }

    public void setTag(int tag){this.tag=tag; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public int isCapture() {
        return capture;
    }

    public void setCapture(int capt) {
        this.capture = capt;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
