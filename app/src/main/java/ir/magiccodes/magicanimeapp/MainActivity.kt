package ir.magiccodes.magicanimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.magiccodes.magicanimeapp.databinding.*

const val KEY_SEND_DATA = "data"

class MainActivity() : AppCompatActivity(), AnimeAdapter.AnimeEvent {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: AnimeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeList = arrayListOf(
            Anime(
                "https://s6.uupload.ir/files/takt_op._destiny_6z3b.jpg",
                "https://s6.uupload.ir/files/11_n56v.jpg",
                "Takt Op. Destiny",
                "fantasy, adventure, music",
                "Finished Airing",
                "Fall 2021",
                491,
                3.6f
            ),
            Anime(
                "https://s6.uupload.ir/files/anime_deep_aeaf.png",
                "https://s6.uupload.ir/files/2_bm1k.png",
                "Deep Insanity: The Lost Child",
                "action, Sci-Fi",
                "Finished Airing",
                "winer 2021",
                216,
                2.8f
            ),
            Anime(
                "https://s6.uupload.ir/files/image_test_caz4.jpg",
                "https://s6.uupload.ir/files/3_7sft.png",
                "Kimetsu no Yaiba: Yuukaku-hen",
                "Action, Supernatural",
                "Currently Airing",
                "winer 2022",
                314,
                4.3f
            ),
            Anime(
                "https://s6.uupload.ir/files/10_yvkn.jpg",
                "https://s6.uupload.ir/files/100_9msi.jpg",
                "One Piece",
                "Action, Adventure, Fantasy",
                "Currently Airing",
                "Winter 1999",
                965,
                4.3f
            ),
            Anime(
                "https://s6.uupload.ir/files/my-hero-academia-characters-mosaic-i63330_qnzb.jpg",
                "https://s6.uupload.ir/files/4_2177.jpg",
                "Boku no Hero Academia",
                "Action, Comedy",
                "Finished Airing",
                "Spring 2016",
                600,
                4f
            ),
            Anime(
                "https://s6.uupload.ir/files/hunter_nae5.jpg",
                "https://s6.uupload.ir/files/5_qazk.jpg",
                "Hunter x Hunter",
                "Adventure, Fantasy",
                "Finished Airing",
                "Fall 2011",
                760,
                4.6f
            ),
            Anime(
                "https://s6.uupload.ir/files/96541_6sdn.jpg",
                "https://s6.uupload.ir/files/6_jl7t.jpeg",
                "Fullmetal Alchemist: Brotherhood",
                "Comedy, Drama, Action",
                "Finished Airing",
                "Spring 2009",
                540,
                4.7f
            ),
            Anime(
                "https://s6.uupload.ir/files/75195_eh0t.jpg",
                "https://s6.uupload.ir/files/7_d6ke.png",
                "Ao no Exorcist",
                "Fantasy, Supernatural",
                "Finished Airing",
                "Spring 2011",
                322,
                3.7f
            ),
            Anime(
                "https://s6.uupload.ir/files/73474_ytao.jpg",
                "https://s6.uupload.ir/files/8_50v2.jpeg",
                "Owari no Seraph",
                "Action, Drama, Supernatural",
                "Finished Airing",
                "Spring 2015",
                662,
                3.9f
            ),
            Anime(
                "https://s6.uupload.ir/files/1_73ph.jpg",
                "https://s6.uupload.ir/files/9_lxt4.jpg",
                "Guilty Crown",
                "Romance, Sci-Fi",
                "Finished Airing",
                "Fall 2011",
                825,
                3.5f
            ),
            Anime(
                "https://s6.uupload.ir/files/3_n89n.jpg",
                "https://s6.uupload.ir/files/011_nnb9.jpg",
                "Sword Art Online",
                "Fantasy, action",
                "Finished Airing",
                "Summer 2012",
                927,
                3.8f
            ),
            Anime(
                "https://s6.uupload.ir/files/4_9xu9.jpg",
                "https://s6.uupload.ir/files/012_qghe.jpg",
                "Tate no Yuusha no Nariagari",
                "Action, Adventure, Drama,",
                "Finished Airing",
                "Winter 2019",
                541,
                4.1f
            )
        )

        myAdapter = AnimeAdapter(animeList.clone() as ArrayList<Anime>, this)
        binding.recyclermain.adapter = myAdapter
        binding.recyclermain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        AddNewItem(animeList)

