package com.tecimenes.harcamatakip

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tecimenes.harcamatakip.database.DatabaseDAO

class harcama_ekle_ViewModelFactory(private val veriKaynagi: DatabaseDAO, private val app: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(harcama_ekle_ViewModel::class.java))
            return harcama_ekle_ViewModel(veriKaynagi, app) as T
        throw IllegalArgumentException("ViewModel Not Found")
    }
}