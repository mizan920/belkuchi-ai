package com.example

import android.app.Application
import com.example.data.db.AppDatabase
import com.example.data.repository.BelkuchiRepository
import com.example.data.service.LocationHelper
import com.example.data.service.SpeechManager

class BelkuchiApp : Application() {

    lateinit var database: AppDatabase
        private set

    lateinit var locationHelper: LocationHelper
        private set

    lateinit var speechManager: SpeechManager
        private set

    lateinit var repository: BelkuchiRepository
        private set

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
        locationHelper = LocationHelper(this)
        speechManager = SpeechManager(this)
        repository = BelkuchiRepository(database, locationHelper)
    }

    override fun onTerminate() {
        super.onTerminate()
        speechManager.destroy()
    }
}
