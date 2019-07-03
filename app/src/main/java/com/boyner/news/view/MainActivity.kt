package com.boyner.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boyner.news.R
import com.boyner.news.view.sourcelist.SourceListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, SourceListFragment()).commit()
        }
    }
}
