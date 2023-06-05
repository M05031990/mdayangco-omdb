package com.mdq.mdayangcomovies.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mdq.mdayangcomovies.R
import com.mdq.mdayangcomovies.R.string.empty_string
import com.mdq.mdayangcomovies.R.string.generic_error
import com.mdq.mdayangcomovies.data.model.DataResponse
import com.mdq.mdayangcomovies.data.model.OMDBModel
import com.mdq.mdayangcomovies.databinding.ActivityDetailsBinding
import com.mdq.mdayangcomovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        intent.extras?.getString(KEY_OMDB_DATA)?.let { id ->
            viewModel.getDetails(id) {
                when (it) {
                    is DataResponse.Success -> populateView(it.data)
                    is DataResponse.Error -> Toast.makeText(
                        this,
                        it.throwable.message ?: getString(generic_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun populateView(omdb: OMDBModel) {
        binding.txtTitle.text = omdb.title
        val appendRateAndRunTime = if (omdb.rated?.lowercase() == N_A.lowercase()) omdb.runtime
        else "${omdb.rated} • ${omdb.runtime}"
        binding.txtRated.text = appendRateAndRunTime
        binding.txtYear.text = string(omdb.year)
        binding.genre = string(omdb.genre)
        binding.release = string(omdb.released)
        binding.plot = string(omdb.plot)
        binding.language = string(omdb.language)
        binding.country = string(omdb.country)
        binding.directors = string(omdb.director)
        binding.writers = string(omdb.writer)
        binding.stars = string(omdb.actors)

        Glide.with(binding.root)
            .load(omdb.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageView)

        Glide.with(binding.root)
            .load(omdb.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgPoster)
    }

    private fun string(str: String?): String {
        return if (str.isNullOrEmpty() || str.lowercase() == N_A.lowercase()) getString(empty_string)
        else str
    }

    companion object {
        private const val KEY_OMDB_DATA = "omdb-data"
        private const val N_A = "N/A"
        fun startActivity(context: Context, imdbID: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_OMDB_DATA, imdbID)
            context.startActivity(intent)
        }
    }
}