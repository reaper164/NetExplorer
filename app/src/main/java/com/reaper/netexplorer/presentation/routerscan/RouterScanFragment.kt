package com.reaper.netexplorer.presentation.routerscan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.reaper.domain.model.RouterscanParams
import com.reaper.netexplorer.R
import com.reaper.netexplorer.adapters.RouterRvAdapter
import com.reaper.netexplorer.databinding.FragmentRouterScanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RouterScanFragment : Fragment() {

    private lateinit var viewModel: RouterScanViewModel
    private lateinit var binding: FragmentRouterScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RouterScanViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    with(binding) {
                        progress.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                        routersList.adapter = RouterRvAdapter(routerList = state.routerList)
                        tvProgress.text = getString(R.string.router_progress, state.progress)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRouterScanBinding.inflate(inflater, container, false)

        binding.btnStartScan.setOnClickListener {

            val scanParams = RouterscanParams(
                rangeIp = listOf("192.168.0.100" to "192.168.0.110"),
                listIp = listOf("192.168.0.1", "192.168.0.2")
            )

            viewModel.discoveryRouters(routerscanParams = scanParams)
        }



        return binding.root
    }


}