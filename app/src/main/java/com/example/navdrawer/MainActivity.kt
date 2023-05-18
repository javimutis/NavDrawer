package com.example.navdrawer

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.navdrawer.databinding.ActivityMainBinding
import com.example.navdrawer.interfaces.IActionBarApp

private lateinit var bindingMain: ActivityMainBinding

class MainActivity : ActionBarActivity(), IActionBarApp {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
            _toolbar = findViewById(R.id.toolbar)
            loadActionBarInActivity(_toolbar)


    }
}