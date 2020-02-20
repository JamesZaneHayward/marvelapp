package com.jameshayward.marvelapp.domain.comic

import com.squareup.moshi.Json

data class Comic(
    var id: Int,
    var title: String,
    var issueNumber: Int,
    var description: String,
    var thumbnail: Thumbnail
)

data class Thumbnail(
    @field:Json(name = "path") var path: String,
    @field:Json(name = "extension") var extension: String
)
