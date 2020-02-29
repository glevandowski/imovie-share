package com.levandowski.imovieshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToRecyclerView(
            adapter = getAdapter(),
            layoutManager = GridLayoutManager(this, NUMBER_COLUMNS)
        )
    }

    private fun getMockMovies() = listOf(
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars"),
        Movie(title = "tarantino"),
        Movie(title = "Star wars")
    )

    private fun getAdapter() = MovieAdapter(getMockMovies()) {
        Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
    }

    private fun setupToRecyclerView(
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        findViewById<RecyclerView>(R.id.recycler_view).run {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
    }

    companion object {
        const val NUMBER_COLUMNS = 2
    }
}
