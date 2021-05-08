package com.tecimenes.harcamatakip.opening

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.databinding.FragmentSplashScreenBinding

class SplashScreen : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater,container,false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed({
            if(oscreenFinish()){
                findNavController().navigate(R.id.action_splashScreen_to_ana_sayfa)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_OScreen)
            }
        },3000)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("Name", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("number",0)
        editor.apply()
    }

    private fun oscreenFinish(): Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("Finish",false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}