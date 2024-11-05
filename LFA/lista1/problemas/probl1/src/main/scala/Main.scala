package scala

import tools.*
import convert.*


def exec(): Unit =
    val n: String = get_line("Informe um Número: ")  // Número que eu quero converter
    val bo: Int = get_base("Informe uma Base de Origem [2, 8, 10, 12, 16 ou 20]: ")  // Base de Origem
    val bd: Int = get_base("Informe uma Base de Destino [2, 8, 10, 16 ou 20]: ")  // Base de Destino

    println(bo match {
        case 2 => bin_get_fun(bd)(n)
        case 8 => oct_get_fun(bd)(n)
        case 10 => decimal_get_fun(bd)(n.toInt, "")
        case 12 => duodec_get_fun(bd)(n)
        case 16 => hex_get_fun(bd)(n)
    })

@main def Main(): Unit =
    exec()