package joao_barreto.novo_ficheiro.service;

import joao_barreto.novo_ficheiro.exception.ResourceNotFoundException;
import joao_barreto.novo_ficheiro.model.Pessoa;
import joao_barreto.novo_ficheiro.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa readPessoa(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa registrada com o Id correspondente encontrada."));
    }

    public List<Pessoa> listPessoas() {
        return pessoaRepository.findAll();
    }

    public void deletePessoa(Long id) {
        Pessoa pessoa = readPessoa(id);

        pessoaRepository.delete(pessoa);
    }
}