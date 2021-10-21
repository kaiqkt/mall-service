package me.kaique.application.web.routes

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object RouterManager: KoinComponent {

    private val storeRoutes: StoreRoutes by inject()

    fun registerRoutes() {
        storeRoutes.register()
    }
}