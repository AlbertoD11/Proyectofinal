package com.restauranteclick.turestauranteenunclick

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.restauranteclick.turestauranteenunclick.databinding.ActivityLoginBinding
import com.restauranteclick.turestauranteenunclick.databinding.ActivityRegistrerBinding


class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrerBinding

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setup()




    }

    private fun setup(){
        title="Autenticación"

        binding.buttoncancell.setOnClickListener {

            val intent = Intent(this, Login::class.java)
            startActivity(intent)}


      binding.txtregistrar.setOnClickListener{
            if (binding.txtEmail.text!!.isNotEmpty() && binding.txtPassword.text!!.isNotEmpty() ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.txtEmail.text.toString(),binding.txtPassword.text.toString()).addOnCompleteListener(){

                    if(it.isSuccessful){


                    }else {
                        showAlert()
                    }
                }

            }

        }


    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usuario no valido")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }




}

