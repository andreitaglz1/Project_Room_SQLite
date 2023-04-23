package com.andrea.roomsqlite

import android.app.Application
import com.example.android.roomwordssample.CiudadRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CiudadApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {
        CiudadRoomDatabase.getDatabase(this,applicationScope)
    }
    val repository by lazy{
        CiudadRepository(database.ciudadDao())
    }
}
