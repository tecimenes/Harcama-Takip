package com.tecimenes.harcamatakip.opening

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.databinding.FragmentOScreenBinding
import me.relex.circleindicator.CircleIndicator3

class OScreen : Fragment() {

    private var _binding: FragmentOScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        val OScreenList = arrayListOf<Fragment>(
            OnboardingScreen(),
            OnboardingScreen2(),
            OnboardingScreen3()
        )

        val adapter = OScreenAdapter(
            OScreenList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter


        val viewpager: ViewPager2 = view.findViewById(R.id.viewPager)
        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)
        indicator.setViewPager(viewpager)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}