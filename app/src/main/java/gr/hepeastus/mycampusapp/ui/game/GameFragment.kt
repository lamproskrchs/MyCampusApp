package gr.hepeastus.mycampusapp.ui.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import gr.hepeastus.mycampusapp.R

class GameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        val webView: WebView = view.findViewById(R.id.game_web_view)

        WebView.setWebContentsDebuggingEnabled(true) // Optional but helpful

        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                android.util.Log.e("WebViewError", "Error $errorCode: $description")
                super.onReceivedError(view, errorCode, description, failingUrl)
            }
        }

        webView.loadUrl("http://192.168.22.40:8000")

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
