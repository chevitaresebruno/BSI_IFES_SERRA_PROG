package tools


def get_line(message: String): String =
    print(message)
    
    return scala.io.StdIn.readLine()


@scala.annotation.tailrec
def get_num(message: String): Int =
    val input = get_line(message)

    try { return input.toInt }
    catch
    {
        case _: NumberFormatException => {
            println("Atenção, escreva apenas números")
            
            return get_num(message)
        }
    }


@scala.annotation.tailrec
def get_base(message: String): Int =
    val input: Int = get_num(message)

    input match {
        case 2 => return input
        case 8 => return input
        case 10 => return input
        case 12 => return input
        case 16 => return input
        case 20 => return input
        case _ => println("Atenção, o código fornecido é inválido")
    }

    return get_base(message)

