package com.example.navdrawer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navdrawer.adapters.RecyclerFlightAdapter
import com.example.navdrawer.databinding.FragmentDeparturesBinding
import com.example.navdrawer.interfaces.RecyclerFlight
import com.example.navdrawer.models.Flight
import com.example.navdrawer.providers.FlightProvider


class DeparturesFragment : Fragment() {

    private lateinit var binding: FragmentDeparturesBinding
    private lateinit var recyclerFlight: RecyclerView
    private val layoutManager by lazy {LinearLayoutManager(context)}
    private val listOfCities by lazy { getFlightsFromProvider() }
    private lateinit var adapterFlight: RecyclerFlightAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.setTitle("Salidas de vuelo")
        binding = FragmentDeparturesBinding.inflate(inflater, container,false)
        recyclerFlight = binding.recyclerDepartures

        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {

        recyclerFlight.setHasFixedSize(true)
        recyclerFlight.itemAnimator = DefaultItemAnimator()
        recyclerFlight.layoutManager = layoutManager
        adapterFlight = (RecyclerFlightAdapter(listOfCities,object :RecyclerFlight{
            override fun onClick(flight: Flight, position: Int) {
                Toast.makeText(activity, "Nos Vamos a ${flight.ciudad}",Toast.LENGTH_LONG).show()
            }

            override fun onDelete(flight: Flight, position: Int) {

                listOfCities.removeAt(position)
                adapterFlight.notifyItemChanged(position)
            }

        }))

        adapterFlight.notifyDataSetChanged()
        recyclerFlight.adapter = adapterFlight

    }

    private fun getFlightsFromProvider():MutableList<Flight>{

        var listFlights = FlightProvider.listCitiesForFlight
        return listFlights

    }

}