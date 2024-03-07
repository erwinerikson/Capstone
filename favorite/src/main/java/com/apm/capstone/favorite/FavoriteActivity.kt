package com.apm.capstone.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apm.capstone.R
import com.apm.capstone.core.ui.UserAdapter
import com.apm.capstone.detail.DetailUserActivity
import com.apm.capstone.favorite.databinding.ActivityFavoriteBinding
import com.apm.capstone.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@Suppress(*arrayOf("unused", "RedundantSuppression"))
class FavoriteActivity : AppCompatActivity() {

    private val favoriteModelView: FavoriteModelView by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        loadKoinModules(favoriteModule)

        val userAdapter = UserAdapter()
        userAdapter.onItemClick = { selectedData ->
            val detail = Intent(this, DetailUserActivity::class.java)
            detail.putExtra(DetailUserActivity.EXTRA_DATA, selectedData)
            detail.putExtra(DetailUserActivity.KD_DET, "1")
            startActivity(detail)
        }

        showLoading(true)
        favoriteModelView.user.observe(this) { user ->
            if (user.isNotEmpty()) {
                userAdapter.setData(user)
            } else {
                Toast.makeText(this, getString(R.string.no_favorite), Toast.LENGTH_LONG).show()
            }
            showLoading(false)
        }

        with(binding.rvFavorite) {
            this.layoutManager = LinearLayoutManager(this.context)
            this.setHasFixedSize(true)
            this.adapter = userAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFav.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}