package ir.magiccodes.magicanimeapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1 , exportSchema = false , entities = [Anime::class])
abstract class MyDatabase : RoomDatabase() {
    abstract val animeDao :AnimeDao

    companion object{

        private var dataBase: MyDatabase? = null
        fun getDatabase(context: Context) :MyDatabase{
            if (dataBase == null){
                dataBase = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dataBase!!
        }
    }
}