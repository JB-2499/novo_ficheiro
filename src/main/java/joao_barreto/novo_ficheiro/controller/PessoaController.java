package joao_barreto.novo_ficheiro.controller;

import jakarta.validation.Valid;
import joao_barreto.novo_ficheiro.model.Pessoa;
import joao_barreto.novo_ficheiro.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createPessoa(pessoa);

        return ResponseEntity.created(URI.create("/Pessoas" + createdPessoa.getId())).body(createdPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> readPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.readPessoa(id));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listPessoas() {
        return ResponseEntity.ok(pessoaService.listPessoas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);

        return ResponseEntity.noContent().build();
    }
}