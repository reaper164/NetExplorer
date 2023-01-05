package com.reaper.netexplorer.presentation.routerscan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.reaper.netexplorer.R
import com.reaper.netexplorer.databinding.FragmentRouterScanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouterScanFragment : Fragment() {

    private lateinit var viewModel: RouterScanViewModel
    private lateinit var binding : FragmentRouterScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RouterScanViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouterScanBinding.inflate(inflater, container, false)
        return binding.root
    }


}