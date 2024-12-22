package ali.company.camdetector.helpers;

import android.graphics.Rect;
import android.graphics.RectF;

public class BoxWithText {

    public Rect rect;
    public String text;

    public BoxWithText(Rect rect, String label) {
        this.rect = rect;
        this.text = label;
    }

    public BoxWithText(String displayName, RectF boundingBox) {
        this.text = displayName;
        this.rect = new Rect();
        boundingBox.round(rect);
    }


}
