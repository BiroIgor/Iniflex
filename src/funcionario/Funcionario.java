package funcionario;

import pessoa.Pessoa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private String funcao;
    private BigDecimal salario;



    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, String funcao, BigDecimal salario) {
        super(nome, dataNascimento); // Chama o construtor da classe Pessoa
        this.funcao = funcao;
        this.salario = salario;
    }

    // Getters e Setters
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String toString() {
        // Configuração do formato numérico
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("R$ ###,###.00", symbols);


        // Construção da representação formatada
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(getNome()).append("\n");
        sb.append("Data de Nascimento: ").append(getDataNascimentoFormatada()).append("\n");
        sb.append("Função: ").append(funcao).append("\n");
        sb.append("Salário: ").append(decimalFormat.format(salario)).append("\n");

        return sb.toString();
    }
}
