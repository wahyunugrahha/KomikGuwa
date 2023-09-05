package com.example.komikguwe

import android.content.Intent
import com.example.komikguwe.adapter.ListChapterAdapter
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.komikguwe.databinding.ActivityDetailBinding
import com.example.komikguwe.data.Chapter
import com.example.komikguwe.data.Komik

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity(), ListChapterAdapter.OnItemClickListener {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var rvChapter: RecyclerView
    private val list = ArrayList<Chapter>() // Deklarasi & inisialisasi list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.actionShare.setOnClickListener {
            shareContent()
        }

        val komik = intent.getParcelableExtra<Komik>("komik_data")
        if (komik != null) {
            // Tampilkan data
            Glide.with(this)
                .load(komik.photo)
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = komik.name
            binding.tvItemDescription.text = komik.description
            binding.tvItemAuthor.text = komik.author
            binding.tvItemStatus.text = komik.status
        }
        //RecycleViewChapter
        rvChapter = binding.rvChapter
        rvChapter.setHasFixedSize(true)

        list.addAll(getListChapter())
        showRecyclerList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    //RVGet
    private fun getListChapter(): ArrayList<Chapter> {
        val dataChapter = resources.getStringArray(R.array.data_chapter)
        val listChapter = ArrayList<Chapter>()
        for (i in dataChapter.indices) {
            val chapter = Chapter(dataChapter[i])
            listChapter.add(chapter)
        }
        return listChapter
    }

    private fun showRecyclerList() {
        rvChapter.layoutManager = LinearLayoutManager(this)
        val adapter = ListChapterAdapter(list, this)
        rvChapter.adapter = adapter
    }

    override fun onItemClick(chapter: Chapter) {
        val chapterName = chapter.no
        Toast.makeText(this, "Anda memilih chapter $chapterName", Toast.LENGTH_SHORT).show()
    }

    private fun shareContent() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/wahyunugrahha")
        startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
    }
}
