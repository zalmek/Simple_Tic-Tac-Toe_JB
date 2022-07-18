package tictactoe

import kotlin.math.abs

fun defineState(x: Int, o: Int, spaces: Int, currentStateList: MutableList<MutableList<Char>>): Boolean {
    if (abs(x - o) >= 2) {
        // println("Impossible")
        return false
    }
    var result: Char? = null
    for (i in 0..2) {
        if (currentStateList[i][0] == currentStateList[i][1] && currentStateList[i][1] == currentStateList[i][2]) {
            if (result != null || currentStateList[i][0]==' ') {
                // println("Impossible")
                return false
            }
            result = currentStateList[i][0]
        }
    }
    for (i in 0..2) {
        if (currentStateList[0][i] == currentStateList[1][i] && currentStateList[1][i] == currentStateList[2][i]) {
            if (result != null || currentStateList[0][i] ==' ') {
                // println("Impossible")
                return false
            }
            result = currentStateList[0][i]
        }
    }
    if (currentStateList[0][0] == currentStateList[1][1] && currentStateList[1][1] == currentStateList[2][2]) {
        if (result != null || currentStateList[0][0]==' ') {
            //println("Impossible")
            return false
        }
        result = currentStateList[0][0]
    }
    if (currentStateList[2][0] == currentStateList[1][1] && currentStateList[1][1] == currentStateList[0][2]) {
        if (result != null || currentStateList[2][0]==' ') {
            // println("Impossible")
            return false
        }
        result = currentStateList[2][0]
    }

    if (result == null) {
        if (spaces > 0) {
            //  println("Game not finished")
            return false
        } else {
            println("Draw")
            return true
        }
    } else {
        println("$result wins")
        return true
    }
}

fun move(x: Int, y: Int, turn: Int, currentStateList: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    while (true) {
        try {
            var movement = readln().split(" ").map { it.toInt() }
            if (movement[0] !in 1..3 || movement[1] !in 1..3) {
                println("Coordinates should be from 1 to 3!")
                continue
            }
            if (currentStateList[movement[0] - 1][movement[1] - 1] != ' ') {
                println("This cell is occupied! Choose another one!")
                continue
            }
            if (turn % 2 == 0) currentStateList[movement[0] - 1][movement[1] - 1] = 'O'
            else currentStateList[movement[0] - 1][movement[1] - 1] = 'X'
            break
        } catch (_: Exception) {
            println("You should enter numbers!")
            continue
        }
    }
    return currentStateList
}


fun main() {
    val input = "         "
    var xnums = 0
    var onums = 0
    var spaces = 9
    val list = mutableListOf<MutableList<Char>>()
    list.add(mutableListOf(input[0], input[1], input[2]))
    list.add(mutableListOf(input[3], input[4], input[5]))
    list.add(mutableListOf(input[6], input[7], input[8]))
    println("---------")
    println("| ${list[0][0]} ${list[0][1]} ${list[0][2]} |")
    println("| ${list[1][0]} ${list[1][1]} ${list[1][2]} |")
    println("| ${list[2][0]} ${list[2][1]} ${list[2][2]} |")
    println("---------")

    var i = 1

    while (true) {

        if (i%2==0){
            onums+=1
        }
        else
            xnums+=1
        move(xnums, onums, i, list)
        spaces-=1
        println("---------")
        println("| ${list[0][0]} ${list[0][1]} ${list[0][2]} |")
        println("| ${list[1][0]} ${list[1][1]} ${list[1][2]} |")
        println("| ${list[2][0]} ${list[2][1]} ${list[2][2]} |")
        println("---------")
        if (defineState(xnums, onums, spaces, list)) {
            break
        }
        i+=1
    }
}