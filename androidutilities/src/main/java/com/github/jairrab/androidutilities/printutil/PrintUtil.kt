package com.github.jairrab.androidutilities.printutil

import android.content.Context
import android.os.Build
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import java.lang.ref.WeakReference

internal class PrintUtil {
    private val printJobs: WeakReference<ArrayList<PrintJob>> = WeakReference(ArrayList())

    fun printJob(
        context: Context,
        html: String,
        printJobName: String = "Print Job"
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Create a WebView object specifically for printing
            val webView = WebView(context)

            webView.webViewClient = object : WebViewClient() {

                override fun shouldOverrideUrlLoading(view: WebView, url: String) = false

                override fun onPageFinished(view: WebView, url: String) {
                    val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager

                    val printAdapter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.createPrintDocumentAdapter(printJobName)
                    } else {
                        view.createPrintDocumentAdapter()
                    }

                    val attributes = PrintAttributes.Builder().build()

                    val printJob = printManager.print(printJobName, printAdapter, attributes)

                    printJobs.get()?.add(printJob)
                }
            }

            webView.loadDataWithBaseURL(
                null, html, "text/HTML",
                "UTF-8", null
            )
        } else {
            Toast.makeText(
                context, "Android Print Support not supported for this older version of Android.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun destroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            printJobs.get()?.forEach {
                it.cancel()
            }
        }
    }
}