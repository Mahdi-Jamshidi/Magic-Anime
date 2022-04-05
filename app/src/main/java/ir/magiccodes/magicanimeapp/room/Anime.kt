package ir.magiccodes.magicanimeapp.room


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_anime")
data class Anime(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null ,
    val urlImage: String,
    val urlImageTitle: String,
    val txtName: String,
    val txtGenres: String,
    val txtStatus: String,
    val txtPremiered: String,
    val numOfRating: Int,
    val Rating: Float
) : Parcelable
