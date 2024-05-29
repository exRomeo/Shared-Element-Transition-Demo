package com.exromeo.sharedelementtransitiondemo.list.presentation.models

import androidx.annotation.DrawableRes
import com.exromeo.sharedelementtransitiondemo.R
import kotlin.random.Random

data class Image(
    val id: Int,
    @DrawableRes val imageId: Int,
    val desc: String = "$id image description"
) {

    companion object {
        val list = listOf(
            Image(
                id = 1,
                imageId = R.drawable.ic_launcher_background,
            ),
            Image(
                id = 2,
                imageId = R.drawable.ic_launcher_foreground
            ),
            Image(
                id = 3,
                imageId = R.drawable.ic_launcher_background
            ),
            Image(
                id = 4,
                imageId = R.drawable.ic_launcher_foreground
            ),
            Image(
                id = 5,
                imageId = R.drawable.ic_launcher_background
            ),
            Image(
                id = 6,
                imageId = R.drawable.ic_launcher_background
            ),
        )

        fun getItem(id: Int?) = list.find { it.id == id }
    }
}