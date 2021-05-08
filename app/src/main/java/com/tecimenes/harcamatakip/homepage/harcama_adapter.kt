package com.tecimenes.harcamatakip.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tecimenes.harcamatakip.R
import com.tecimenes.harcamatakip.database.Harcama
import com.tecimenes.harcamatakip.databinding.HarcamaListeGorunumBinding

class harcama_adapter(
        private val harcamaClick: harcamaClick,
        private val degisimListener: dovizDegisimListener,
        private var type: Int,
        private var rate: Float) : ListAdapter<Harcama, ViewHolder>(Callback())
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val item = getItem(position)
        holder.guncelOran(type, rate)
        holder.bind(item, harcamaClick)
    }

    fun dovizDegisim(type: Int, rate: Float)
    {
        this.type = type
        this.rate = rate
        notifyDataSetChanged()
        var sum = 0f
        this.currentList.forEach { sum += it.cost }
        degisimListener.degisim(sum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, type, rate) }
}

class ViewHolder private constructor(
        private val binding: HarcamaListeGorunumBinding,
        private var type: Int,
        private var rate: Float) : RecyclerView.ViewHolder(binding.root)
{
    fun guncelOran(type: Int, rate: Float)
    {
        this.type = type
        this.rate = rate
    }

    fun bind(item: Harcama, harcamaClick: harcamaClick)
    {
        binding.itemTur.setImageResource(when(item.type)
        {
            1 -> R.drawable.fatura
            2 -> R.drawable.kira
            else -> R.drawable.diger
        })
        binding.itemAciklama.text = item.description
        val fiyat = "%.0f".format(item.cost * rate)
        val textFiyat = when(type)
        {
            1 -> "$fiyat ₺"
            2 -> "$fiyat $"
            3 -> "$fiyat £"
            4 -> "$fiyat zł"
            else -> "$fiyat €"
        }
        binding.itemFiyat.text = textFiyat
        binding.cardViewItem.setOnClickListener{
            harcamaClick.Click(item)
        }
    }

    companion object
    {
        fun from(parent: ViewGroup, type: Int, rate: Float): ViewHolder
        {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HarcamaListeGorunumBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding, type, rate)
        }
    }
}

class harcamaClick(val clickListener: (harcama: Harcama) -> Unit)
{
    fun Click(harcama: Harcama) = clickListener(harcama)
}

class dovizDegisimListener(val listener: (total: Float) -> Unit)
{
    fun degisim(total: Float) = listener(total)
}

class Callback : DiffUtil.ItemCallback<Harcama>()
{
    override fun areItemsTheSame(oldItem: Harcama, newItem: Harcama): Boolean { return oldItem.id == newItem.id }

    override fun areContentsTheSame(oldItem: Harcama, newItem: Harcama): Boolean { return oldItem == newItem }
}