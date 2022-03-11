package ir.magiccodes.magicanimeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ir.magiccodes.magicanimeapp.databinding.ActivityItemClickedBinding

class ItemClickedActivity() : AppCompatActivity() {
    lateinit var binding: ActivityItemClickedBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemClickedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anime = intent.getParcelableExtra<Anime>(KEY_SEND_DATA)!!
        binding.tvActivityItemClickedAnimename.text = anime.txtName
        binding.tvActivityItemClickedGenres.text = anime.txtGenres
        binding.tvActivityItemClickedStatus.text = anime.txtStatus
        binding.tvActivityItemClickedPremiered.text = anime.txtPremiered
        binding.tvActivityItemClickedNumofrating.text = "(" + anime.numOfRating.toString() + " ratings)"
        binding.ratingActivityItemClicked.rating = anime.Rating

        Glide.with(this)
            .load(anime.urlImageTitle)
            .into(binding.imgActivityItemClickedAnimeimage)
    }

}






