package com.tecimenes.harcamatakip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tecimenes.harcamatakip.database.AppDatabase
import com.tecimenes.harcamatakip.databinding.FragmentHarcamaEkleBinding

class harcama_ekle : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val binding: FragmentHarcamaEkleBinding = FragmentHarcamaEkleBinding.inflate(inflater, container, false)
        val app = requireNotNull(this.activity).application
        val veriKaynagi = AppDatabase.getInstance(app).databaseDao
        val viewModelFactory = harcama_ekle_ViewModelFactory(veriKaynagi, app)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(harcama_ekle_ViewModel::class.java)

        binding.ekleButonu.setOnClickListener {
            val aciklama = binding.aciklama.text.toString()
            val harcamaTutari = binding.harcama.text.toString()
            val harcamaTuru = when(binding.radioGroup.checkedRadioButtonId){
                R.id.faturaButon -> 1
                R.id.kiraButon -> 2
                R.id.digerButon -> 3
                else -> -1
            }
            val dovizSimge = when(binding.radioGroup2.checkedRadioButtonId){
                R.id.he_tlButon -> 1
                R.id.he_dolarButon -> 2
                R.id.he_sterlinButon -> 3
                R.id.he_zlotyButon -> 4
                else -> 0
            }
            viewModel.eveDonus.observe(viewLifecycleOwner, {
                if(it == true)
                {
                    Toast.makeText(activity,"Harcama Eklendi", Toast.LENGTH_SHORT).show()
                    this.findNavController().navigate(harcama_ekleDirections.actionHarcamaEkleToAnaSayfa())
                    viewModel.navigationOkay()
                }
            })

            if (binding.radioGroup.checkedRadioButtonId != -1 && aciklama.isNotEmpty() && binding.radioGroup2.checkedRadioButtonId != -1 && harcamaTutari.isNotEmpty()){
                viewModel.harcamaEkle(harcamaTuru, aciklama, dovizSimge, harcamaTutari)
            } else{
                Toast.makeText(activity,"Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

}