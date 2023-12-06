package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieAddRequest;
import com.pivovarit.movies.repository.MovieRepository;
import com.pivovarit.movies.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RentalServiceTest {
 
    @InjectMocks
    private RentalService rentalService;
 
    @Mock
    private MovieRepository movieRepository;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void saveTest() {
        MovieAddRequest movieAddRequest =  new MovieAddRequest(14, "foo", "NEW");
        rentalService.save(movieAddRequest);
        verify(movieRepository, times(1)).save(any(Movie.class));
    }
 
    @Test
    public void findAllTest() {
        rentalService.findAll();
        verify(movieRepository, times(1)).findAll();
    }
 
    @Test
    public void findAllByTypeTest() {
        MovieType movieType = MovieType.NEW;
        rentalService.findAllByType(movieType);
        verify(movieRepository, times(1)).findAll();
    }
 
    @Test
    public void findByIdTest() {
        int id =  1;
        rentalService.findById(id);
        verify(movieRepository, times(1)).findById(any(MovieId.class));
    }
}
