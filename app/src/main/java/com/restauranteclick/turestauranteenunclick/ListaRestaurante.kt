package com.restauranteclick.turestauranteenunclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.restauranteclick.turestauranteenunclick.databinding.ActivityListarestauranteBinding
import com.restauranteclick.turestauranteenunclick.entities.Restaurante

class ListaRestaurante : AppCompatActivity() {
    private val firestore = App.getFirestore()
    private lateinit var binding: ActivityListarestauranteBinding
    private lateinit var categoria: String
    private lateinit var ciudad: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoria = intent!!.extras!!.get("categoria")!!.toString()
        ciudad = intent!!.extras!!.get("ciudad")!!.toString()
        val recyclerView = binding.rvListaRestaurante
        val mAdapter = RestauranteAdapter(mutableListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListaRestaurante,RecyclerView.VERTICAL,false)
            adapter = mAdapter
        }

        firestore.collection("Restaurantes").get().addOnSuccessListener {
            /*val listarestaurante = findViewById<>(R.id.listarestaurante)
            val arrayAdapter: ArrayAdapter<*>
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurantes)
            listarestaurante.adapter = arrayAdapter
*/
            for (document in it) {
                if ((document.data["Categoria"] as String) == categoria && (document.data["Ciudad"] as String)==ciudad) {
                    val restaurante = Restaurante(
                            document.data["Nombre"] as String,
                            document.data["Telefono"] as Long,
                            document.data["Direccion"] as String)
                    mAdapter.addRestaurante(restaurante)
                }

            }


            val list3 = findViewById<ListView>(R.id.list2)
            /* val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurantes)
            list3.adapter = adaptador1
            list3.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent()
                intent.putExtra("opcion",restaurantes.get(i))
                setResult(RESULT_OK,intent)
                finish()}

        }*/

        }
    }
}