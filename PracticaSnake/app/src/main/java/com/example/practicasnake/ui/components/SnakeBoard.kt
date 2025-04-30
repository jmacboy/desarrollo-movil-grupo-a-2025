package com.example.practicasnake.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.practicasnake.models.Fruit
import com.example.practicasnake.models.Snake

class SnakeBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var boardWidth = 20
    private var boardHeight = 40
    private var snake: Snake = Snake(2, 2, Snake.Direction.RIGHT)
    private var fruit = generateFruit()
    private val snakeWidth = 50
    private val snakeHeight = 50
    private val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        boardWidth = width / snakeWidth
        boardHeight = height / snakeHeight
        snake.setBoardSize(boardWidth, boardHeight)
        paint.color = Color.WHITE
        canvas.drawRect(
            0f, 0f,
            (width).toFloat(),
            (height).toFloat(),
            paint
        )

        paint.color = Color.BLUE
        // Draw the snake on the canvas
        for (segment in snake.snakeBody) {

            canvas.drawRect(
                segment.first.toFloat() * snakeWidth,
                segment.second.toFloat() * snakeHeight,
                (segment.first * snakeWidth + snakeWidth).toFloat(),
                (segment.second * snakeHeight + snakeHeight).toFloat(),
                paint
            )
        }
        paint.color = Color.RED
        // Draw the fruit on the canvas
        canvas.drawRect(
            fruit.x.toFloat() * snakeWidth,
            fruit.y.toFloat() * snakeHeight,
            (fruit.x * snakeWidth + snakeWidth).toFloat(),
            (fruit.y * snakeHeight + snakeHeight).toFloat(),
            paint
        )

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            val newDirection = if (y < 300) {
                Snake.Direction.UP
            } else if (y > height - 300) {
                Snake.Direction.DOWN
            } else if (x < 200) {
                Snake.Direction.LEFT
            } else if (x > width - 200) {
                Snake.Direction.RIGHT
            } else {
                null
            }
            newDirection?.let { snake.changeDirection(it) }
        }
        return true
    }

    fun move() {
        snake.move()
        if (snake.checkCollision(fruit)) {
            snake.grow(fruit)
            fruit = generateFruit()
            invalidate()
            return
        }
        if (snake.checkCollisionWithBody()) {
            snake = Snake(2, 2, Snake.Direction.RIGHT)
            fruit = generateFruit()
        }
        invalidate()
    }

    private fun generateFruit(): Fruit {
        return Fruit(
            (0 until boardWidth).random(),
            (0 until boardHeight).random()
        )
    }
}