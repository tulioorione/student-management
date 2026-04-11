package br.com.student.management.dominio;

public class Estudante {
    String nome;
    String matricula;
    double[] notas;
    int totalNotas;

    void adicionarNota(double nota){
        if(totalNotas >= notas.length) {
            System.out.println("Limite de notas atingido para o estudante "+ nome);
            return;
        }
        notas[totalNotas] = nota;
        totalNotas++;
    }

    double calcularMedia(){
        if(totalNotas == 0){
            return 0;
        }
        double soma = 0;
        for (int i = 0; i < totalNotas; i++) {
            soma += notas[i];
        }
        return soma / totalNotas;
    }

    void exibirInformacoes() {
        System.out.println("=== Informações do Estudante ===");
        System.out.printf("Nome: %s%n", nome);
        System.out.printf("Matrícula: %s%n", matricula);
        System.out.printf("Notas registradas: %d de %d%n", totalNotas, notas.length);

        if(totalNotas > 0){
            System.out.print("Notas: ");
            for (int i = 0; i < totalNotas; i++) {
                if(i > 0){
                    System.out.print(", ");
                }
                System.out.printf("%.1f",notas[i]);
            }
            System.out.println();
            System.out.printf("Média: %.1f%n", calcularMedia());
        }
    }

    String verificarSituacao(double mediaMinima) {
        if(totalNotas == 0){
            return "Sem notas registradas";
        }
        if (calcularMedia() >= mediaMinima){
            return "Aprovado";
        }
        return "Reprovado";
    }

    void inicializar(String nomeEstudante, String matriculaEstudante, int capacidadeNotas){
        nome = nomeEstudante;
        matricula = matriculaEstudante;
        notas = new double[capacidadeNotas];
        totalNotas = 0;
    }


}
