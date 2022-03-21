package com.example.jtproject.ui.searchingmodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jt_project.databinding.SearchingModuleFragmentBinding
import com.example.jtproject.ui.searchingmodule.compose.SearchingModuleComposeView
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingModuleFragment : Fragment() {

    private val viewModel: SearchingModuleViewModel by viewModels()

    private var _binding: SearchingModuleFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = SearchingModuleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.composeSearchingModule.setContent {
            MdcTheme {
                SearchingModuleComposeView(
                    viewModel = viewModel,
                    openDetails = { }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}