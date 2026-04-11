package br.com.studentmanagement.repositorio;

import br.com.studentmanagement.dominio.Estudante;

public class EstudanteRepositorio {
    Estudante[] estudantes;
    int totalEstudantes;

    public void inicializar(int capacidade) {
        estudantes = new Estudante[capacidade];
        totalEstudantes = 0;

    }
    public boolean adicionarEstudante(Estudante estudante){
        if (totalEstudantes >= estudantes.length){
            System.out.println("Repositório cheio. Não é possivel adicionar mais estudantes.");
            return false;
        }

        if (buscarPorMatricula(estudante.matricula) != null){
            System.out.println("Já existe um estudante com a matrícula "+ estudante.matricula);
            return false;
        }

        estudantes[totalEstudantes] = estudante;
        totalEstudantes++;
        return true;
    }

    public Estudante buscarPorMatricula(String matricula) {
        for (int i = 0; i < totalEstudantes; i++) {
            if(estudantes[i].matricula.equals(matricula)){
                return estudantes[i];
            }
        }
        return null;
    }

    public boolean removerEstudante(String matricula) {
        for (int i = 0; i < totalEstudantes; i++) {
            if (estudantes[i].matricula.equals(matricula)){
                estudantes[i] = estudantes[totalEstudantes -1];
                estudantes[totalEstudantes - 1] = null;
                totalEstudantes--;
                return true;
            }
        }
        return false;
    }

    public void listarTodos() {
        if (totalEstudantes == 0) {
            System.out.println("Nenhum estudante cadastrado.");
            return;
        }

        System.out.println("=== Lista de Estudantes ===");
        for (int i = 0; i < totalEstudantes; i++) {
            System.out.printf("%d. %s (Matricula: %s) - Media: %.1f%n",
                    i+1,
                    estudantes[i].nome,
                    estudantes[i].matricula,
                    estudantes[i].calcularMedia());
        }
    }
}
