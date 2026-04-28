package br.com.studentmanagement.dominio;

public class Curso {
    private String nome;
    private String codigo;
    private double mediaMinima;
    private Estudante[] estudantes;
    private int totalEstudantes;
    private static int totalCursosCriados = 0;

    public Curso(String nome, String codigo, double mediaMinima, int capacidadeEstudantes){
        this.nome = nome;
        this.codigo = codigo;
        this.mediaMinima = mediaMinima;
        this.estudantes = new Estudante[capacidadeEstudantes];
        totalCursosCriados++;
    }

    public Curso(String nome, String codigo){
        this(nome, codigo, 7.0, 50);
    }

    public boolean adicionarEstudante(Estudante estudante){
        if (totalEstudantes >= estudantes.length){
            System.out.println("Curso "+ nome + " está cheio.");
            return false;
        }

        for (int i = 0; i < totalEstudantes; i++) {
            if (estudantes[i].getMatricula().equals(estudante.getMatricula())){
                System.out.println("Estudante já está neste curso.");
                return false;
            }
        }

        estudantes[totalEstudantes] = estudante;
        totalEstudantes++;
        return true;
    }

    public void listarEstudantes(){
        if (totalEstudantes == 0){
            System.out.println("Nenhum estudante matriculado em " + nome);
            return;
        }

        System.out.println("=== Estudantes do curso " + nome + " ===");
        for (int i = 0; i < totalEstudantes; i++) {
            System.out.printf("%d. %s (Matrícula: %s) - Média: %.1f - %s%n",
                    i + 1,
                    estudantes[i].getNome(),
                    estudantes[i].getMatricula(),
                    estudantes[i].calcularMedia(),
                    estudantes[i].verificarSituacao(mediaMinima));
        }
    }


    // Getters
    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public double getMediaMinima() { return mediaMinima; }
    public static int getTotalCursosCriados() { return totalCursosCriados; }


}
