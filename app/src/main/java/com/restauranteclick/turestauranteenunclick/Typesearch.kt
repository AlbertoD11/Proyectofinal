package com.restauranteclick.turestauranteenunclick

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import com.restauranteclick.turestauranteenunclick.databinding.ActivityTypesearchBinding

class Typesearch : AppCompatActivity() {

    private lateinit var binding: ActivityTypesearchBinding
    private lateinit var ciudad:String
    private lateinit var categoria: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityTypesearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val extras= intent.extras!!
        val opcion = extras.getString("opcion")!!

        Toast.makeText(this,opcion,Toast.LENGTH_LONG).show()

        binding.btnciudad.setOnClickListener {
            obtenerCiudad.launch(Intent(this,Ciudad::class.java))


        }
        binding.btncategoria.setOnClickListener {
            obtenerCategoria.launch(Intent(this,Categoria::class.java))

        }
        binding.button3.setOnClickListener {
           if (!this::categoria.isInitialized){
               Toast.makeText(this,"no ha seleccionado una categoria",Toast.LENGTH_LONG).show()
               return@setOnClickListener


           }
            if (!this::ciudad.isInitialized){
                Toast.makeText(this,"no ha seleccionado una ciudad",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this,ListaRestaurante::class.java)
            intent.putExtra("categoria",categoria)
            intent.putExtra("ciudad",ciudad)
            startActivity(intent)

        }

    }
    private val obtenerCiudad = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode== Activity.RESULT_OK){
            ciudad=it.data?.getStringExtra("opcion")!!
            Toast.makeText(this,ciudad,Toast.LENGTH_LONG).show()

        }
    }

    private val obtenerCategoria= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode== Activity.RESULT_OK){
            categoria=it.data?.getStringExtra("opcion")!!
            Toast.makeText(this,categoria,Toast.LENGTH_LONG).show()

        }
    }
}