# 🎯 Case — Plataforma de Cursos EAD Gamificada

Uma plataforma vende cursos online e EAD no modelo de assinaturas.

O aluno paga um valor mensal e tem acesso a um conjunto de cursos no plano básico.

A cada curso finalizado com média ≥ 7,0, o aluno ganha direito a realizar +3 cursos.

O aluno mais participativo no fórum (mais tópicos e ajuda a colegas) recebe +1 curso extra no final do mês.

Ao conquistar 12 cursos concluídos, o aluno sobe para o plano Premium, e passa a receber:

- Vouchers para participar de projetos reais.
- 3 moedas, que podem ser:
  - Convertidas em novos cursos.
  - Acumuladas.
  - Trocadas por criptomoeda.

---

# 👤 User Stories

### Integrante 1 — Ana Lúcia
**EU COMO** gerente da plataforma  
**QUERO** um dashboard que mostre o volume e a distribuição das escolhas de recompensas dos alunos  
**PARA** entender o motivador do uso e orientar decisões de produto/marketing  

### Integrante 2 — Tiago
**EU COMO** coordenador da escola  
**QUERO** recompensar os alunos com média acima de 7  
**PARA** incentivar os demais alunos a atingirem essa meta  

---

# ✅ BDD (Behavior Driven Development)

## 📊 User Story — Dashboard de recompensas (User Story escolhida)

Feature: Dashboard de Recompensas
  Como gerente da plataforma
  Quero visualizar volume e distribuição das escolhas de recompensas
  Para entender motivadores de uso e orientar decisões de produto/marketing

Scenario: Exibir contagem ao filtrar por criptomoeda
  Dado que o gerente acessa a plataforma
    E entra no dashboard de recompensas
  Quando filtra pelo tipo de recompensa "Criptomoeda"
  Então visualiza a quantidade total de alunos que escolheram "Criptomoeda"

Scenario: Exibir ranking de cursos no detalhamento
  Dado que o dashboard está com filtros aplicados
  Quando abre o detalhamento por curso
  Então exibe o ranking de cursos que mais geraram cada tipo de escolha


## 📊 User Story — Tiago (Recompensar alunos com média ≥ 7)

Feature: Recompensa por Desempenho Acadêmico
  Como coordenador da escola
  Quero recompensar alunos com média acima de 7
  Para incentivar os demais alunos a atingirem essa meta
