package br.org.generation.lojagames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.repository.CategoriaRepository;
import br.org.generation.lojagames.repository.CategoriaRepository2;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaRepository2 categoriaRepository2;
	
	@GetMapping
	private ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	private ResponseEntity<List<Categoria>> getByTipo(@PathVariable String tipo){
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping
	private ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	private ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
		return categoriaRepository.findById(categoria.getId())
				.map(resposta ->{
					return ResponseEntity.ok().body(categoriaRepository.save(categoria));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteCategoria(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> {
					categoriaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());	
	}
}
