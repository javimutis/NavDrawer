package com.example.navdrawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.navdrawer.databinding.ActivityMainBinding
import com.example.navdrawer.fragments.ArrivalsFragment
import com.example.navdrawer.fragments.DeparturesFragment
import com.example.navdrawer.fragments.HomeFragment
import com.example.navdrawer.interfaces.IActionBarApp
import com.google.android.material.navigation.NavigationView

private lateinit var bindingMain: ActivityMainBinding

class MainActivity : ActionBarActivity(), IActionBarApp,
    NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        _toolbar = findViewById(R.id.toolbar)
        loadActionBarInActivity(_toolbar)
        configurationDrawer()
        setAppInformation()
        if(savedInstanceState == null ) fragmentTransaction(HomeFragment())

    }

    private fun configurationDrawer() {

        var toogle = ActionBarDrawerToggle(
            this,
            bindingMain.drawer,
            _toolbar,
            R.string.drawerClose, R.string.drawerOpen
        )
        // Activar el menu hamburguesa
        toogle.isDrawerIndicatorEnabled
        bindingMain.drawer.addDrawerListener(toogle)

        bindingMain.navigation.menu.getItem(0).isChecked = true

        toogle.syncState()
        bindingMain.navigation.setNavigationItemSelectedListener(this)


    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun loadFragmentById(idFragment: Int) {
        when (idFragment) {
            R.id.home -> fragmentTransaction(HomeFragment())
            R.id.arrivals -> fragmentTransaction(ArrivalsFragment())
            R.id.departures -> fragmentTransaction(DeparturesFragment())
        }

    }

    private fun loadOtherOptions(idOption: Int) {

        when (idOption) {

            R.id.profile -> Toast.makeText(
                this,
                "Aca va el perfil del Usuario", Toast.LENGTH_LONG
            ).show()

            R.id.settings -> Toast.makeText(
                this,
                "Aca va la configuracion de la APP", Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun setAppInformation() {

        var name = bindingMain.navigation
            .getHeaderView(0)
            .findViewById<TextView>(R.id.txtInformation)

        name?.let {

            name.text = "Usuario de la BD o de la API"

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        loadFragmentById(item.itemId)
        loadOtherOptions(item.itemId)
        bindingMain.drawer.closeDrawer(GravityCompat.START)
        return true
    }
}