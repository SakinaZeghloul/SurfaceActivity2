package openclass.tp.nfa024.cnam.fr.surfaceactivity.Modèle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class POIObject {

    public Rect rectangle;
    public int color;


    public POIObject(Rect rectangle, int color)
    {
        this.rectangle=rectangle;
        this.color=color;
    }

    public Rect getRectangle() {
        return rectangle;
    }


    public void draw(Canvas canvas)
    {
        Paint paint=new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    public void update(Point point)
    {
        rectangle.set(point.x-rectangle.width()/2, point.y-rectangle.height()/2,
                point.x+rectangle.width()/2, point.y+rectangle.height()/2);
    }
}
