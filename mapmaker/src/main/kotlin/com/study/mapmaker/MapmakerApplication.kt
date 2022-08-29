package com.study.mapmaker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MapmakerApplication

fun main(args: Array<String>) {
    runApplication<MapmakerApplication>(*args)
    try {
        val errorIsland = IslandMaker(0,0)
    } catch (e: Exception) {
        println("init 값 에러: ${e.message}")
    }

    val island = IslandMaker(3,4)
    island.getMatrix()

    println("생성된 섬 개수: ${island.generateIsland(3)}")
    println("생성된 섬 개수: ${island.generateIsland(4)}")
    println("생성된 섬 개수: ${island.generateIsland(10)}")
    println("생성된 섬 개수: ${island.generateIsland(1)}")


}
