package main

import (
	"database/sql"
	_ "github.com/lib/pq"
	"log"
)

/*
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.password=mysecretpassword
spring.datasource.username=postgres
*/
func main() {
	psqlInfo := "host=172.17.0.2 port=5432 user=postgres password=mysecretpassword dbname=postgres sslmode=disable"
	db, err := sql.Open("postgres", psqlInfo)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}

	//select all entries in the rental_history table
	results, err := db.Query("SELECT event_type FROM rental_history WHERE processed = false ORDER BY id")
	if err != nil {
		log.Fatal(err)
	}
	defer results.Close()

	for results.Next() {
		var id string
		err = results.Scan(&id)
		if err != nil {
			log.Fatal(err)
		}
		println(id)
	}
}
