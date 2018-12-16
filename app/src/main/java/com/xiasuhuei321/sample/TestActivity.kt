package com.xiasuhuei321.sample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.xiasuhuei321.loadingdialog.XLoading

class TestActivity : AppCompatActivity() {
    var loading = XLoading()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        Handler().postDelayed({
            loading.show(this)
        }, 1000)

        Handler().postDelayed({ loading.dismiss() }, 3000)
    }
}