        Search(animeList)
    }


    fun AddNewItem(animeList: ArrayList<Anime>){
        binding.btnAddNewAnime.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val dialogbinding = DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialogbinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogbinding.dialogBtnDone.setOnClickListener {

                if (
                    dialogbinding.dialogEdtAnimename.length() > 0 &&
                    dialogbinding.dialogEdtAnimegenres.length() > 0 &&
                    dialogbinding.dialogEdtAnimestatus.length() > 0 &&
                    dialogbinding.dialogEdtAnimepremiered.length() > 0
                ) {
                    val txtname = dialogbinding.dialogEdtAnimename.text.toString()
                    val txtgenres = dialogbinding.dialogEdtAnimegenres.text.toString()
                    val txtstatus = dialogbinding.dialogEdtAnimestatus.text.toString()
                    val txtpremiered = dialogbinding.dialogEdtAnimepremiered.text.toString()
                    val txtnumOfRating: Float = (1..5).random().toFloat()
                    val ratingbarstar: Int = (1..999).random()

                    val randonForUrl: Int = (0..11).random()
                    val urlPic = animeList[randonForUrl].urlImage
                    val urlPicTtle = animeList[randonForUrl].urlImageTitle
                    val newAnime = Anime(
                        urlPic,
                        urlPicTtle,
                        txtname,
                        txtgenres,
                        txtstatus,
                        txtpremiered,
                        ratingbarstar,
                        txtnumOfRating
                    )
                    myAdapter.addAnime(newAnime)

                    dialog.dismiss()
                    binding.recyclermain.scrollToPosition(0)

                } else {
                    Toast.makeText(this, " لطفا مقادیر را وارد کنید :)", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun Search(animeList: ArrayList<Anime>){
        binding.edtSearch.addTextChangedListener { editTextInput ->

            if (editTextInput!!.isNotEmpty()) {

                val cloneList = animeList.clone() as ArrayList<Anime>
                val filteredList = cloneList.filter { animeItem ->
                    animeItem.txtName.contains(editTextInput)
                }

                myAdapter.setData(ArrayList(filteredList))

            } else {
                // show all data
                myAdapter.setData(animeList.clone() as ArrayList<Anime>)

            }
        }
    }

    override fun onAnimeclicked(anime: Anime) {

        val intent = Intent(this, ItemClickedActivity::class.java)
        intent.putExtra(KEY_SEND_DATA, anime)
        startActivity(intent)
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
                dialog.dismiss()
            }
            dialogParent.dismiss()
        }

        deleteDialogBinding.btnUpdate.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val updateDialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
            dialog.setView(updateDialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            updateDialogBinding.dialogEdtAnimename.setText(anime.txtName)
            updateDialogBinding.dialogEdtAnimegenres.setText(anime.txtGenres)
            updateDialogBinding.dialogEdtAnimestatus.setText(anime.txtStatus)
            updateDialogBinding.dialogEdtAnimepremiered.setText(anime.txtPremiered)

            updateDialogBinding.dialogUpdateBtnCancel.setOnClickListener {
                dialog.dismiss()
            }

            updateDialogBinding.dialogUpdateBtnDone.setOnClickListener {
                if (
                    updateDialogBinding.dialogEdtAnimename.length() > 0 &&
                    updateDialogBinding.dialogEdtAnimegenres.length() > 0 &&
                    updateDialogBinding.dialogEdtAnimestatus.length() > 0 &&
                    updateDialogBinding.dialogEdtAnimepremiered.length() > 0
                ) {
                    val txtname = updateDialogBinding.dialogEdtAnimename.text.toString()
                    val txtgenres = updateDialogBinding.dialogEdtAnimegenres.text.toString()
                    val txtstatus = updateDialogBinding.dialogEdtAnimestatus.text.toString()
                    val txtpremiered = updateDialogBinding.dialogEdtAnimepremiered.text.toString()

                    // creat new anime to add to recycler view
                    val newAnime = Anime(
                        anime.urlImage,
                        anime.urlImageTitle,
                        txtname,
                        txtgenres,
                        txtstatus,
                        txtpremiered,
                        anime.numOfRating,
                        anime.Rating
                    )

                    //update item
                    myAdapter.updateAnime(newAnime, pos)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, " لطفا مقادیر را وارد کنید :)", Toast.LENGTH_LONG).show()
                }
            }
            dialogParent.dismiss()
        }
    }

}

