package com.restauranteclick.turestauranteenunclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.restauranteclick.turestauranteenunclick.databinding.ActivitySearchBinding

class Search : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        binding.close.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        binding.search.setOnClickListener {

            val intent = Intent(this, Typesearch::class.java)
            intent.putExtra("opcion","Agenda")
            startActivity(intent)

        }
        binding.newsearch.setOnClickListener {

            val intent = Intent(this, Typesearch::class.java)
            intent.putExtra("opcion","Nueva")
            startActivity(intent)

        }
        binding.txtcalendar.setOnClickListener {

            val intent = Intent(this, Calendario::class.java)
            intent.putExtra("opcion","Nueva")
            startActivity(intent)

        }



    }
    private fun showDatePickerDialog() {
        val datePicker = Calendario{ day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.txtcalendar.setText("Has seleccionado el $day del $month del año $year")
    }

}