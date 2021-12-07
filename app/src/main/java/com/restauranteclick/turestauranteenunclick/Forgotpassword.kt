package com.restauranteclick.turestauranteenunclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.restauranteclick.turestauranteenunclick.databinding.ActivityForgotpasswordBinding
import com.restauranteclick.turestauranteenunclick.databinding.ActivityLoginBinding
import com.restauranteclick.turestauranteenunclick.databinding.ActivityRegistrerBinding

class Forgotpassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btnsiguiente: Button
    private lateinit var etEmail: EditText

    private lateinit var binding: ActivityForgotpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotpasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()



        binding. btnsiguiente.setOnClickListener{
            val email=etEmail.text.toString()
            if(email.isEmpty()){
                etEmail.error = "Enter email"
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Enter valid mail"
            }
            forgotPassword(email)
        }
    }
    private fun forgotPassword(email:String){

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Reset Password Email sent", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Forgotpassword,Login::class.java))
                    }
                }

    }


    //try try but never give up
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,Login::class.java))
        return true
    }
}