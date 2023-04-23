
package com.example.android.roomwordssample

import android.content.Context
import androidx.compose.ui.text.android.animation.SegmentType
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andrea.roomsqlite.Ciudad
import com.andrea.roomsqlite.CiudadDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

    @Database(entities = [Ciudad :: class], version = 1)
abstract class CiudadRoomDatabase : RoomDatabase() {

    abstract fun ciudadDao(): CiudadDao

    companion object {
        @Volatile
        private var INSTANCE: CiudadRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CiudadRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CiudadRoomDatabase::class.java,
                    "ciudad_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(CiudadDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class CiudadDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.ciudadDao())
                    }
                }
            }
        }


        suspend fun populateDatabase(ciudadDao: CiudadDao) {
            var ciudad = Ciudad(1, "Granada", 1500)
            ciudadDao.insertCiudad(ciudad)
            ciudad= Ciudad(2, "Masaya", 1300)
            ciudadDao.insertCiudad(ciudad)
        }
    }
}
