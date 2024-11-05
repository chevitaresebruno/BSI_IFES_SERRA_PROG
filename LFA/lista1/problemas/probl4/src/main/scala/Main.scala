package scala

import scala.collection.mutable.Set

import handlers.*


@main def Main(): Unit =
    println("Calculadora de Conjuntos")
    println("Comece da seguinte forma: 'Letra = n1, n2, n3, ..., nx'")

    val pattern = """([^=]+)\s*=\s*(.+)""".r
    val input = scala.io.StdIn.readLine()

    val s1 = input match {
        case pattern(word, numbers) => SetHandler(word.trim, numbers.split(",").foreach { }))
    }