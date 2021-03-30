package com.akinci.matchscores.features.news.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akinci.matchscores.databinding.FragmentNewsDetailBinding
import timber.log.Timber

class NewsDetailFragment : Fragment() {

    lateinit var binding : FragmentNewsDetailBinding
    private val newsDetailArgs by lazy { NewsDetailFragmentArgs.fromBundle(requireArguments()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        /** Initialization of ViewBinding not need for DataBinding here **/
        binding = FragmentNewsDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.webView.webViewClient = WebViewDelegate(
            loadCompleted = {
                //on load completed.
                Timber.d("Webview loaded..")
                binding.progressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
            },
            onError = {
                // on error occurred while loading
                Timber.d("Webview couldnt be loaded..")
                binding.progressBar.visibility = View.GONE
                Toast.makeText(activity, "Got Error! $it", Toast.LENGTH_SHORT).show()
            }
        )

        binding.webView.loadUrl(newsDetailArgs.newslink)

        Timber.d("NewsDetailFragment created..")
        return binding.root
    }

    class WebViewDelegate(
        private val loadCompleted : () -> Unit,
        private val onError : (String) -> Unit
    ) : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            loadCompleted.invoke()
            super.onPageFinished(view, url)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            onError.invoke(error.toString())
            super.onReceivedError(view, request, error)
        }
    }

}