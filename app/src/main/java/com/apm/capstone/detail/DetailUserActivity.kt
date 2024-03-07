package com.apm.capstone.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.apm.capstone.R
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

@Suppress(*arrayOf("unused", "RedundantSuppression"))
class DetailUserActivity : AppCompatActivity() {

    private val detailUserViewModel: DetailUserViewModel by viewModel()

    private var _activityDetailUserBinding: ActivityDetailUserBinding? = null
    private val binding get() = _activityDetailUserBinding

    private var favorite = false
    private var kdDetail: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        val detail = intent.getParcelableExtra<User>(EXTRA_DATA)
        kdDetail = intent.getStringExtra(KD_DET)?.toInt()

        favorite = detail?.isFavorite == true
        showDetailUser(detail)

        binding?.fab?.setOnClickListener {
            if (favorite) {
                val response =
                    detail?.let { it1 -> detailUserViewModel.setFavoriteUser(it1, false) }
                if (response == true) {
                    favorite = false
                    setFavorite(false)
                }
            } else {
                val response = detail?.let { it1 -> detailUserViewModel.setFavoriteUser(it1, true) }
                if (response == true) {
                    favorite = true
                    setFavorite(true)
                }
            }

            if (kdDetail == 1) {
                val uri = Uri.parse("capstone://favorite")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showDetailUser(detailUser: User?) {
        if (detailUser != null) {
            showLoading(true)
            detailUser.let {
                binding?.tvTitleDetail?.text = it.login
                binding?.tvSubtitleDetail?.text = it.html_url
                Glide.with(this@DetailUserActivity)
                    .load(it.avatar_url)
                    .into(binding!!.ivImageDetail)

                val webView = binding!!.webView
                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        showLoading(false)
                    }
                }
                webView.settings.javaScriptEnabled = true
                it.let { it1 -> webView.loadUrl(it1.html_url) }
            }
        }
        setFavorite(favorite)
    }

    private fun setFavorite(status: Boolean) {
        if (status) {
            binding?.fab?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding?.fab?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite
                )
            )
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBarDetail?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val KD_DET = "kode_detail"
    }
}