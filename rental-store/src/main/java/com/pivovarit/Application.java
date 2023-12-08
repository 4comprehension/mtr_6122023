package com.pivovarit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // TODO dodać kolumnę account_version BIGINT (unique constraint na account_version + account_id)
    // TODO jak zmieniamy stan accounta, to zapisujemy event z podbitą wersją o 1
    // TODO dodać ponawianie (3 razy) przy każdym wykryciu współbieżnej modyfikacji account

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
