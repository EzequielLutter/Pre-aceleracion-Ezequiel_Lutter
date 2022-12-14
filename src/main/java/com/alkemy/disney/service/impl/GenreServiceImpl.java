package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private final GenreMapper genreMapper;

    @Autowired
    private final GenreRepository genreRepository;

    //guarda el genero en el repositorio
    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity savedEntity = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(savedEntity);
        return result;
    }

    public GenreEntity getGenreEntityById(Long id) {
        Optional<GenreEntity> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new ParamNotFound("Genre with id: " + id + " not found");
        }
        return genre.get();
    }
}
