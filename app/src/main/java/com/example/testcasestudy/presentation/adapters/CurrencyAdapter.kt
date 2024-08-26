package com.example.testcasestudy.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testcasestudy.databinding.ItemCurrencyBinding
import com.example.testcasestudy.domain.models.CurrencyInfo

class CurrencyAdapter :
    ListAdapter<CurrencyInfo, CurrencyAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyInfo: CurrencyInfo) {
            binding.tvFirstChar.text = currencyInfo.name.first().toString()
            binding.currencyName.text = currencyInfo.name

            if (currencyInfo.code != null) {
                binding.ivArrow.visibility = View.INVISIBLE
                binding.currencySymbol.visibility = View.INVISIBLE
            } else {
                binding.ivArrow.visibility = View.VISIBLE
                binding.currencySymbol.text = currencyInfo.symbol

            }

        }
    }
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem == newItem
    }
}
