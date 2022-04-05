package ir.magiccodes.magicanimeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.magiccodes.magicanimeapp.databinding.*
import ir.magiccodes.magicanimeapp.room.Anime
import ir.magiccodes.magicanimeapp.room.AnimeDao
import ir.magiccodes.magicanimeapp.room.MyDatabase

const val KEY_SEND_DATA = "data"

class MainActivity() : AppCompatActivity(), AnimeAdapter.AnimeEvent {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: AnimeAdapter
    lateinit var animeDao: AnimeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animeDao = MyDatabase.getDatabase(this).animeDao

        val sharedPreferences = getSharedPreferences("magicAnime" , Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run" , true)){
            firstRun()
            sharedPreferences.edit().putBoolean("first_run" , false).apply()
        }

        showAllData()

        binding.btnRemoveAllAnime.setOnClickListener {
            removeAllData()
        }

//        AddNewItem(animeList)
//
//        Search(animeList)
    }

    private fun removeAllData() {
        animeDao.deleteAllAnimes()
        showAllData()
    }

    private fun firstRun(){
        val animeList = arrayListOf(
            Anime(
                urlImage = "https://s6.uupload.ir/files/takt_op._destiny_6z3b.jpg",
                urlImageTitle = "https://s6.uupload.ir/files/11_n56v.jpg",
                txtName = "Takt Op. Destiny",
                txtGenres = "fantasy, adventure, music",
                txtStatus = "Finished Airing",
                txtPremiered = "Fall 2021",
                numOfRating = 491,
                Rating = 3.6f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/anime_deep_aeaf.png",
                urlImageTitle="https://s6.uupload.ir/files/2_bm1k.png",
                txtName="Deep Insanity: The Lost Child",
                txtGenres ="action, Sci-Fi",
                txtStatus ="Finished Airing",
                txtPremiered ="winer 2021",
                numOfRating = 216,
                Rating =2.8f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/image_test_caz4.jpg",
                urlImageTitle="https://s6.uupload.ir/files/3_7sft.png",
                txtName="Kimetsu no Yaiba: Yuukaku-hen",
                txtGenres ="Action, Supernatural",
                txtStatus ="Currently Airing",
                txtPremiered ="winer 2022",
                numOfRating =314,
                Rating =4.3f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/10_yvkn.jpg",
                urlImageTitle="https://s6.uupload.ir/files/100_9msi.jpg",
                txtName="One Piece",
                txtGenres ="Action, Adventure, Fantasy",
                txtStatus ="Currently Airing",
                txtPremiered ="Winter 1999",
                numOfRating =965,
                Rating =4.3f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/my-hero-academia-characters-mosaic-i63330_qnzb.jpg",
                urlImageTitle="https://s6.uupload.ir/files/4_2177.jpg",
                txtName="Boku no Hero Academia",
                txtGenres ="Action, Comedy",
                txtStatus ="Finished Airing",
                txtPremiered ="Spring 2016",
                numOfRating =600,
                Rating =4f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/hunter_nae5.jpg",
                urlImageTitle="https://s6.uupload.ir/files/5_qazk.jpg",
                txtName="Hunter x Hunter",
                txtGenres ="Adventure, Fantasy",
                txtStatus ="Finished Airing",
                txtPremiered ="Fall 2011",
                numOfRating =760,
                Rating =4.6f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/96541_6sdn.jpg",
                urlImageTitle="https://s6.uupload.ir/files/6_jl7t.jpeg",
                txtName="Fullmetal Alchemist: Brotherhood",
                txtGenres ="Comedy, Drama, Action",
                txtStatus ="Finished Airing",
                txtPremiered ="Spring 2009",
                numOfRating =540,
                Rating =4.7f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/75195_eh0t.jpg",
                urlImageTitle="https://s6.uupload.ir/files/7_d6ke.png",
                txtName="Ao no Exorcist",
                txtGenres ="Fantasy, Supernatural",
                txtStatus ="Finished Airing",
                txtPremiered ="Spring 2011",
                numOfRating =322,
                Rating =3.7f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/73474_ytao.jpg",
                urlImageTitle="https://s6.uupload.ir/files/8_50v2.jpeg",
                txtName="Owari no Seraph",
                txtGenres ="Action, Drama, Supernatural",
                txtStatus ="Finished Airing",
                txtPremiered ="Spring 2015",
                numOfRating =662,
                Rating =3.9f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/1_73ph.jpg",
                urlImageTitle="https://s6.uupload.ir/files/9_lxt4.jpg",
                txtName="Guilty Crown",
                txtGenres ="Romance, Sci-Fi",
                txtStatus ="Finished Airing",
                txtPremiered ="Fall 2011",
                numOfRating =825,
                Rating =3.5f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/3_n89n.jpg",
                urlImageTitle="https://s6.uupload.ir/files/011_nnb9.jpg",
                txtName="Sword Art Online",
                txtGenres ="Fantasy, action",
                txtStatus ="Finished Airing",
                txtPremiered ="Summer 2012",
                numOfRating =927,
                Rating =3.8f
            ),
            Anime(
                urlImage="https://s6.uupload.ir/files/4_9xu9.jpg",
                urlImageTitle="https://s6.uupload.ir/files/012_qghe.jpg",
                txtName="Tate no Yuusha no Nariagari",
                txtGenres ="Action, Adventure, Drama,",
                txtStatus ="Finished Airing",
                txtPremiered ="Winter 2019",
                numOfRating =541,
                Rating =4.1f
            )
        )
        animeDao.insertAllAnimes(animeList)
    }

    private fun showAllData(){

        val animeData = animeDao.getAllAnimes()
        myAdapter = AnimeAdapter(ArrayList(animeData), this)
        binding.recyclermain.adapter = myAdapter
        binding.recyclermain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    fun AddNewItem(animeList: ArrayList<Anime>) {
//        binding.btnAddNewAnime.setOnClickListener {
//
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogbinding = DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialogbinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            dialogbinding.dialogBtnDone.setOnClickListener {
//
//                if (
//                    dialogbinding.dialogEdtAnimename.length() > 0 &&
//                    dialogbinding.dialogEdtAnimegenres.length() > 0 &&
//                    dialogbinding.dialogEdtAnimestatus.length() > 0 &&
//                    dialogbinding.dialogEdtAnimepremiered.length() > 0
//                ) {
//                    val txtname = dialogbinding.dialogEdtAnimename.text.toString()
//                    val txtgenres = dialogbinding.dialogEdtAnimegenres.text.toString()
//                    val txtstatus = dialogbinding.dialogEdtAnimestatus.text.toString()
//                    val txtpremiered = dialogbinding.dialogEdtAnimepremiered.text.toString()
//                    val txtnumOfRating: Float = (1..5).random().toFloat()
//                    val ratingbarstar: Int = (1..999).random()
//
//                    val randonForUrl: Int = (0..11).random()
//                    val urlPic = animeList[randonForUrl].urlImage
//                    val urlPicTtle = animeList[randonForUrl].urlImageTitle
//                    val newAnime = Anime(
//                        urlImage= urlPic,
//                        urlImageTitle= urlPicTtle,
//                        txtName= txtname,
//                        txtGenres = txtgenres,
//                        txtStatus = txtstatus,
//                        txtPremiered = txtpremiered,
//                        numOfRating = ratingbarstar,
//                        Rating = txtnumOfRating
//                    )
//                    myAdapter.addAnime(newAnime)
//
//                    dialog.dismiss()
//                    binding.recyclermain.scrollToPosition(0)
//
//                } else {
//                    Toast.makeText(this, " لطفا مقادیر را وارد کنید :)", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }

    fun Search(animeList: ArrayList<Anime>) {

//        binding.edtSearch.addTextChangedListener { editTextInput ->
//
//            if (editTextInput!!.isNotEmpty()) {
//
//                val cloneList = animeList.clone() as ArrayList<Anime>
//                val filteredList = cloneList.filter { animeItem ->
//                    animeItem.txtName.contains(editTextInput)
//                }
//
//                myAdapter.setData(ArrayList(filteredList))
//
//            } else {
//                // show all data
//                myAdapter.setData(animeList.clone() as ArrayList<Anime>)
//
//            }
//        }
    }

    override fun onAnimeclicked(anime: Anime) {

//        val intent = Intent(this, ItemClickedActivity::class.java)
//        intent.putExtra(KEY_SEND_DATA, anime)
//        startActivity(intent)
    }

    override fun onAnimeLongclicked(anime: Anime, pos: Int) {

        val dialogParent = AlertDialog.Builder(this).create()
        val deleteDialogBinding = DialogLongclickBinding.inflate(layoutInflater)
        dialogParent.setView(deleteDialogBinding.root)
        dialogParent.setCancelable(true)
        dialogParent.show()

        deleteDialogBinding.btnDelete.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val deleteDialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
            dialog.setView(deleteDialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            deleteDialogBinding.dialogDeletBtnCancel.setOnClickListener {
                dialog.dismiss()
            }

            deleteDialogBinding.dialogDeletBtnSure.setOnClickListener {
                myAdapter.removeAnime(anime, pos)
                animeDao.deleteAnime(anime)
                dialog.dismiss()
            }
            dialogParent.dismiss()
        }

//        deleteDialogBinding.btnUpdate.setOnClickListener {
//            val dialog = AlertDialog.Builder(this).create()
//            val updateDialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
//            dialog.setView(updateDialogBinding.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            updateDialogBinding.dialogEdtAnimename.setText(anime.txtName)
//            updateDialogBinding.dialogEdtAnimegenres.setText(anime.txtGenres)
//            updateDialogBinding.dialogEdtAnimestatus.setText(anime.txtStatus)
//            updateDialogBinding.dialogEdtAnimepremiered.setText(anime.txtPremiered)
//
//            updateDialogBinding.dialogUpdateBtnCancel.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            updateDialogBinding.dialogUpdateBtnDone.setOnClickListener {
//                if (
//                    updateDialogBinding.dialogEdtAnimename.length() > 0 &&
//                    updateDialogBinding.dialogEdtAnimegenres.length() > 0 &&
//                    updateDialogBinding.dialogEdtAnimestatus.length() > 0 &&
//                    updateDialogBinding.dialogEdtAnimepremiered.length() > 0
//                ) {
//                    val txtname = updateDialogBinding.dialogEdtAnimename.text.toString()
//                    val txtgenres = updateDialogBinding.dialogEdtAnimegenres.text.toString()
//                    val txtstatus = updateDialogBinding.dialogEdtAnimestatus.text.toString()
//                    val txtpremiered = updateDialogBinding.dialogEdtAnimepremiered.text.toString()
//
//                    // creat new anime to add to recycler view
//                    val newAnime = Anime(
//                        urlImage= anime.urlImage,
//                        urlImageTitle= anime.urlImageTitle,
//                        txtName= txtname,
//                        txtGenres = txtgenres,
//                        txtStatus = txtstatus,
//                        txtPremiered = txtpremiered,
//                        numOfRating = anime.numOfRating,
//                        Rating = anime.Rating
//                    )
//
//                    //update item
//                    myAdapter.updateAnime(newAnime, pos)
//                    dialog.dismiss()
//                } else {
//                    Toast.makeText(this, " لطفا مقادیر را وارد کنید :)", Toast.LENGTH_LONG).show()
//                }
//            }
//            dialogParent.dismiss()
//        }
    }

}








