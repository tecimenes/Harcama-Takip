package com.tecimenes.harcamatakip

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val sp: String = "SharedPreferences"
private const val secilenIsim: String = "kullaniciIsim"
private const val secilenCinsiyet: String = "kullaniciCinsiyet"

class isim_degistir_viewModel(app: Application) : ViewModel()
{
    private val sharedPreferences = app.getSharedPreferences(sp, Context.MODE_PRIVATE)
    private val _kullaniciIsim = MutableLiveData<String>()
    val kullaniciIsim: LiveData<String>
        get() = _kullaniciIsim
    private val _kullaniciCinsiyet = MutableLiveData<Int>()
    val kullaniciCinsiyet: LiveData<Int>
        get() = _kullaniciCinsiyet
    private val _eveDonus = MutableLiveData<Boolean?>()
    val eveDonus: LiveData<Boolean?>
        get() = _eveDonus

    init { kullanicidanAl() }

    private fun kullanicidanAl()
    {
        _kullaniciIsim.value = sharedPreferences.getString(secilenIsim,"")
        _kullaniciCinsiyet.value = sharedPreferences.getInt(secilenCinsiyet, -1)
    }

    fun kullaniciBilgiKaydet(isim: String, cinsiyet: Int)
    {
        if(isim != "")
        {
            with(sharedPreferences.edit()) {
                putString(secilenIsim, isim)
                putInt(secilenCinsiyet, cinsiyet)
                apply()
            }

            _eveDonus.value = true
        }
    }

    fun navigationOkay() { _eveDonus.value = null }
}