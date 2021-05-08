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
import com.tecimenes.harcamatakip.databinding.FragmentHarcamaSilBinding

class harcama_sil : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHarcamaSilBinding = FragmentHarcamaSilBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val veriKaynagi = AppDatabase.getInstance(application).databaseDao
        val harcama = harcama_silArgs.fromBundle(requireArguments()).secilenHarcama
        val harcamaTuru = harcama_silArgs.fromBundle(requireArguments()).secilenHarcamaTuru
        val viewModelFactory = harcama_sil_viewModelFactory(veriKaynagi, application, harcama, harcamaTuru)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(harcama_sil_viewModel::class.java)

        binding.backButton.setOnClickListener {
            viewModel.eveGeriDonus()
        }

        binding.detayGorsel.setImageResource(when(harcama.type) {
            1 -> R.drawable.fatura
            2 -> R.drawable.kira
            else -> R.drawable.diger
        })

        binding.detayAciklama.text = harcama.description

        viewModel.cost.observe(viewLifecycleOwner, {
            binding.detayFiyat.text = it
        })

        binding.silButonu.setOnClickListener {
            viewModel.delete()
            Toast.makeText(activity,"Harcama Silindi",Toast.LENGTH_SHORT).show()
        }

        viewModel.eveDonus.observe(viewLifecycleOwner, {
            if(it == true)
            {
                val action = harcama_silDirections.actionHarcamaSilToAnaSayfa(harcamaTuru)
                this.findNavController().navigate(action)
                viewModel.navigationOkay()
            }
        })

        return binding.root
    }


}