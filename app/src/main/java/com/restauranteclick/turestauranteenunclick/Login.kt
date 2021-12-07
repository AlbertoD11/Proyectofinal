package com.restauranteclick.turestauranteenunclick


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.restauranteclick.turestauranteenunclick.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailUsuario: EditText
    private lateinit var passUsuario: EditText
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        instanciasFirebase()
        binding.btnLogin.setOnClickListener {
            signInFirebase(
                emailUsuario.text.toString(),
                passUsuario.text.toString()
            )
        }
        emailUsuario = binding.testmail
        passUsuario = binding.testpassword


        binding.txtRegisterNow.setOnClickListener {

            val intent = Intent(this, Register::class.java)
            startActivity(intent)

        }

        binding.txtForgotPassword.setOnClickListener {

            val intent = Intent(this, Forgotpassword::class.java)
            startActivity(intent)

        }


    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser != null) {
            startActivity(Intent(this@Login, Search::class.java))
            finish()
        }
    }


    private fun instanciasFirebase() {
        mAuth = FirebaseAuth.getInstance() //Autenticacion de Firebase
    }


    private fun signInFirebase(email: String, password: String) {
        Log.d("login firebase", email)
        Log.d("login firebase", password)

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, getString(R.string.email_blanco), Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, getString(R.string.pass_blanco), Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (password.length < 6) {
            passUsuario.error = getString(R.string.pass_lenght)
            return
        }




        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

            if (task.isSuccessful) {

                startActivity(Intent(this@Login, Search::class.java))
                finish()
            } else {

                Toast.makeText(
                    applicationContext,
                    getString(R.string.error_usuario_invalido), Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


}
