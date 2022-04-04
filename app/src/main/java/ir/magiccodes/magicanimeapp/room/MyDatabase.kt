package ir.magiccodes.magicanimeapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1 , exportSchema = false , entities = [Anime::class])
abstract class MyDatabase : RoomDatabase() {
    abstract val animeDao :AnimeDao

    companion object{

        private val dataBase: MyDatabase? = null
        fun getDatabase(context: Context) :MyDatabase{
            var instance = dataBase
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                ).build()
            }
            return instance
        }
    }
}