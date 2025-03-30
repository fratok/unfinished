package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import dagger.android.DaggerActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var registrationView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registrationView = layoutInflater.inflate(R.layout.registration, null)
        addContentView(
            registrationView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )
        setupRegistration()
    }


    private fun setupRegistration() {
        val userLogin = registrationView.findViewById<EditText>(R.id.user_login)
        val userEmail = registrationView.findViewById<EditText>(R.id.user_email)
        val userPass = registrationView.findViewById<EditText>(R.id.user_pass)
        val button = registrationView.findViewById<Button>(R.id.button_reg)
        val linkToAuth = registrationView.findViewById<TextView>(R.id.link_to_auth)

        linkToAuth.setOnClickListener {
            registrationView.visibility = View.GONE
            findNavController(R.id.nav_host_fragment).navigate(R.id.authFragment)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || email == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, email, pass)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }
    }
}


