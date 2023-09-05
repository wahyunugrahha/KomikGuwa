package com.example.komikguwe

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komikguwe.adapter.ListKomikAdapter
import com.example.komikguwe.databinding.ActivityMainBinding
import com.example.komikguwe.data.Komik

class MainActivity : AppCompatActivity() {
    private lateinit var rvKomik: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Komik>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        rvKomik = binding.rvKomik
        rvKomik.setHasFixedSize(true)
        list.addAll(getListKomik())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


    private fun getListKomik(): ArrayList<Komik> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataStatus = resources.getStringArray(R.array.data_status)
        val listKomik = ArrayList<Komik>()
        for (i in dataName.indices) {
            val komik =
                Komik(dataName[i], dataDescription[i], dataPhoto[i], dataAuthor[i], dataStatus[i])
            listKomik.add(komik)
        }
        return listKomik
    }

    fun onClick(komik: Komik) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("komik_data", komik)
        startActivity(intent)
    }

    private fun showRecyclerList() {
        rvKomik.layoutManager = LinearLayoutManager(this)
        val listKomikAdapter = ListKomikAdapter(list)
        rvKomik.adapter = listKomikAdapter

        listKomikAdapter.setOnItemClickCallback(object : ListKomikAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Komik) {
                onClick(data)
            }
        })
    }
}
