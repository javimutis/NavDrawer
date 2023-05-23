package com.example.navdrawer

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.navdrawer.interfaces.IActionBarApp

open class ActionBarActivity : AppCompatActivity(), IActionBarApp {

    protected lateinit var _toolbar:Toolbar

    override fun loadActionBarInActivity(toolbar: Toolbar) {

        _toolbar = toolbar
        _toolbar.let {
            setSupportActionBar(toolbar)
        }
    }

}