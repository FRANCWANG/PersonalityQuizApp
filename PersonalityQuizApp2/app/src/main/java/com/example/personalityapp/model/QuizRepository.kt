package com.example.personalityapp.model


object QuizRepository {

    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                text = "Quando você tem tempo livre, prefere:",
                options = listOf("Ler um livro", "Sair com amigos", "Praticar esporte", "Assistir séries"),
                weights = listOf(3, 2, 3, 1)
            ),
            Question(
                text = "Em um projeto você costuma:",
                options = listOf("Planejar tudo", "Improvisar conforme avança", "Delegar tarefas", "Fazer sozinho"),
                weights = listOf(3, 2, 1, 2)
            ),
            Question(
                text = "Qual dessas opções melhor descreve seu sono?",
                options = listOf("Regular e cedo", "Tarde mas consistente", "Variável", "Quase sempre insone"),
                weights = listOf(3, 2, 1, 0)
            ),
            Question(
                text = "Com que frequência você revisa o que aprendeu?",
                options = listOf("Sempre", "Às vezes", "Raramente", "Nunca"),
                weights = listOf(3, 2, 1, 0)
            ),
            Question(
                text = "Se pudesse viajar agora, escolheria:",
                options = listOf("Museus e cultura", "Praia com amigos", "Trilha na montanha", "Turismo gastronômico"),
                weights = listOf(3, 2, 2, 1)
            )
        )
    }
}
