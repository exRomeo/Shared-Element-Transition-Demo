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
                imageId = R.drawable.img_stage,
            ),
            Image(
                id = 2,
                imageId = R.drawable.img_clouds
            ),
            Image(
                id = 3,
                imageId = R.drawable.img_glasses_on_desk
            ),
            Image(
                id = 4,
                imageId = R.drawable.img_doggy
            ),
            Image(
                id = 5,
                imageId = R.drawable.img_reeds_field
            ),
            Image(
                id = 6,
                imageId = R.drawable.img_plant_in_a_glass
            ),
        )

        fun getItem(id: Int?) = list.find { it.id == id }
    }
}