package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {

    launch {
        delay(1000)
        println("코루틴 실행")
    }

    println("main 실행")
}