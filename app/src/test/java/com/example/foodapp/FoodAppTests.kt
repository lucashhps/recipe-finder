package com.example.foodapp

import org.junit.Test
import org.junit.Assert.assertEquals

class FoodAppTests {

    @Test
    fun searchAlgorithmTest(){
        // instanciando variáveis
        var receitas = ArrayList<Receita>()
        var ingredientes = ArrayList<Ingrediente>()
        var receita : Receita
        var receitaBusca : ReceitaBusca

        // Criando Ingredientes
        val manteiga = Ingrediente("Manteiga", "")
        val paprika = Ingrediente("PAPRIKA", "")
        val sal = Ingrediente("sal", "")
        val alho = Ingrediente("alHo", "")
        val pao = Ingrediente("paO", "")

        // Criando Receita 1

        ingredientes.add(manteiga)
        receita = Receita("receita 1", "", ingredientes, 0)
        receitas.add(receita)

        // Criando Receita 2

        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(sal)
        receita = Receita("receita 2", "", ingredientes, 4800)
        receitas.add(receita)

        // Criando Receita 3
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(alho)
        ingredientes.add(pao)
        receita = Receita("receita 3", "", ingredientes, 0)
        receitas.add(receita)


        // Criando Busca
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(paprika)
        ingredientes.add(sal)
        receitaBusca = ReceitaBusca("receita", "", ingredientes, 0)
        
        var resultadoBusca = receitaBusca.searchRecipe(receitas)
        var tamanhoEncontrado = resultadoBusca.size
        var tamanhoEsperado = 3
        assertEquals(tamanhoEsperado, tamanhoEncontrado)
        println("Teste 1 executado")

        // Criando Busca 2
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(paprika)
        ingredientes.add(sal)
        receitaBusca = ReceitaBusca("receita 1", "", ingredientes, 0)

        resultadoBusca = receitaBusca.searchRecipe(receitas)
        tamanhoEncontrado = resultadoBusca.size
        tamanhoEsperado = 1
        assertEquals(tamanhoEsperado, tamanhoEncontrado)

        println("Teste 2 executado")

        // Criando Busca 3
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(paprika)
        ingredientes.add(sal)
        receitaBusca = ReceitaBusca("receita", "", ingredientes, 0)

        resultadoBusca = receitaBusca.searchRecipe(receitas, ingredienteOn = true)
        tamanhoEncontrado = resultadoBusca.size
        tamanhoEsperado = 2
        assertEquals(tamanhoEsperado, tamanhoEncontrado)
        println("Teste 3 executado")

        // Criando Busca 4
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(paprika)
        ingredientes.add(sal)
        receitaBusca = ReceitaBusca("receita", "", ingredientes, 3600)

        resultadoBusca = receitaBusca.searchRecipe(receitas, ingredienteOn = true, tempoOn = true)
        tamanhoEncontrado = resultadoBusca.size
        tamanhoEsperado = 1
        assertEquals(tamanhoEsperado, tamanhoEncontrado)
        println("Teste 4 executado")

        // Criando Busca 5
        ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(manteiga)
        ingredientes.add(paprika)
        ingredientes.add(sal)
        receitaBusca = ReceitaBusca("receita", "", ingredientes, 3600)

        resultadoBusca = receitaBusca.searchRecipe(receitas, ingredienteOn = false, tempoOn = true)
        tamanhoEncontrado = resultadoBusca.size
        tamanhoEsperado = 2
        assertEquals(tamanhoEsperado, tamanhoEncontrado)
        println("Teste 5 executado")



    }
}