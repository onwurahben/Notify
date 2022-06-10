package com.deepvision.notify

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val path = Path()    //coordinate system
    private val paint = Paint()  //brush


    //must be overridden to draw on screen
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    //follows users motion
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)
            else -> return false
        }
        invalidate()
        return true
    }

    //Brush settings go here
    private fun paintSettings() {
        paint.color = Color.BLUE
        paint.strokeWidth = 10f
    }

    //equivalent of running code in class constructor(java)
    init {
        isFocusable = true
        isFocusableInTouchMode = true
        paintSettings()
    }
}