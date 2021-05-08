package com.tecimenes.harcamatakip.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tecimenes.harcamatakip.database.AppDatabase
import com.tecimenes.harcamatakip.databinding.FragmentAnaSayfaBinding

class ana_sayfa : Fragment() {
    private lateinit var adapter: harcama_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAnaSayfaBinding = FragmentAnaSayfaBinding.inflate(inflater, container, false)
        val app = requireNotNull(this.activity).application
        val veriKaynagi = AppDatabase.getInstance(app).databaseDao
        val viewModelFactory = view_model_factory(veriKaynagi, app)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(view_model::class.java)

        val dovizM = ana_sayfaArgs.fromBundle(requireArguments()).dovizArg
        viewModel.dovizAyarla(dovizM)
        binding.dovizGroup.check(when(dovizM)
        {
            1 -> binding.buttonTL.id
            2 -> binding.buttonDolar.id
            3 -> binding.buttonSterlin.id
            4 -> binding.buttonZloty.id
            else -> binding.buttonEuro.id
        })

        adapter = harcama_adapter(harcamaClick {
            viewModel.harcamaTik(it)
        }, dovizDegisimListener {
            viewModel.toplamHarcamaHesapFonk(it)
        }, viewModel.dovizCek(), viewModel.guncelVeriCek())
        binding.recyclerView.adapter = adapter

        viewModel.harcamalar.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
                var sum = 0F
                it.forEach { i -> sum += i.cost }
                viewModel.toplamHarcamaHesapFonk(sum)
            }
        })

        viewModel.toplamHarcama.observe(viewLifecycleOwner, {
            binding.textViewToplamHarcama.text = it
        })

        viewModel.anlikDoviz.observe(viewLifecycleOwner, {
            adapter.dovizDegisim(viewModel.dovizCek(), viewModel.guncelVeriCek())
        })

        viewModel.kullaniciIsim.observe(viewLifecycleOwner, {
            binding.anasayfaIsim.text = it
        })

        viewModel.kullaniciCinsiyet.observe(viewLifecycleOwner, {
            binding.anasayfaCinsiyet.text = when(it)
            {
                0 -> " Hanım"
                1 -> " Bey"
                else -> ""
            }
        })

        binding.dovizGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.buttonEuro.id -> viewModel.dovizAyarla(0)
                binding.buttonTL.id -> viewModel.dovizAyarla(1)
                binding.buttonDolar.id -> viewModel.dovizAyarla(2)
                binding.buttonSterlin.id -> viewModel.dovizAyarla(3)
                binding.buttonZloty.id -> viewModel.dovizAyarla(4)
            }
        }

        viewModel.toHarcamaSil.observe(viewLifecycleOwner, {
            if(it != null)
            {
                val action = ana_sayfaDirections.actionAnaSayfaToHarcamaSil(it, viewModel.dovizCek())
                this.findNavController().navigate(action)
                viewModel.navigationOkay()
            }
        })

        val action = ana_sayfaDirections.actionAnaSayfaToIsimDegistir()
        binding.anasayfaIsim.setOnClickListener{ this.findNavController().navigate(action) }
        binding.anasayfaCinsiyet.setOnClickListener{ this.findNavController().navigate(action) }

        binding.anaSayfaButon.setOnClickListener { this.findNavController().navigate(ana_sayfaDirections.actionAnaSayfaToHarcamaEkle()) }

        return binding.root
    }

}