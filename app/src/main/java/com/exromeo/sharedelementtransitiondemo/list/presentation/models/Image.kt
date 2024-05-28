package com.exromeo.sharedelementtransitiondemo.list.presentation.models

import androidx.annotation.DrawableRes
import com.exromeo.sharedelementtransitiondemo.R
import kotlin.random.Random

data class Image(
    val id: Int,
    @DrawableRes val imageId: Int,
    val desc: String = "$id image description" /*@StringRes val description: Int*/
) {

    companion object {
        val list = listOf(
            Image(
                id = 1,
                imageId = R.drawable.img_skyscrapers,
            ),
            Image(
                id = 2,
                imageId = R.drawable.img_night_scenery
            ),
            Image(
                id = 3,
                imageId = R.drawable.img_shirt_hangers
            ),
            Image(
                id = 4,
                imageId = R.drawable.img_sunset_scenery
            ),
            Image(
                id = 5,
                imageId = R.drawable.img_snowy_mountains
            ),
/*            Image(
                id = 6,
                imageId = R.drawable.img_skyscrapers
            ),
            Image(
                id = 7,
                imageId = R.drawable.img_night_scenery
            ),
            Image(
                id = 8,
                imageId = R.drawable.img_shirt_hangers
            ),
            Image(
                id = 9,
                imageId = R.drawable.img_sunset_scenery
            ),
            Image(
                id = 10,
                imageId = R.drawable.img_snowy_mountains
            ),*/
        )

        fun getItem(id: Int?) = list.find { it.id == id }
    }
}