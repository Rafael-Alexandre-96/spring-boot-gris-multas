package br.com.gris.multas.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gris.multas.domain.model.Motorista;
import br.com.gris.multas.domain.repository.MotoristaRepository;

@Service
public class MotoristaService {
    @Autowired private MotoristaRepository repository;

    public List<Motorista> findAll() {
        return repository.findAll();
    }

    public Motorista findById(@NonNull String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not founded."));
    }

    @Transactional
    public Motorista create(Motorista entity) {
        this.validate(entity);
        entity.getRegistroStatus().setCreateAtNow();
        entity.getRegistroStatus().setActive();
        return repository.save(entity);
    }

    @Transactional
    public Motorista update(@NonNull String id, Motorista entity) {
        Motorista finded = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not founded."));
        finded.setNome(entity.getNome());
        finded.setCpf(entity.getCpf());
        this.validate(finded);
        finded.getRegistroStatus().setUpdateAtNow();
        return repository.save(finded);
    }

    @Transactional
    public Motorista setActive(@NonNull String id, @NonNull Boolean active) {
        Motorista finded = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not founded."));
        finded.getRegistroStatus().setUpdateAtNow();
        if (active)
            finded.getRegistroStatus().setActive();
        else
            finded.getRegistroStatus().setDeactive();
        return repository.save(finded);
    }

    private Motorista validate(Motorista entity) {
        entity.setNome(entity.getNome().toUpperCase());
        return entity;
    }

    @Transactional
    public void deleteById(@NonNull String id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not founded."));
        repository.deleteById(id);
    }
}
