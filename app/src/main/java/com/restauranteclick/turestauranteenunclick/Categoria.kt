package com.restauranteclick.turestauranteenunclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class Categoria : AppCompatActivity() {
    private val firestore = App.getFirestore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        firestore.collection("Categorias").get().addOnSuccessListener {
            val categoria = mutableListOf<String>()
            for (document in it) {
                categoria.add(document.data.get("Nombre") as String)

                Log.d("Categorias", "${document.id} = ${document.data}")
            }


            val list2 = findViewById<ListView>(R.id.list2)
            val adaptador1 =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoria)
            list2.adapter = adaptador1
            list2.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent()
                intent.putExtra("opcion", categoria.get(i))
                setResult(RESULT_OK, intent)
                finish()
            }

        }

    }

}

