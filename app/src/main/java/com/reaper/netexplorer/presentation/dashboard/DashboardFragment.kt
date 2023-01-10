package com.reaper.netexplorer.presentation.dashboard

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.reaper.domain.model.ChrootParams
import com.reaper.netexplorer.R
import com.reaper.netexplorer.databinding.FragmentHomeBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DashboardViewModel
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    with(binding) {
                        progressChroot.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                        tvProgressValue.text = getString(R.string.progress_percent, state.progress)
                    }
                }
            }
        }

        // TODO move to check chroot fun
        lifecycleScope.launch {
            Log.e(TAG, "start downloadChroot()")
            viewModel.downloadChroot(
                chrootParams = ChrootParams(
                    url = "reaper164/alpine-chroot/releases/download/v1.0.0/chroot.tar.gz",
                    savePath = File(
                        Environment.getExternalStorageDirectory(),
                        "Download"
                    ).absolutePath
                )
            )
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}