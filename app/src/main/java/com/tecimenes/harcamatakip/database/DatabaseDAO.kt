package com.tecimenes.harcamatakip.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DatabaseDAO
{
    @Insert
    suspend fun insert(expense: Harcama)

    @Delete
    suspend fun delete(expense: Harcama)

    @Query("SELECT * FROM harcamalar WHERE id = :id")
    suspend fun get(id: Long): Harcama?

    @Query("SELECT * FROM harcamalar ORDER BY id DESC")
    fun tumHarcamalar(): LiveData<List<Harcama>>

    @Query("DELETE FROM harcamalar")
    suspend fun clear()
}