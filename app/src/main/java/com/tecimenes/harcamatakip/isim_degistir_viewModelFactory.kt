package com.tecimenes.harcamatakip

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class isim_degistir_viewModelFactory(private val app: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(isim_degistir_viewModel::class.java))
            return isim_degistir_viewModel(app) as T
        throw IllegalArgumentException("ViewModel Not Found")
    }
}