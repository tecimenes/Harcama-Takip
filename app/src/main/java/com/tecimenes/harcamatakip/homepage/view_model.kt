package com.tecimenes.harcamatakip.homepage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tecimenes.harcamatakip.api.Api
import com.tecimenes.harcamatakip.api.doviz_api
import com.tecimenes.harcamatakip.database.DatabaseDAO
import com.tecimenes.harcamatakip.database.Harcama
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val sp: String = "SharedPreferences"
private const val secilenIsim: String = "kullaniciIsim"
private const val secilenCinsiyet: String = "kullaniciCinsiyet"
private const val secilenTRY: String = "degerTRY"
private const val secilenUSD: String = "degerUSD"
private const val secilenGBP: String = "degerGBP"
private const val secilenPLN: String = "degerPLN"

class view_model(dataSource: DatabaseDAO, applicationMain: Application) : ViewModel()
{
    private val application = applicationMain
    private var sharedPreferences: SharedPreferences = application.getSharedPreferences(sp, Context.MODE_PRIVATE)
    private val database = dataSource
    var harcamalar = database.tumHarcamalar()

    private var _anlikDoviz = MutableLiveData(1)
    val anlikDoviz: LiveData<Int>
        get() = _anlikDoviz

    private val degerTRY = sharedPreferences.getFloat(secilenTRY, 9.97F)
    private var degerUSD = sharedPreferences.getFloat(secilenUSD, 1.20F)
    private var degerGBP = sharedPreferences.getFloat(secilenGBP, 0.87F)
    private var degerPLN = sharedPreferences.getFloat(secilenPLN,4.57F)

    private val _kullaniciIsim = MutableLiveData<String>()
    val kullaniciIsim: LiveData<String>
        get() = _kullaniciIsim

    private val _kullaniciCinsiyet = MutableLiveData<Int>()
    val kullaniciCinsiyet: LiveData<Int>
        get() = _kullaniciCinsiyet

    private val _toplamHarcama = MutableLiveData<String>()
    val toplamHarcama: LiveData<String>
        get() = _toplamHarcama

    private val _toHarcamaSil = MutableLiveData<Harcama>()
    val toHarcamaSil: LiveData<Harcama>
        get() = _toHarcamaSil

    init {
        kullaniciBilgileri()
        veriGuncelle()
    }

    private fun kullaniciBilgileri()
    {
        _kullaniciIsim.value = sharedPreferences.getString(secilenIsim, "İsminizi Giriniz")
        _kullaniciCinsiyet.value = sharedPreferences.getInt(secilenCinsiyet, -1)
    }

    fun dovizCek(): Int {
        return this._anlikDoviz.value?:1
    }

    fun dovizAyarla(newRate: Int) {
        this._anlikDoviz.value = newRate
    }

    fun guncelVeriCek(): Float
    {
        return when(this._anlikDoviz.value) {
            1 -> degerTRY
            2 -> degerUSD
            3 -> degerGBP
            4 -> degerPLN
            else -> 1f
        }
    }

    fun toplamHarcamaHesapFonk(sumFloat: Float)
    {
        val sum = when(dovizCek())
        {
            1 -> "%.0f".format(sumFloat * degerTRY)
            2 -> "%.0f".format(sumFloat * degerUSD)
            3 -> "%.0f".format(sumFloat * degerGBP)
            4 -> "%.0f".format(sumFloat * degerPLN)
            else -> "%.0f".format(sumFloat)
        }
        _toplamHarcama.value = when(dovizCek())
        {
            1 -> "$sum ₺"
            2 -> "$sum $"
            3 -> "$sum £"
            4 -> "$sum zł"
            else -> "$sum €"
        }
    }

    private fun veriGuncelle()
    {
        Api.retrofitService.dovizKurlari().enqueue(
            object: Callback<doviz_api> {
                override fun onResponse(call: Call<doviz_api>, response: Response<doviz_api>) {
                    with(sharedPreferences.edit())
                    {
                        response.body()?.conversion_rates?.TRY?.let { putFloat(secilenTRY, it) }
                        response.body()?.conversion_rates?.USD?.let { putFloat(secilenUSD, it) }
                        response.body()?.conversion_rates?.GBP?.let { putFloat(secilenGBP, it) }
                        response.body()?.conversion_rates?.PLN?.let { putFloat(secilenPLN, it) }
                        apply()
                    }
                }

                override fun onFailure(call: Call<doviz_api>, t: Throwable)
                {
                    Log.e("API ERROR", t.message?:"Unknown Error")
                }
            }
        )
    }

    fun harcamaTik(harcama: Harcama) { _toHarcamaSil.value = harcama }

    fun navigationOkay() { _toHarcamaSil.value = null }
}