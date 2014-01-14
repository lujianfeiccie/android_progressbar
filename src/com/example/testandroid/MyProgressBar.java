package com.example.testandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

public class MyProgressBar extends ProgressBar {
	String text;  
    Paint mPaint;
    int textColor = Color.BLACK;
    int textSize = 40;
	private String tag = "MyProgressBar";  
  
	private Context context = null;
    public MyProgressBar (Context context) {  
        this(context,null);  
    }  
  
    public MyProgressBar (Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        this.context = context;
        initText();  
    }  
  
    public MyProgressBar (Context context, AttributeSet attrs) {  
        this(context, attrs,0);  
    }  
  
    @Override  
    public synchronized void setProgress(int progress) {  
    	setText(progress);
        super.setProgress(progress);  
    }  
  
    @Override  
    protected synchronized void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
//this.setText();  
        Rect rect = new Rect(0,0,400,400);  
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);  
        int x = (getWidth() / 2) - rect.centerX();  
  
        int y = (getHeight() / 2) - rect.centerY();  
        canvas.drawText(this.text, x, y, this.mPaint);  
    }  
  
//初始化，画笔  
    private void initText() {  
        this.mPaint = new Paint();  
        this.mPaint.setColor(textColor);  
        this.mPaint.setTextSize(textSize);  
    }  
    public void setTextColor(int color){
    	this.textColor = color;
    	updateTextStyle();
    }
    public void setTextSize(int textSize){
    	this.textSize = textSize;
    	updateTextStyle();
    }
    private void updateTextStyle(){
    	this.mPaint.setColor(textColor);
    	this.mPaint.setTextSize(textSize);
    }
  
    private void setText(int progress) {  
        float i = (float)(progress * 100) / (float)this.getMax();  
        if(i>=100){
        	this.text = String.format("发布成功");
        	setProgressDrawable(context.getResources().getDrawable(R.drawable.barcolor_finish));
        }else{
        	this.text = String.format("发布中 %s%%", (int)i);
        }
    } 
    void log(String msg){
    	Log.d(tag , msg);
    }
}
