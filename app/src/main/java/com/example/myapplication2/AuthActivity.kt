package com.example.myapplication2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.myapplication2.databinding.FragmentItems2Binding
import com.example.myapplication2.databinding.FragmentAuthBinding
import dagger.android.DaggerActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    @set:Inject
    lateinit var dbHelper: DbHelper


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun inflateViewBinding( inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            linkToReg.setOnClickListener {
                findNavController().navigate(R.id.action_authFragment_to_itemsFragment2)
            }

            this.buttonAuth.setOnClickListener {
                val login = userLoginAuth.text.toString().trim()
                val pass = userPassAuth.text.toString().trim()

                when {
                    login.isEmpty() || pass.isEmpty() ->
                        showToast("Заполните все поля")

                    dbHelper.getUser(login, pass) -> {
                        showToast("Авторизация успешна")
                        clearFields()
                        navigateToItems()
                    }

                    else -> showToast("Ошибка авторизации")

                }
            }
        }
    }

    private fun clearFields() {
        with(binding) {
            this.userLoginAuth.text?.clear()
            this.userPassAuth.text?.clear()
        }
    }

    private fun navigateToItems() {
        findNavController().navigate(R.id.action_authFragment_to_itemsFragment2)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "AuthFragment"
    }
}