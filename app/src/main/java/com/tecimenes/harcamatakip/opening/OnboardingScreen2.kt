package com.tecimenes.harcamatakip.opening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.databinding.FragmentOnboardingScreen2Binding

class OnboardingScreen2 : Fragment() {
    private var _binding: FragmentOnboardingScreen2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingScreen2Binding.inflate(inflater,container,false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.os2.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}