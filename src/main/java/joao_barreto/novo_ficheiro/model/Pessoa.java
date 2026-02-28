package joao_barreto.novo_ficheiro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "Informe a área de atuação do indivíduo")
    private String trabalho;

    @Min(value = 16, message = "Indivíduos menores de 16 anos não podem estagiar.")
    @Max(value = 120, message = "Idade acima do limite.")
    private int idade;

    private Boolean empregado;
}