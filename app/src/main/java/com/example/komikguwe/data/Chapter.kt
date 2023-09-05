package com.example.komikguwe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val no: String
) : Parcelable