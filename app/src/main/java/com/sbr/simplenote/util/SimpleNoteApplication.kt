package com.sbr.simplenote.util

import android.app.Application
import com.sbr.simplenote.model.database.WordRoomDatabase
import com.sbr.simplenote.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SimpleNoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SimpleNoteApplication
            private set
    }
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val databases by lazy { WordRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { WordRepository(databases.wordDao()) }
}