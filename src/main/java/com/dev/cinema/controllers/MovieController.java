package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.mappers.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto requestDto) {
        movieService.add(movieMapper.toMovieModel(requestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::toMovieResponseDto)
                .collect(Collectors.toList());
    }
}
