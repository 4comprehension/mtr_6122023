package com.pivovarit.domain.rental;

import com.pivovarit.domain.rental.api.MovieAddRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RentalServiceTest {
 
    @InjectMocks
    private RentalFacade rentalService;
 
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
        String movieType = MovieType.NEW.toString();
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
