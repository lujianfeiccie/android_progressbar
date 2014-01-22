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
	private String tag = "MyProgressBar";  
    private String text="";  
    private Paint mPaint;
    private int textColor = Color.BLACK;
    private int textSize = 20;
    private int max = 100;
    private Context context = null;
    
    public static final int STATUS_SELLED = 101;
    public static final int STATUS_FAILED = 102;
    public MyProgressBar (Context context) {  
        this(context,null);  
    }  
  
    public MyProgressBar (Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        this.context = context;
        setMax(max);
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
    	if(progress == STATUS_SELLED){  
        	this.text = String.format("已售");
        	setProgressDrawable(context.getResources().getDrawable(R.drawable.barcolor_selled));
        	return;
        }
    	if(progress == STATUS_FAILED){  
    		this.text = String.format("发布失败");
    		setProgressDrawable(context.getResources().getDrawable(R.drawable.barcolor_failed));
    		return;
    	}
    	
        float i = (float)(progress * 100) / (float)this.getMax();  
        if(i>=100){
                this.text = String.format("在售中");
                setProgressDrawable(context.getResources().getDrawable(R.drawable.barcolor_finish));
        }else if(i>0){
                this.text = String.format("发布中 %s%%", (int)i);
        }
    } 
    void log(String msg){
            Log.d(tag , msg);
    }
}


