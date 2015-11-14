package com.kmia.nbfids.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by mac86cy on 15/11/14.
 */
public class MyImageButton extends ImageButton {
    private String _text = "";
    private int _color = 0;
    private float _textsize = 0f;

    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setText(String _text) {
        this._text = _text;
    }

    public void setColor(int _color) {
        this._color = _color;
    }

    public void setTextSize(float _textsize) {
        this._textsize = _textsize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextAlign(Align.CENTER);
        paint.setColor(_color);
        paint.setTextSize(_textsize);
        canvas.drawText(_text, canvas.getWidth() / 2, (canvas.getHeight() / 2) + 20, paint);
    }
}
