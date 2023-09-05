package com.example.komikguwe

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val imageUrl =
            "https://media.licdn.com/dms/image/D5603AQH91CegaSQ3BQ/profile-displayphoto-shrink_200_200/0/1690540571886?e=1699488000&v=beta&t=A9Jvq__Ra-Ervc21JNzdVhjL-dG03Ev6zwHU-CqG6LE"
        val imageView: ImageView = findViewById(R.id.img_item_photo)
        Glide.with(this)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(imageView)
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
}
