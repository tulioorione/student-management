package br.com.studentmanagement.dominio;

public class Estudante {
    private String nome;
    private String matricula;
    private double[] notas;
    private int totalNotas;
    private static int totalEstudantesCriados = 0;
    private Curso curso;

    public Estudante(String nome, String matricula, int capacidadeNotas){
        this.setNome(nome);
        this.matricula = matricula;
        this.notas = new double[capacidadeNotas];
        this.totalNotas = 0;
        totalEstudantesCriados++;
    }

    public Estudante(String nome, String matricula){
        this(nome, matricula, 10);
    }

    public String getNome(){ return this.nome; }
    public void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        this.nome = nome;
    }

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
        if(curso != null){
            System.out.printf("Curso: %s (%s)%n", curso.getNome(), curso.getCodigo());
        }
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
    // Sobrecarregando o método.
    public String verificarSituacao(){
        if(curso == null){
            return verificarSituacao(7.0);
        }
        return verificarSituacao(curso.getMediaMinima());
    }

    public String getMatricula(){ return this.matricula; }

    public static int getTotalEstudantesCriados(){
        return totalEstudantesCriados;
    }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
