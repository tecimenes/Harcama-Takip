package com.tecimenes.harcamatakip.opening

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.databinding.FragmentOnboardingScreenBinding

class OnboardingScreen : Fragment() {

    private var _binding: FragmentOnboardingScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingScreenBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.os1.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}