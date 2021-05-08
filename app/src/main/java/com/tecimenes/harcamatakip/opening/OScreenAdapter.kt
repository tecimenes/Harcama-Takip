package com.tecimenes.harcamatakip.opening

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OScreenAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm,lifecycle) {

    private val OScreenList = list

    override fun getItemCount(): Int {
        return OScreenList.size
    }

    override fun createFragment(position: Int): Fragment {
        return OScreenList[position]
    }

}