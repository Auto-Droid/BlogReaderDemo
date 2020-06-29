package com.sourabhkarkal.blogreaderdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sourabhkarkal.blogreaderdemo.R
import com.sourabhkarkal.blogreaderdemo.view.fragment.BlogFragment

class MainActivity : AppCompatActivity() {
    companion object {
        const val FRAGMENT_TAG = "BlogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BlogFragment(), FRAGMENT_TAG)
            .commit()
    }
}