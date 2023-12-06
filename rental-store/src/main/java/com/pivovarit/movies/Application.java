package com.pivovarit.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // GET /movies
    // GET /movies?type=NEW
    // POST /movies
    // GET /movies/{id}

    /*
      curl --header "Content-Type: application/json" \
   --request POST \
   --data '{ "id":14, "title":"spiderman", "type":"NEW"}' \
  http://localhost:8081/movies
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
