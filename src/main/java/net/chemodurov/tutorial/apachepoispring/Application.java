package net.chemodurov.tutorial.apachepoispring;

import net.chemodurov.tutorial.apachepoispring.poi.BookPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private BookPoi bookPoi;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        bookPoi.export(args[0]);
    }
}
