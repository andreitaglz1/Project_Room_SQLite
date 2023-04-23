package com.andrea.roomsqlite

import kotlinx.coroutines.flow.Flow

class CiudadRepository(private val ciudadDao: CiudadDao) {

    val allCiudad: Flow<List<Ciudad>> = ciudadDao.getAll()

    suspend fun insert(ciudad: Ciudad){
        ciudadDao.insertCiudad(ciudad)
    }
}