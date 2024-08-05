package com.dicoding.tmdbclient.presentation.artist

import android.opengl.Visibility
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
import com.dicoding.tmdbclient.databinding.ActivityArtistBinding
import com.dicoding.tmdbclient.presentation.di.Injector
import okhttp3.internal.notify
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var binding: ActivityArtistBinding
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (application as Injector).createArtistSubComponent()
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
                updateArtistList()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateArtistList() {
        showLoading(true)
        artistViewModel.updateArtist().observe(this) {
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
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter
        displayArtistList()
    }

    private fun displayArtistList() {
        showLoading(true)
        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class.java]
        artistViewModel.getArtist().observe(this) {
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
        binding.artistProgressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}