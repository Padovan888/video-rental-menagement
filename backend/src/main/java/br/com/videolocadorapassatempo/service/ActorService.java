package br.com.videolocadorapassatempo.service;

import br.com.videolocadorapassatempo.service.dto.ActorDto;

import java.util.List;
import java.util.UUID;

public interface ActorService {

    public List<ActorDto> findAll();

    public ActorDto findById(UUID id);

    public ActorDto save(ActorDto actorDto);

    public void deleteById(UUID id);

}
