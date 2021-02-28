package com.example.graphqlserver.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

/**
 * FilmResource Test
 * @author treetips
 */
@QuarkusTest
class FilmResourceTest {

  @Test
  fun testHelloEndpoint() {
    given()
        .headers(mapOf("Content-Type" to "application/json", "Charset" to "UTF-8"))
        .body("{ \"query\": \"query getFilm {film(filmId: 0) {title}}\"}")
        .`when`()
        .post("/graphql")
        .then()
        .statusCode(200)
        .body(`is`("{\"data\":{" + "\"film\":{\"title\":\"A New Hope\"}" + "}}"))
  }
}
