package me.kaique.application

import me.kaique.application.web.MallEntryPoint

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        MallEntryPoint.init()
    }
}