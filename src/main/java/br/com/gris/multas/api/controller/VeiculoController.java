package br.com.gris.multas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gris.multas.domain.model.Veiculo;
import br.com.gris.multas.domain.service.VeiculoService;


@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {
    @Autowired private VeiculoService service;
    
    @GetMapping()
    public List<Veiculo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@NonNull @PathVariable String id) {
        return new ResponseEntity<Veiculo>(service.findById(id), HttpStatus.OK);
    }
    
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Veiculo> findByPlaca(@NonNull @PathVariable String placa) {
        return new ResponseEntity<Veiculo>(service.findByPlaca(placa), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo input) {
        return new ResponseEntity<Veiculo>(service.create(input), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@NonNull @PathVariable String id, @RequestBody Veiculo input) {
        return new ResponseEntity<Veiculo>(service.update(id, input), HttpStatus.OK);
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Veiculo> active(@NonNull @PathVariable String id) {
        return new ResponseEntity<Veiculo>(service.setActive(id, true), HttpStatus.OK);
    }

    @PatchMapping("/{id}/deactive")
    public ResponseEntity<Veiculo> deactive(@NonNull @PathVariable String id) {
        return new ResponseEntity<Veiculo>(service.setActive(id, false), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
