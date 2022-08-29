package com.study.mapmaker

import java.lang.RuntimeException

class IslandMaker(private val rowSize: Int,
                  private val columnSize: Int) {

    // init : 0으로 채워진 IntArray
    private var matrix = Array(rowSize) { IntArray(columnSize) }
    private var sum = 0
    init {
        if (rowSize <2 || rowSize > 10000 || columnSize <2 || columnSize > 10000) {
            throw RuntimeException("IslandMaker를 생성할 수 없습니다. m, n의 값은 2이상 10000이하의 정수여야 합니다.")
        }
    }

    fun getMatrix() {
        println("current Map >>>")
        for (row in matrix) {
            println(row.contentToString())
        }
    }

    fun generateIsland(n:Int): Int {
        // 랜덤하게 생성하고 싶을 것 같아서 랜덤생성
        var cnt = n

        // 남은 공간
        val emptySpace: () -> Int = {
            val empty = (rowSize * columnSize) - (sum)
            if (empty == 0) {println("[FAIL - generateIsland] 섬을 추가할 공간이 없습니다.")}
            empty
        }

        while (cnt > 0) {
            // 남은 공간 없을시 break
            if (emptySpace() <= 0) {
                break
            }
            // 섬 추가
            val rowIndex = (0..rowSize-1).random()
            val columnIndex = (0..columnSize-1).random()
            if (matrix[rowIndex][columnIndex] != 1){
                matrix[rowIndex][columnIndex] = 1
                cnt -= 1
                sum += 1
            }
        }
        println("generateIsland result")
        getMatrix()
        return n - cnt
    }

}