package com.apm.capstone.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.capstone.R
import com.apm.capstone.core.data.Resource
import com.apm.capstone.core.ui.UserAdapter
import com.apm.capstone.databinding.FragmentHomeBinding
import com.apm.capstone.detail.DetailUserActivity
import org.koin.android.viewmodel.ext.android.viewModel

@Suppress(*arrayOf("unused", "RedundantSuppression"))
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserAdapter()
        userAdapter.onItemClick = { selectedData ->
            val detail = Intent(context, DetailUserActivity::class.java)
            detail.putExtra(DetailUserActivity.EXTRA_DATA, selectedData)
            detail.putExtra(DetailUserActivity.KD_DET, "0")
            startActivity(detail)
        }

        homeViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading<*> -> showLoading(true)
                    is Resource.Success<*> -> {
                        showLoading(false)
                        userAdapter.setData(user.data)
                    }

                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            context,
                            getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        with(binding?.rvUser) {
            this?.layoutManager = LinearLayoutManager(this?.context)
            this?.setHasFixedSize(true)
            this?.adapter = userAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}