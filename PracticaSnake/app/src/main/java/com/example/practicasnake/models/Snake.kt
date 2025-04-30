package com.example.practicasnake.models

class Snake(
    var x: Int,
    var y: Int,
    var direction: Direction
) {
    val snakeBody: ArrayList<Pair<Int, Int>> = ArrayList()
    var boardWidth: Int = 0
    var boardHeight: Int = 0

    init {
        snakeBody.add(Pair(x, y))
        snakeBody.add(Pair(x - 1, y))
        snakeBody.add(Pair(x - 2, y))
    }

    private fun moveRight() {
        x++
        if (x >= boardWidth) {
            x = 0
        }
        snakeBody.add(Pair(x, y))
        snakeBody.removeAt(0)
    }

    private fun moveLeft() {
        x--
        if (x < 0) {
            x = boardWidth - 1
        }
        snakeBody.add(Pair(x, y))
        snakeBody.removeAt(0)

    }

    private fun moveUp() {
        y--
        if (y < 0) {
            y = boardHeight - 1
        }

        snakeBody.add(Pair(x, y))
        snakeBody.removeAt(0)

    }

    private fun moveDown() {
        y++
        if (y >= boardHeight) {
            y = 0
        }
        snakeBody.add(Pair(x, y))
        snakeBody.removeAt(0)
    }


    fun move() {
        when (this.direction) {
            Direction.UP -> moveUp()
            Direction.DOWN -> moveDown()
            Direction.LEFT -> moveLeft()
            Direction.RIGHT -> moveRight()
        }
    }

    fun changeDirection(newDirection: Direction) {
        if (this.direction == Direction.UP && newDirection == Direction.DOWN) return
        if (this.direction == Direction.DOWN && newDirection == Direction.UP) return
        if (this.direction == Direction.LEFT && newDirection == Direction.RIGHT) return
        if (this.direction == Direction.RIGHT && newDirection == Direction.LEFT) return
        this.direction = newDirection
    }

    fun checkCollision(fruit: Fruit): Boolean {
        return x == fruit.x && y == fruit.y
    }

    fun checkCollisionWithBody(): Boolean {
        for (i in 0 until snakeBody.size - 1) {
            if (x == snakeBody[i].first && y == snakeBody[i].second) {
                return true
            }
        }
        return false
    }

    fun grow(fruit: Fruit) {
        snakeBody.add(Pair(fruit.x, fruit.y))
    }

    fun setBoardSize(boardWidth: Int, boardHeight: Int) {
        this.boardWidth = boardWidth
        this.boardHeight = boardHeight
    }

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}