package br.com.studentmanagement.dominio;

public class Estudante {
    private String nome;
    private String matricula;
    private double[] notas;
    private int totalNotas;

    public String getNome(){ return this.nome; }
    public void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        this.nome = nome;
    }

    public String getMatricula(){ return this.matricula; }

    public void adicionarNota(double nota){
        if(totalNotas >= notas.length) {
            System.out.println("Limite de notas atingido para o estudante "+ nome);
            return;
        }
        notas[totalNotas] = nota;
        totalNotas++;
    }

    public double calcularMedia(){
        if(totalNotas == 0){
            return 0;
        }
        double soma = 0;
        for (int i = 0; i < totalNotas; i++) {
            soma += notas[i];
        }
        return soma / totalNotas;
    }

    public void exibirInformacoes() {
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

    public String verificarSituacao(double mediaMinima) {
        if(totalNotas == 0){
            return "Sem notas registradas";
        }
        if (calcularMedia() >= mediaMinima){
            return "Aprovado";
        }
        return "Reprovado";
    }

    public void inicializar(String nome, String matricula, int capacidadeNotas){
        this.setNome(nome);
        this.matricula = matricula;
        notas = new double[capacidadeNotas];
        totalNotas = 0;
    }
}
