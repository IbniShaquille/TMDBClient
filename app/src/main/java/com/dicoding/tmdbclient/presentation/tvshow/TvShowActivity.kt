package com.dicoding.tmdbclient.presentation.tvshow

import android.os.Bundle
import android.view.Menu
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
import com.dicoding.tmdbclient.databinding.ActivityTvShowBinding
import com.dicoding.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (application as Injector).createTvShowSubComponent()
            .inject(this)
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
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
        tvShowViewModel.updateTvShows().observe(this) {
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
        binding.tvShowRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.tvShowRecyclerView.adapter = adapter
        displayTvShowList()
    }

    private fun displayTvShowList() {
        showLoading(true)
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        tvShowViewModel.getTvShows().observe(this) {
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
        binding.tvShowProgressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}