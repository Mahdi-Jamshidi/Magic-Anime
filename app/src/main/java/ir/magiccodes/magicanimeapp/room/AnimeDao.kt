package ir.magiccodes.magicanimeapp.room

import androidx.room.*

@Dao
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