package com.example.myapplication2

import ErrorDialogFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.launch
import javax.inject.Inject



class Main : AppCompatActivity(), DialogListener {

    @Inject
    lateinit var itemsRepository: ItemsRepository


    private lateinit var loadingIndicator: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.fragment_items2)


        loadingIndicator = findViewById(R.id.loadingIndicator)
        recyclerView = findViewById(R.id.itemsList)

        fetchItems()
    }

    private fun fetchItems() {
        lifecycleScope.launch {
            loadingIndicator.visibility = View.VISIBLE
            try {
                val items = itemsRepository.getItems()
                setupRecyclerView(items)
            } catch (e: Exception) {
                showErrorDialog(e.message ?: "Ошибка загрузки")
            } finally {
                loadingIndicator.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView(items: List<Item>) {
        adapter = ItemsAdapter(
            items = items,
            onItemClick = { item ->
                Toast.makeText(this@Main, "Выбран: ${item.name}", Toast.LENGTH_SHORT).show()
            },
            context = this
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showErrorDialog(message: String) {
        val errorDialog = ErrorDialogFragment(message)
        errorDialog.show(supportFragmentManager, "ErrorDialogTitle")
    }

    override fun onDialogDismissed() {
        Toast.makeText(this, "Диалог закрыт", Toast.LENGTH_SHORT).show()
    }
}


