package ir.magiccodes.magicanimeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(

    val urlImage: String,
    val urlImageTitle: String,
    val txtName: String,
    val txtGenres: String,
    val txtStatus: String,
    val txtPremiered: String,
    val numOfRating: Int,
    val Rating: Float
) : Parcelable
