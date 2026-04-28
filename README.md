

<img width="1280" height="1544" alt="student_management_system_flowchart" src="https://github.com/user-attachments/assets/a2a02306-9b68-4c18-9be8-e96d1f450e04" />
# Student Management System

Sistema de gerenciamento de estudantes desenvolvido em Java puro, sem frameworks.
Projeto construído como estudo prático de conceitos fundamentais de back-end,
com foco em organização profissional de código e separação de responsabilidades.

## Funcionalidades

- Cadastro de estudantes com matrícula única (validação de duplicidade)
- Registro de notas com limite configurável por estudante
- Cálculo automático de média
- Verificação de situação acadêmica (aprovado/reprovado)
- Busca por matrícula
- Remoção de estudantes com gerenciamento de memória
- Menu interativo via terminal

## Estrutura do Projeto
src/
└── br.com.studentmanagement
├── dominio/          → Entidades de negócio (Estudante)
├── repositorio/      → Camada de armazenamento em memória
└── app/              → Ponto de entrada e interação com usuário

### Decisões de Arquitetura

- **Separação em camadas**: domínio, repositório e aplicação isolados em pacotes distintos,
  simulando a estrutura de projetos profissionais com Spring Boot
- **Armazenamento em array**: implementação manual de repositório em memória,
  preparado para futura migração para Collections e banco de dados
- **Validações de negócio**: verificação de matrícula duplicada e controle de capacidade
  no repositório, não na camada de apresentação

## Conceitos Aplicados

- Orientação a objetos: classes, métodos, atributos de instância
- Arrays com controle manual de posição e capacidade
- Passagem por valor (primitivos) e por referência (objetos)
- Guard clauses e early return
- Formatação com printf
- Entrada de dados com Scanner (tratamento do buffer com nextLine)
- Remoção otimizada em array com swap pelo último elemento — O(1)
- Encapsulamento: atributos private com getters/setters e validação
- Construtores com sobrecarga e constructor chaining (this())
- Modificador static para contador de instâncias

## Roadmap

Este projeto será evoluído conforme novos conceitos forem aprendidos:

- [x] Encapsulamento com getters/setters e construtores
- [ ] Refatoração de arrays para ArrayList
- [ ] Tratamento de exceções (try/catch)
- [ ] Persistência com banco de dados
- [ ] API REST com Spring Boot

## Status do Projeto

🟢 **Em desenvolvimento ativo** — projeto-âncora dos meus estudos em Java backend. Atualmente focado na refatoração para Collections e tratamento de exceções. Próxima grande etapa: persistência com JPA/Hibernate e migração para Spring Boot.

## Autor

**Túlio Orione** — Desenvolvedor Backend Júnior em formação  
📧 tulioorione@gmail.com  
💼 [LinkedIn](https://linkedin.com/in/tulio-orione-7b89a2329)

## Como Executar

1. Clone o repositório
```bash
git clone https://github.com/tulioorione/student-management.git
```

2. Abra o projeto no IntelliJ IDEA
3. Marque a pasta `src` como Sources Root
4. Execute a classe `MenuPrincipal`
