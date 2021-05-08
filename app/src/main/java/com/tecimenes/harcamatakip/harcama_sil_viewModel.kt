package com.tecimenes.harcamatakip

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecimenes.harcamatakip.database.DatabaseDAO
import com.tecimenes.harcamatakip.database.Harcama
import kotlinx.coroutines.launch

private const val sp: String = "SharedPreferences"
private const val secilenTRY: String = "degerTRY"
private const val secilenUSD: String = "degerUSD"
private const val secilenGBP: String = "degerGBP"
private const val secilenPLN: String = "degerPLN"

class harcama_sil_viewModel(veriKaynagi: DatabaseDAO, app: Application, secilenHarcama: Harcama, secilenHarcamaTuru: Int) : ViewModel()
{
    private val sharedPreferences: SharedPreferences = app.getSharedPreferences(sp, Context.MODE_PRIVATE)
    private val database = veriKaynagi
    private val harcama = secilenHarcama
    private val type = secilenHarcamaTuru

    private val _cost = MutableLiveData<String>()
    val cost: LiveData<String>
        get() = _cost

    private val _eveDonus = MutableLiveData<Boolean?>()
    val eveDonus: LiveData<Boolean?>
        get() = _eveDonus

    init {
        harcamaDetay()
    }

    private fun harcamaDetay()
    {
        val degerTRY = sharedPreferences.getFloat(secilenTRY, 9.95F)
        val degerUSD = sharedPreferences.getFloat(secilenUSD, 1.21F)
        val degerGBP = sharedPreferences.getFloat(secilenGBP, 0.86F)
        val degerPLN = sharedPreferences.getFloat(secilenPLN, 4.57F)

        val cost = when(type)
        {
            1 -> "%.0f".format(harcama.cost * degerTRY)
            2 -> "%.0f".format(harcama.cost * degerUSD)
            3 -> "%.0f".format(harcama.cost * degerGBP)
            4 -> "%.0f".format(harcama.cost * degerPLN)
            else -> "%.0f".format(harcama.cost)
        }

        val costString = when(type)
        {
            1 -> "$cost ₺"
            2 -> "$cost $"
            3 -> "$cost £"
            4 -> "$cost zł"
            else -> "$cost €"
        }
        _cost.value = costString
    }

    fun delete()
    {
        viewModelScope.launch {
            database.delete(harcama)
            eveGeriDonus()
        }
    }

    fun eveGeriDonus() { _eveDonus.value = true }

    fun navigationOkay() { _eveDonus.value = null }
}