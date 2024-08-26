package com.example.testcasestudy.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcasestudy.databinding.FragmentCurrencyBinding
import com.example.testcasestudy.presentation.adapters.CurrencyAdapter
import com.example.testcasestudy.presentation.viewModels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class CurrencyFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: CurrencyViewModel by viewModels()

    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CurrencyAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.currencies.collect { currencies ->
                adapter.submitList(currencies)
                binding.rvNoListFound.isVisible = currencies.isEmpty()
            }
        }

        // Set up search view listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    delay(300) // delay to debounce user input
                    viewModel.searchCurrencies(newText ?: "")
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}