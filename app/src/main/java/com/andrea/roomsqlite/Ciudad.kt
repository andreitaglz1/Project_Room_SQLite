package com.andrea.roomsqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("tblCiudad")
data class Ciudad(
    @PrimaryKey(true)
    val id: Int,

    @ColumnInfo(name = "nombre")
    val nombre : String,

    @ColumnInfo(name = "poblacion")
    val poblacion : Int

)