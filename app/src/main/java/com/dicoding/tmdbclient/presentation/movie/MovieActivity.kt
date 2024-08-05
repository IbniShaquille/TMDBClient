package com.dicoding.tmdbclient.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tmdbclient.R
import com.dicoding.tmdbclient.databinding.ActivityMovieBinding
import com.dicoding.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (application as Injector).createMovieSubComponent()
            .inject(this)
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovies() {
        showLoading(true)
        movieViewModel.updateMovies().observe(this) {
            if (it != null) {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
            } else {
                showLoading(false)
                Toast.makeText(applicationContext, "Refresh Failed", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        showLoading(true)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        movieViewModel.getMovies().observe(this) {
            if (it != null) {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                showLoading(false)
            } else {
                showLoading(false)
                Toast.makeText(applicationContext, "Data is Not Available", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.movieProgressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}