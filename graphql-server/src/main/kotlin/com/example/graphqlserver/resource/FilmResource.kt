package com.example.graphqlserver.resource

import com.example.graphqlserver.entity.Film
import com.example.graphqlserver.entity.Hero
import com.example.graphqlserver.service.GalaxyService
import javax.inject.Inject
import org.eclipse.microprofile.graphql.*

/**
 * Film Resolver
 * @author treetips
 */
@GraphQLApi
class FilmResource {
  @Inject lateinit var galaxyService: GalaxyService

  @Query("allFilms")
  @Description("Get all Films from a galaxy far far away")
  fun getAllFilms(): List<Film> = galaxyService.allFilms()

  @Query("film")
  @Description("Get a Films from a galaxy far far away")
  fun getFilm(@Name("filmId") id: Int): Film = galaxyService.getFilm(id)

  fun getHeroes(@Source film: Film): List<Hero> = galaxyService.getHeroesByFilm(film)

  @Query("heroesWithSurname")
  fun getHeroesWithSurname(@DefaultValue("Skywalker") surname: String): List<Hero> {
    return galaxyService.getHeroesBySurname(surname)
  }

  @Mutation("createHero")
  fun createHero(hero: Hero): Hero {
    galaxyService.addHero(hero)
    return hero
  }

  @Mutation("deleteHero") fun deleteHero(id: Int): Hero = galaxyService.deleteHero(id)
}
