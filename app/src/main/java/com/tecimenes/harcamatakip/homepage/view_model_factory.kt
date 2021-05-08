package com.tecimenes.harcamatakip.homepage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tecimenes.harcamatakip.database.DatabaseDAO

class view_model_factory(private val dataSource: DatabaseDAO, private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(view_model::class.java))
            return view_model(dataSource, application) as T
        throw IllegalArgumentException("ViewModel Not Found")
    }
}