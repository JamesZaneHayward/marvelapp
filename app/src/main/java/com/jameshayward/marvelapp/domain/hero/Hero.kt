package com.jameshayward.marvelapp.domain.hero

import com.squareup.moshi.Json

data class Hero(
    @field:Json(name = "name") var name: String,
    @field:Json(name = "thumbnail") var thumbnail: Thumbnail,
    @field:Json(name = "description") var description: String
)

data class Thumbnail(
    @field:Json(name = "path") var path: String,
    @field:Json(name = "extension") var extension: String
)
