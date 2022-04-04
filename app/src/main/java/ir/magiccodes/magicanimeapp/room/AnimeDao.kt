package ir.magiccodes.magicanimeapp.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface AnimeDao {

    @Insert
    fun insertAnime(anime: Anime)

    @Update
    fun updateAnime(anime: Anime)

    @Delete
    fun deleteAnime(anime: Anime)

    @Query("DELETE FROM table_anime")
    fun deleteAllAnimes()

    @Query(" SELECT * FROM table_anime")
    fun getAllAnimes() : List<Anime>

    @Query("SELECT * FROM table_anime WHERE txtName LIKE '%'|| :searching || '%'")
    fun searchAnimes(searching : String) : List<Anime>
}