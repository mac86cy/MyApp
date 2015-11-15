package com.kmia.nbfids.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.widget.ImageButton;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：自定义ImageButton类，用于在ImageButton上显示文字
 *  
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
