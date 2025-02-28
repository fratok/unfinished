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
import com.example.myapplication2.databinding.ActivityAuthBinding
import com.example.myapplication2.databinding.ActivityItems2Binding
import dagger.android.DaggerActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class AuthFragment : BaseFragment<ActivityAuthBinding>() {

    @Inject
    lateinit var dbHelper: DbHelper

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//enableEdgeToEdge()
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            this?.linkToReg?.setOnClickListener {
                findNavController().navigate(R.id.action_mainActivity_to_itemsActivity2)
            }

            this?.buttonAuth?.setOnClickListener {
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
            this?.userLoginAuth?.text?.clear()
            this?.userPassAuth?.text?.clear()
        }
    }

    private fun navigateToItems() {
        findNavController().navigate(R.id.action_mainActivity_to_itemsActivity2)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "AuthFragment"
    }
}