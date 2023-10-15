enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String) {
    val progresso = mutableMapOf<String, Int>() // Acompanhamento do progresso por conteúdo
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
            println("$usuario foi matriculado na formação $nome.")
        } else {
            println("$usuario já está matriculado na formação $nome.")
        }
    }

    fun desmatricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            inscritos.remove(usuario)
            println("$usuario foi desmatriculado da formação $nome.")
        } else {
            println("$usuario não está matriculado na formação $nome.")
        }
    }
}

fun main() {
    val usuario1 = Usuario("João")
    val usuario2 = Usuario("Maria")

    val conteudo1 = ConteudoEducacional("Introdução à Programação", 120)
    val conteudo2 = ConteudoEducacional("Estruturas de Dados", 90)

    val formacao = Formacao("Ciência da Computação", listOf(conteudo1, conteudo2))

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)
    formacao.matricular(usuario1) // Tentando matricular o mesmo usuário novamente

    println("Inscritos na formação ${formacao.nome}:")
    formacao.inscritos.forEach { println(it.nome) }

    // Simulando progresso dos usuários nos conteúdos
    usuario1.progresso[conteudo1.nome] = 50 // 50% de progresso
    usuario2.progresso[conteudo2.nome] = 75 // 75% de progresso

    // Exibindo o progresso dos usuários
    println("Progresso dos inscritos na formação ${formacao.nome}:")
    formacao.inscritos.forEach { usuario ->
        println("${usuario.nome}:")
        usuario.progresso.forEach { (conteudo, progresso) ->
            println("$conteudo - Progresso: $progresso%")
        }
    }
}
