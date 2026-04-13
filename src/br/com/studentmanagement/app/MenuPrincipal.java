package br.com.studentmanagement.app;

import br.com.studentmanagement.dominio.Estudante;
import br.com.studentmanagement.repositorio.EstudanteRepositorio;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        EstudanteRepositorio repositorio = new EstudanteRepositorio();
        repositorio.inicializar(50);

        int opcao = -1;

        System.out.println("=== Sistema de Gerenciamento de Estudantes ===");

        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar estudante");
            System.out.println("2. Listar estudantes");
            System.out.println("3. Buscar por matrícula");
            System.out.println("4. Adicionar nota");
            System.out.println("5. Ver detalhes do estudante");
            System.out.println("6. Remover estudante");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1 -> cadastrarEstudante(scanner, repositorio);
                case 2 -> repositorio.listarTodos();
                case 3 -> buscarEstudante(scanner, repositorio);
                case 4 -> adicionarNota(scanner, repositorio);
                case 5 -> verDetalhes(scanner, repositorio);
                case 6 -> removerEstudante(scanner, repositorio);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Sistema encerrado. Até logo!");
        scanner.close();
    }

    static void cadastrarEstudante(Scanner scanner, EstudanteRepositorio repositorio){
        System.out.print("Nome do estudante: ");
        String nome = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        System.out.print("Quantidade máxima de notas: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = new Estudante();
        estudante.inicializar(nome, matricula, capacidade);

        if(repositorio.adicionarEstudante(estudante)) {
            System.out.println("Estudante cadastrado com sucesso!");
        }
    }

    static void buscarEstudante(Scanner scanner, EstudanteRepositorio repositorio) {
        System.out.print("Digite a matrícula: ");
        String matricula = scanner.nextLine();

        Estudante estudante = repositorio.buscarPorMatricula(matricula);
        if (estudante == null){
            System.out.println("Estudante não encontrado.");
            return;
        }
        System.out.printf("Encontrado: %s (Matrícula: %s)%n", estudante.nome, estudante.matricula);
    }

    static void adicionarNota(Scanner scanner, EstudanteRepositorio repositorio){
        System.out.print("Matrícula do estudante: ");
        String matricula = scanner.nextLine();

        Estudante estudante = repositorio.buscarPorMatricula(matricula);
        if (estudante == null){
            System.out.println("Estudante não encontrado.");
            return;
        }

        System.out.print("Valor da nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine();

        estudante.adicionarNota(nota);
        System.out.printf("Nota %.1f adiciona para %s. Média atual: %.1f%n",
                nota, estudante.nome, estudante.calcularMedia());
    }

    static void verDetalhes(Scanner scanner, EstudanteRepositorio repositorio){
        System.out.print("Matricula do estudante: ");
        String matricula = scanner.nextLine();

        Estudante estudante = repositorio.buscarPorMatricula(matricula);
        if (estudante == null){
            System.out.println("Estudante não encontrado.");
            return;
        }

        estudante.exibirInformacoes();
        System.out.println("Situação: " + estudante.verificarSituacao(7.0));
    }

    static void removerEstudante(Scanner scanner, EstudanteRepositorio repositorio){
        System.out.print("Matricula do estudante a remover: ");
        String matricula = scanner.nextLine();

        if (repositorio.removerEstudante(matricula)){
            System.out.println("Estudante removido com sucesso!");
        } else {
            System.out.println("Estudante não encontrado.");
        }
    }

}
