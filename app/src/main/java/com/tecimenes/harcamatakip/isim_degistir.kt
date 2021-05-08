package com.tecimenes.harcamatakip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tecimenes.harcamatakip.databinding.FragmentIsimDegistirBinding

class isim_degistir : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = FragmentIsimDegistirBinding.inflate(inflater, container, false)
        val app = requireNotNull(this.activity).application
        val viewModelFactory = isim_degistir_viewModelFactory(app)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(isim_degistir_viewModel::class.java)

        viewModel.kullaniciIsim.observe(viewLifecycleOwner, {
            binding.isimEditText.setText(it)
        })

        binding.isimKaydetButon.setOnClickListener {
            val kullaniciIsim = binding.isimEditText.text.toString().trimEnd()
            val kullaniciCinsiyet = when(binding.radioGroupGender.checkedRadioButtonId)
            {
                binding.kadinButon.id -> 0
                binding.erkekButon.id -> 1
                binding.belistbutton.id -> 2

                else -> -1
            }
            if (kullaniciIsim.isNotEmpty() && binding.radioGroupGender.checkedRadioButtonId != -1){
                viewModel.kullaniciBilgiKaydet(kullaniciIsim, kullaniciCinsiyet)
            } else{
                Toast.makeText(activity,"Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.kullaniciCinsiyet.observe(viewLifecycleOwner, {
            if(it == 0)
                binding.radioGroupGender.check(binding.kadinButon.id)
            else if(it == 1)
                binding.radioGroupGender.check(binding.erkekButon.id)
            else if(it == 2)
                binding.radioGroupGender.check(binding.belistbutton.id)
        })

        viewModel.eveDonus.observe(viewLifecycleOwner, {
            if(it == true)
            {
                this.findNavController().navigate(isim_degistirDirections.actionIsimDegistirToAnaSayfa())
                viewModel.navigationOkay()
            }
        })

        return binding.root
    }
}








