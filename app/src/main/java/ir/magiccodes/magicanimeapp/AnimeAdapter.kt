package ir.magiccodes.magicanimeapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AnimeAdapter(private val data: ArrayList<Anime>, private val animeEvent: AnimeEvent ) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {


    inner class AnimeViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        val animeImage = itemView.findViewById<ImageView>(R.id.item_img_animeimage)
        val animeName = itemView.findViewById<TextView>(R.id.item_txt_anime_name)
        val animeGenres = itemView.findViewById<TextView>(R.id.tv_activity_item_clicked_genres)
        val animeStatus = itemView.findViewById<TextView>(R.id.tv_activity_item_clicked_status)
        val animePremiered = itemView.findViewById<TextView>(R.id.tv_activity_item_clicked_premiered)
        val animeRatingbar = itemView.findViewById<RatingBar>(R.id.rating_activity_item_clicked)
        val numOfRating = itemView.findViewById<TextView>(R.id.tv_activity_item_clicked_numofrating)

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            animeName.text = data[position].txtName
            animeGenres.text = data[position].txtGenres
            animeStatus.text = data[position].txtStatus
            animePremiered.text = data[position].txtPremiered
            animeRatingbar.rating = data[position].Rating
            numOfRating.text = "(" + data[position].numOfRating.toString() + " ratings)"

            Glide.with(context)
                .load(data[position].urlImage)
                .transform(RoundedCornersTransformation(25, 0))
                .into(animeImage)

            itemView.setOnClickListener {

                animeEvent.onAnimeclicked(data[adapterPosition])
            }

            itemView.setOnLongClickListener{

                animeEvent.onAnimeLongclicked(data[adapterPosition] , adapterPosition)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun addAnime(newAnime: Anime) {
        data.add(0 , newAnime)
        notifyItemInserted(0)
    }

    fun removeAnime(oldAnime: Anime, oldPositiom:Int){

        data.remove(oldAnime)
        notifyItemRemoved(oldPositiom)
    }

    fun updateAnime(newAnime: Anime, position: Int){
        data.set(position , newAnime)
        notifyItemChanged(position)
    }

    fun setData(newList :ArrayList<Anime>){
        // set new data to list
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }


    interface AnimeEvent{

        fun onAnimeclicked(anime:Anime)
        fun onAnimeLongclicked(anime:Anime , pos:Int)
    }
}