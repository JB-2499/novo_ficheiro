package joao_barreto.novo_ficheiro.controller;

import jakarta.validation.Valid;
import joao_barreto.novo_ficheiro.model.Pessoa;
import joao_barreto.novo_ficheiro.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.updatePessoa(id, pessoa));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pessoa> patchPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.patchPessoa(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        Map<String, String>  response = new HashMap<>();
        response.put("message:", "pessoa removida com sucesso.");
        response.put("status:", "complete");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAllPessoas(@RequestParam(name = "confirmar", defaultValue = "false") boolean confirmar) {
        Map<String, String> response = new HashMap<>();

        if (!confirmar) {
            response.put("erro:", "esta operação requer uma confirmação. adicione '?confirmar=true' à URL.");
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
        }

        pessoaService.deleteAllPessoas();
        response.put("message:", "Registro deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}