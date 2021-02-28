package com.example.graphqlserver.service

import com.example.graphqlserver.entity.Film
import com.example.graphqlserver.entity.Hero
import com.example.graphqlserver.type.LightSaber
import java.time.LocalDate
import java.time.Month
import javax.enterprise.context.ApplicationScoped

/**
 * Galaxy Service
 * @author treetips
 */
@ApplicationScoped
class GalaxyService {
  private val heroes: MutableList<Hero> = mutableListOf()
  private val films: MutableList<Film> = mutableListOf()

  fun allFilms(): List<Film> = films

  fun getFilm(id: Int): Film = films[id]

  fun getHeroesByFilm(film: Film): List<Hero> =
      heroes.filter { it.episodeIds.contains(film.episodeID) }

  fun addHero(hero: Hero) {
    heroes.add(hero)
  }

  fun deleteHero(id: Int): Hero = heroes.removeAt(id)

  fun getHeroesBySurname(surname: String): List<Hero> = heroes.filter { it.surname.equals(surname) }

  init {
    Film()
        .apply {
          title = "A New Hope"
          releaseDate = LocalDate.of(1977, Month.MAY, 25)
          episodeID = 4
          director = "George Lucas"
        }
        .let { films.add(it) }

    Film()
        .apply {
          title = "The Empire Strikes Back"
          releaseDate = LocalDate.of(1980, Month.MAY, 21)
          episodeID = 5
          director = "George Lucas"
        }
        .let { films.add(it) }

    Film()
        .apply {
          title = "Return Of The Jedi"
          releaseDate = LocalDate.of(1983, Month.MAY, 25)
          episodeID = 6
          director = "George Lucas"
        }
        .let { films.add(it) }

    Hero()
        .apply {
          name = "Luke"
          surname = "Skywalker"
          height = 1.7
          mass = 73
          lightSaber = LightSaber.GREEN
          darkSide = false
          episodeIds.addAll(listOf(4, 5, 6))
        }
        .let { heroes.add(it) }

    Hero()
        .apply {
          name = "Leia"
          surname = "Organa"
          height = 1.5
          mass = 51
          darkSide = false
          episodeIds.addAll(listOf(4, 5, 6))
        }
        .let { heroes.add(it) }

    Hero()
        .apply {
          name = "Darth"
          surname = "Vader"
          height = 1.9
          mass = 89
          darkSide = true
          lightSaber = LightSaber.RED
          episodeIds.addAll(listOf(4, 5, 6))
        }
        .let { heroes.add(it) }
  }
}
