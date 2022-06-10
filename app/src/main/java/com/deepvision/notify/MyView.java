//package com.deepvision.notify;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
//public class MyView extends View {
//
//    private  Path path = new Path();
//    private Paint paint = new Paint();
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        canvas.drawPath(path, paint);
//    }
//
//
//    public boolean onTouchEvent(MotionEvent event){
//        float pointX = event.getX();
//        float pointY = event.getY();
//
//        switch (event.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//                path.moveTo(pointX, pointY);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                path.lineTo(pointX,pointY);
//                break;
//            default: return false;
//        }
//
//        invalidate();
//        return true;
//    }
//
//
//
//
//    public MyView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//
//        paintSettings();
//
//    }
//
//
//    //Brush settings go here
//
//    private void paintSettings() {
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(10);
//    }
//}
