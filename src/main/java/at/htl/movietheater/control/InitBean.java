package at.htl.movietheater.control;

import at.htl.movietheater.entity.Genre;
import at.htl.movietheater.entity.Movie;
import at.htl.movietheater.entity.Show;
import at.htl.movietheater.entity.Theater;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    MovieRepository movieRepository;

    @Inject
    TheaterRepository theaterRepository;

    @Inject
    ShowRepository showRepository;

    public void onStart(@Observes StartupEvent event) {
        init();
    }

    private void init() {
        readFile("src/main/resources/movies.csv")
            .stream()
            .skip(1)
            .forEach(line -> {
                String[] parts = line.split(";");

                movieRepository.save(
                    new Movie(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Genre.valueOf(parts[2].toUpperCase()),
                        Integer.parseInt(parts[3]))
                );
            });

        readFile("src/main/resources/theaters.csv")
            .stream()
            .skip(1)
            .forEach(line -> {
                String[] parts = line.split(";");

                theaterRepository.save(new Theater(parts[0], Integer.parseInt(parts[1])));
            });

        readFile("src/main/resources/shows.csv")
            .stream()
            .skip(1)
            .forEach(line -> {
                String[] parts = line.split(";");

                showRepository.save(
                    new Show(
                        movieRepository.findByTitle(parts[0]),
                        theaterRepository.findByName(parts[1]),
                        null,
                        null)
                );
            });
    }

    private List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
