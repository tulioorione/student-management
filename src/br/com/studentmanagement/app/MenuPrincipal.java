package br.com.studentmanagement.app;

import br.com.studentmanagement.dominio.Estudante;
import br.com.studentmanagement.repositorio.EstudanteRepositorio;
import br.com.studentmanagement.dominio.Curso;

import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        EstudanteRepositorio repositorio = new EstudanteRepositorio(50);
        Curso[] cursos = new Curso[20];
        int totalCursos = 0;

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
            System.out.println("7. Cadastrar curso");
            System.out.println("8. Matricular estudante em curso");
            System.out.println("9. Listar estudantes de um curso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEstudante(scanner, repositorio);
                case 2 -> repositorio.listarTodos();
                case 3 -> buscarEstudante(scanner, repositorio);
                case 4 -> adicionarNota(scanner, repositorio);
                case 5 -> verDetalhes(scanner, repositorio);
                case 6 -> removerEstudante(scanner, repositorio);
                case 7 -> totalCursos = cadastrarCurso(scanner, cursos, totalCursos);
                case 8 -> matricularEmCurso(scanner, repositorio, cursos, totalCursos);
                case 9 -> listarEstudantesCurso(scanner, cursos, totalCursos);
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

        Estudante estudante = new Estudante(nome, matricula, capacidade);

        if(repositorio.adicionarEstudante(estudante)) {
            System.out.println("Estudante cadastrado com sucesso!");
            System.out.printf("Total de estudantes criados no sistema: %d%n",
                    Estudante.getTotalEstudantesCriados());
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
        System.out.printf("Encontrado: %s (Matrícula: %s)%n", estudante.getNome(), estudante.getMatricula());
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
        System.out.printf("Nota %.1f adicionada para %s. Média atual: %.1f%n",
                nota, estudante.getNome(), estudante.calcularMedia());
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
        System.out.println("Situação: " + estudante.verificarSituacao());
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

    static int cadastrarCurso(Scanner scanner, Curso[] cursos, int totalCursos) {
        if(totalCursos >= cursos.length) {
            System.out.println("Limite de cursos atingido.");
            return totalCursos;
        }

        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();

        System.out.print("Código do curso: ");
        String codigo = scanner.nextLine();

        System.out.print("Média mínima para aprovação: ");
        double mediaMinima = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Capacidade máxima de estudantes: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        cursos[totalCursos] = new Curso(nome, codigo, mediaMinima, capacidade);
        totalCursos++;

        System.out.println("Curso cadastrado com sucesso!");
        System.out.printf("Total de cursos criados: %d%n", Curso.getTotalCursosCriados());

        return totalCursos;
    }

    static void matricularEmCurso(Scanner scanner, EstudanteRepositorio repositorio, Curso[] cursos, int totalCursos ){
        System.out.print("Matrícula do estudante: ");
        String matricula = scanner.nextLine();

        Estudante estudante = repositorio.buscarPorMatricula(matricula);
        if(estudante == null) {
            System.out.println("Estudante não encontrado.");
            return;
        }

        if(totalCursos == 0){
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < totalCursos; i++) {
            System.out.printf("%d. %s (%s)%n",
                    i+1, cursos[i].getNome(), cursos[i].getCodigo());
        }

        System.out.print("Escolha o número do curso: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha < 1 || escolha > totalCursos){
            System.out.println("Opção inválida.");
            return;
        }

        Curso curso = cursos[escolha - 1];

        if (curso.adicionarEstudante(estudante)) {
            estudante.setCurso(curso);
            System.out.printf("%s matriculado em %s com sucesso!%n",
                    estudante.getNome(), curso.getNome());
        }
    }

    static void listarEstudantesCurso(Scanner scanner, Curso[] cursos, int totalCursos) {
        if(totalCursos == 0){
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < totalCursos; i++) {
            System.out.printf("%d. %s (%s)%n",
                    i+1, cursos[i].getNome(), cursos[i].getCodigo());
        }

        System.out.print("Escolha o número do curso: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha < 1 || escolha > totalCursos){
            System.out.println("Opção inválida.");
            return;
        }

        cursos[escolha - 1].listarEstudantes();
    }

}
