package com.tecimenes.harcamatakip

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tecimenes.harcamatakip.database.DatabaseDAO
import com.tecimenes.harcamatakip.database.Harcama

class harcama_sil_viewModelFactory(private val veriKaynagi: DatabaseDAO, private val app: Application, private val harcama: Harcama, private val type: Int) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(harcama_sil_viewModel::class.java))
            return harcama_sil_viewModel(veriKaynagi, app, harcama, type) as T
        throw IllegalArgumentException("ViewModel Not Found")
    }
}