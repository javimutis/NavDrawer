package com.example.navdrawer.interfaces

import com.example.navdrawer.models.Flight

interface RecyclerFlight {

    fun onClick(flight: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)

}