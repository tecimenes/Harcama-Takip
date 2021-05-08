package com.tecimenes.harcamatakip.opening

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.databinding.FragmentOnboardingScreen3Binding

class OnboardingScreen3 : Fragment() {

    private var _binding: FragmentOnboardingScreen3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingScreen3Binding.inflate(inflater,container,false)

        binding.os3.setOnClickListener {
            findNavController().navigate(R.id.action_OScreen_to_ana_sayfa)
            OScreenFinish()
        }

        return binding.root
    }

    private fun OScreenFinish(){
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("Finish",true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}