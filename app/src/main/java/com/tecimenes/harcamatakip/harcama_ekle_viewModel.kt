package com.tecimenes.harcamatakip

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecimenes.harcamatakip.database.DatabaseDAO
import com.tecimenes.harcamatakip.database.Harcama
import kotlinx.coroutines.launch
import java.lang.Exception

private const val sp: String = "SharedPreferences"
private const val secilenTRY: String = "degerTRY"
private const val secilenUSD: String = "degerUSD"
private const val secilenGBP: String = "degerGBP"
private const val secilenPLN: String = "degerPLN"

class harcama_ekle_ViewModel(veriKaynagi: DatabaseDAO, app: Application) : ViewModel()
{
    private val sharedPreferences: SharedPreferences = app.getSharedPreferences(sp, Context.MODE_PRIVATE)

    private val database = veriKaynagi
    private val _eveDonus = MutableLiveData<Boolean?>()
    val eveDonus: LiveData<Boolean?>
        get() = _eveDonus

    fun harcamaEkle(harcamaTuru: Int, aciklama: String, paraBirimi: Int, harcamaTutari: String)
    {
        try
        {
            if(harcamaTuru != -1 && aciklama != "" && harcamaTutari != "")
            {
                var harcamaTutar = harcamaTutari.toFloat()
                if(harcamaTutar >= 0)
                {
                    val degerTRY= sharedPreferences.getFloat(secilenTRY, 9.95F)
                    val degerUSD = sharedPreferences.getFloat(secilenUSD, 1.21F)
                    val degerGBP = sharedPreferences.getFloat(secilenGBP, 0.86F)
                    val degerPLN = sharedPreferences.getFloat(secilenPLN, 4.57F)
                    harcamaTutar /= when(paraBirimi)
                    {
                        1 -> degerTRY
                        2 -> degerUSD
                        3 -> degerGBP
                        4 -> degerPLN
                        else -> 1f
                    }

                    viewModelScope.launch {
                        val harcama = Harcama(0L, harcamaTuru, aciklama, harcamaTutar)
                        insert(harcama)
                        _eveDonus.value = true
                    }
                }
            }
        }
        catch(ex: Exception) { Log.e("ERROR", "harcama_ekle_viewModel - harcamaEkle()") }
    }

    private suspend fun insert(harcama: Harcama) { database.insert(harcama) }

    fun navigationOkay() { _eveDonus.value = null }
}