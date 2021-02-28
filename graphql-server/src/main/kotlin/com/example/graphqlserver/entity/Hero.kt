package com.example.graphqlserver.entity

import com.example.graphqlserver.type.LightSaber

class Hero {
  var name: String? = null
  var surname: String? = null
  var height: Double? = null
  var mass: Int? = null
  var darkSide: Boolean? = null
  var lightSaber: LightSaber? = null
  var episodeIds: MutableList<Int> = mutableListOf()
}
