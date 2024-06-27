import funcionario.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {

        //3.1 Inserir todos os funcionários
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), "Operador", new BigDecimal("2009.44")));
        funcionarios.add(new Funcionario("João", LocalDate.parse("1990-05-12"), "Operador", new BigDecimal("2284.38")));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("1961-05-02"), "Coordenador", new BigDecimal("9836.14")));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), "Diretor", new BigDecimal("19119.88")));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("1995-01-05"), "Recepcionista", new BigDecimal("2234.68")));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), "Operador", new BigDecimal("1582.72")));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), "Contador", new BigDecimal("4071.84")));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), "Gerente", new BigDecimal("3017.45")));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), "Eletricista", new BigDecimal("1606.85")));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), "Gerente", new BigDecimal("2799.93")));


        //3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));


        //3.4 - Atualizar salários com 10% de aumento
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            BigDecimal novoSalario = funcionario.getSalario().add(aumento);
            funcionario.setSalario(novoSalario);
        }


        //3.5 e 3.6 – Agrupar funcionários por função utilizando MAP

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("\u001B[1m" + entry.getKey() + "\u001B[0m \n");
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println(funcionario.toString());
                System.out.println();
            }

        }

        System.out.println("-----------------------------------------");

        //3.8 Imprimir os funcionários que fazem aniversário em outubro e dezembro
        List<Funcionario> aniversariantesOutubroDezembro = funcionarios.stream()
                .filter(funcionario -> {
                    int mesAniversario = funcionario.getDataNascimento().getMonthValue();
                    return mesAniversario == 10 || mesAniversario == 12;
                })
                .collect(Collectors.toList());

        System.out.println("\u001B[1mFuncionários que fazem aniversário em outubro e dezembro:\u001B[0m");
        aniversariantesOutubroDezembro.forEach(funcionario -> {
            System.out.println(funcionario.toString());
            System.out.println();
        });

        System.out.println("-----------------------------------------");

        //3.9 Encontrar o funcionário com maior idade
        Optional<Funcionario> funcionarioMaiorIdade = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));

        if (funcionarioMaiorIdade.isPresent()) {
            Funcionario maiorIdade = funcionarioMaiorIdade.get();
            LocalDate hoje = LocalDate.now();
            int idade = Period.between(maiorIdade.getDataNascimento(), hoje).getYears();

            System.out.println("\u001B[1mFuncionário com a maior idade:\u001B[0m");
            System.out.println("Nome: " + maiorIdade.getNome());
            System.out.println("Idade: " + idade + " Anos \n\n");
        } else {
            System.out.println("Nenhum funcionário encontrado. \n\n");
        }

        System.out.println("-----------------------------------------");

        //3.10 Ordenar a lista de funcionários por nome em ordem alfabética
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        System.out.println("\u001B[1mLista de funcionários em ordem alfabética:\u001B[0m");
        funcionariosOrdenados.forEach(funcionario -> {
            System.out.println(funcionario.toString());
            System.out.println();
        });

        System.out.println("-----------------------------------------");

        //3.11 Calcular o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\u001B[1mTotal dos salários dos funcionários:\u001B[0m ");
        System.out.println("R$ " + totalSalarios + "\n\n");

        System.out.println("-----------------------------------------");

//3.12 Imprimir quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\u001B[1mQuantidade de salários mínimos de cada funcionário:\u001B[0m");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioFuncionario = funcionario.getSalario();
            BigDecimal salariosMinimos = salarioFuncionario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });

    }
    }

