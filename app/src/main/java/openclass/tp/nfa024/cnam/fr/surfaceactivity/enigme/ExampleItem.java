package openclass.tp.nfa024.cnam.fr.surfaceactivity.enigme;

public class ExampleItem {

    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageResource, String text1, String text2) {

        mImageResource=imageResource;
        mText1=text1;
        mText2=text2;

    }

    public int getImageResource(){
        return mImageResource;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public String getText1() {
        return mText1;
    }

    public void setText1(String text1) {
        mText1 = text1;
    }

    public String getText2() {
        return mText2;
    }

    public void setText2(String text2) {
        mText2 = text2;
    }
}
