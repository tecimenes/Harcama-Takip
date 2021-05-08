package com.tecimenes.harcamatakip.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "harcamalar")
data class Harcama(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val type: Int,
    val description: String,
    var cost: Float) : Parcelable