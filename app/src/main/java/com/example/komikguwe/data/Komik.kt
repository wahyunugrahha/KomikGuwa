package com.example.komikguwe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Komik(
    val name: String,
    val description: String,
    val photo: String,
    val author: String,
    val status: String
) : Parcelable