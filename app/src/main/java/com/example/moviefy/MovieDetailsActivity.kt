package com.example.moviefy
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.moviefy.database.AppDatabase
const val ID = 0L
const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var add: Button
    private lateinit var delete: Button
    lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        backdrop = findViewById(R.id.movie_backdrop)
        poster = findViewById(R.id.movie_poster)
        title = findViewById(R.id.movie_title)
        rating = findViewById(R.id.movie_rating)
        releaseDate = findViewById(R.id.movie_release_date)
        overview = findViewById(R.id.movie_overview)

        add = findViewById(R.id.add)
        delete = findViewById(R.id.delete)

        val extras = intent.extras

        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }
        setListener()
    }


    private fun setListener() {
        add.setOnClickListener {
            insert(movie)
        }
        delete.setOnClickListener {
            delete(movie)
        }
    }


    private fun populateDetails(extras: Bundle) {
        extras.getString(MOVIE_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }

        extras.getString(MOVIE_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text = extras.getString(MOVIE_TITLE, "")
        rating.rating = extras.getFloat(MOVIE_RATING, 0f) / 2
        releaseDate.text = extras.getString(MOVIE_RELEASE_DATE, "")
        overview.text = extras.getString(MOVIE_OVERVIEW, "")

        movie = Movie(
            extras.getLong(ID.toString(), 0L),
            extras.getString(MOVIE_TITLE, ""),
            extras.getString(MOVIE_OVERVIEW, ""),
            extras.getString(MOVIE_POSTER).toString(),
            extras.getString(
                MOVIE_BACKDROP
            ).toString(),
            extras.getFloat(MOVIE_RATING, 0f) / 2,
            extras.getString(MOVIE_RELEASE_DATE, "")
        )
        checkButton(movie)
    }

    private fun insert(movie: Movie) {
        val check = AppDatabase.getInstance(this).Dao().check(movie.id)
        val list = AppDatabase.getInstance(this).Dao().get()
        if (check !in list) {
            val finished = AppDatabase.getInstance(this).Dao().insert(movie)
            if (finished > 0) {
                Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show()
                add.setBackgroundResource(R.drawable.faved)
                delete.setBackgroundResource(R.drawable.delete)
            } else {
                Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Already in list", Toast.LENGTH_SHORT).show()
        }
    }

    private fun delete(movie: Movie) {
        val check = AppDatabase.getInstance(this).Dao().check(movie.id)
        val list = AppDatabase.getInstance(this).Dao().get()
        if (check in list) {
            val finished = AppDatabase.getInstance(this).Dao().delete(movie)
            if (finished > 0) {
                Toast.makeText(this, "Deleted Success", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Deleted Failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Not in the list", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkButton(movie: Movie) {
        val check = AppDatabase.getInstance(this).Dao().check(movie.id)
        val list = AppDatabase.getInstance(this).Dao().get()
        if (check in list) {
            add.setBackgroundResource(R.drawable.faved)
            delete.setBackgroundResource(R.drawable.delete)
        } else {
            delete.visibility = View.GONE
        }
    }
}
