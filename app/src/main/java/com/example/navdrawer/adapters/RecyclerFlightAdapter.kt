package com.example.navdrawer.adapters

/*En esta sección se importan las clases necesarias para el funcionamiento del adaptador.
Esto incluye clases para manejar vistas (LayoutInflater, ViewGroup) y RecyclerView,
así como clases personalizadas (ItemFlightLayoutBinding, RecyclerFlight, Flight)
y la biblioteca Picasso para cargar imágenes.*/

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navdrawer.databinding.ItemFlightLayoutBinding
import com.example.navdrawer.interfaces.RecyclerFlight
import com.example.navdrawer.models.Flight
import com.squareup.picasso.Picasso

/*Aquí se define la clase RecyclerFlightAdapter. Esta clase extiende RecyclerView.Adapter y utiliza
ViewHolderFlight como su clase interna para mantener y gestionar las vistas de los elementos del
RecyclerView. El adaptador recibe dos parámetros en su constructor: listFlight, que es una lista
de objetos Flight, y flightListener, que implementa la interfaz RecyclerFlight.*/

class RecyclerFlightAdapter(
    private val listFlight: List<Flight>,
    private val flightListener: RecyclerFlight
) : RecyclerView.Adapter<RecyclerFlightAdapter.ViewHolderFlight>() {

    /*La función onCreateViewHolder se llama cuando se crea un nuevo ViewHolder para el RecyclerView.
    Recibe dos parámetros: parent, que es el ViewGroup al que se adjuntará la vista, y viewType,
    que se utiliza para identificar el tipo de vista si hay varios tipos en el adaptador.
    En esta función, se crea una instancia de LayoutInflater para inflar el diseño de elemento
    del vuelo (ItemFlightLayoutBinding) a partir de su archivo de diseño XML correspondiente.
    Luego, se crea y devuelve un nuevo ViewHolderFlight con el enlace de datos a la vista inflada.*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFlight {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFlightLayoutBinding.inflate(inflater, parent, false)

        return ViewHolderFlight(binding)

    }
    /*La función getItemCount devuelve el número de elementos en la lista de vuelos (listFlight).*/

    override fun getItemCount(): Int = listFlight.size

    /*La función onBindViewHolder se llama cuando se necesita enlazar los datos de un elemento de
    la lista (flight) a la vista (holder). Recibe dos parámetros: holder, que es el ViewHolder
    asociado al elemento actual, y position, que es la posición del elemento en la lista.
    Aquí se obtiene el vuelo correspondiente a la posición actual y se llama al método bind del
    ViewHolder para enlazar los datos del vuelo a la vista.*/

    override fun onBindViewHolder(holder: ViewHolderFlight, position: Int) {

        val flight = listFlight[position]
        holder.bind(flight)
    }

    /*La clase interna ViewHolderFlight es responsable de mantener y gestionar las vistas del
    elemento del vuelo. Toma un parámetro binding de tipo ItemFlightLayoutBinding,
    que es una clase generada automáticamente que contiene las referencias a las vistas del diseño
    de elemento del vuelo.

    Dentro de la función bind, se enlazan los datos del vuelo con las vistas correspondientes.
    En este caso, se establece el texto del campo txtCity con el nombre de la ciudad del vuelo
    (flight.ciudad). También se utiliza la biblioteca Picasso para cargar la imagen del vuelo desde
    la URL proporcionada por flight.imagen en la vista cityImage.

    Además, se establecen los listeners de clic para la vista raíz (root) y el botón de eliminar
    (btnDelete). Cuando se hace clic en la vista raíz, se llama al método onClick del flightListener
    pasando el vuelo y la posición del adaptador (adapterPosition). Cuando se hace clic en el
    botón de eliminar, se llama al método onDelete del flightListener pasando el vuelo y la
    posición del adaptador.

    En resumen, este adaptador se encarga de inflar y gestionar las vistas de los elementos del
    RecyclerView de vuelos. Al enlazar los datos de cada vuelo con las vistas correspondientes,
    permite mostrar una lista de vuelos en una interfaz de usuario y manejar interacciones como
    clics en elementos y acciones de eliminación.*/

    inner class ViewHolderFlight(private val binding: ItemFlightLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flight: Flight) {

            binding.txtCity.text = flight.ciudad
            Picasso.get().load(flight.imagen).fit().into(binding.cityImage)
            binding.root.setOnClickListener { flightListener.onClick(flight, adapterPosition) }
            binding.btnDelete.setOnClickListener {
                flightListener.onDelete(
                    flight,
                    adapterPosition
                )
            }

        }

    }

}