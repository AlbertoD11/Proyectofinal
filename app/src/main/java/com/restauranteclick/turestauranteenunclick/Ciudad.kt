package com.restauranteclick.turestauranteenunclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.toObject

class Ciudad : AppCompatActivity() {
    private val firestore = App.getFirestore()
    lateinit var ref: DatabaseReference
    lateinit var ciudadlista: MutableList<Ciudad>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)

        firestore.collection("Ciudades").get().addOnSuccessListener {
            val paises = mutableListOf<String>()
            for (document in it) {
                paises.add(document.id)

                Log.d("ciudades", "${document.id} = ${document.data}")


            }
            val tv1 = findViewById<TextView>(R.id.tv1)
            val list1 = findViewById<ListView>(R.id.list1)
            val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises)
            list1.adapter = adaptador1
            list1.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent()
                intent.putExtra("opcion", paises.get(i))
                setResult(RESULT_OK, intent)
                finish()
            }


        }

    }

}