package edu.miu.cvbuilderapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import edu.miu.cvbuilderapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        val loadUrl = intent.getStringExtra("url")

        binding.mainWebView.webViewClient = WebViewClient()
        binding.mainWebView.settings.javaScriptEnabled = true
        binding.mainWebView.settings.builtInZoomControls = true
        loadUrl?.let { binding.mainWebView.loadUrl(it) }

        setContentView(binding.root)
    }

    inner class WebViewClient : android.webkit.WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }
}