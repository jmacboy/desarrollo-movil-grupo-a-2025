package com.example.practicaviewdraw.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Paint
import android.graphics.Paint.Style
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.sqrt

class Board(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var xOrigin = 0f
    private var yOrigin = 0f
    private var xFinal = 0f
    private var yFinal = 0f
    private var shape = Shape.NONE
    private val paint = Paint().apply {
        color = BLUE
        style = Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (shape == Shape.NONE) return

        when (shape) {
            Shape.RECTANGLE -> drawRectangle(canvas)
            Shape.CIRCLE -> drawCircle(canvas)
            Shape.LINE -> drawLine(canvas)
            else -> {}
        }

    }

    private fun drawLine(canvas: Canvas) {
        paint.color = Color.WHITE
        canvas.drawLine(
            xOrigin,
            yOrigin,
            xFinal,
            yFinal,
            paint
        )
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = Color.RED
        val distance = sqrt(
            ((xFinal - xOrigin) * (xFinal - xOrigin) + (yFinal - yOrigin) * (yFinal - yOrigin)).toDouble()
        )
        canvas.drawCircle(
            xOrigin,
            yOrigin,
            distance.toFloat(),
            paint
        )
    }

    private fun drawRectangle(canvas: Canvas) {
        paint.color = Color.BLUE
        canvas.drawRect(
            xOrigin,
            yOrigin,
            xFinal,
            yFinal,
            paint
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                xOrigin = event.x
                yOrigin = event.y
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                xFinal = event.x
                yFinal = event.y
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
//                xFinal = event.x
//                yFinal = event.y
                invalidate()
            }
        }
        return true
    }

    fun setShape(shape: Shape) {
        this.shape = shape
    }
}