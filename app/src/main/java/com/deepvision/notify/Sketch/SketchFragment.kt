package com.deepvision.notify.Sketch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deepvision.notify.R


/**

 */
class SketchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sketch, container, false)
    }

//    class Sketch(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
//
//        private  val path = Path()
//        private val paint = Paint()
//
//
//        override fun setFocusableInTouchMode(focusableInTouchMode: Boolean) {
//            super.setFocusableInTouchMode(focusableInTouchMode)
//        }
//
//        override fun setFocusable(focusable: Boolean) {
//            super.setFocusable(focusable)
//        }
//
//
//        override fun onDraw(canvas: Canvas?) {
//            super.onDraw(canvas)
//        }
//    }


}