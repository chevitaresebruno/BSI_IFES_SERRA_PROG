package scala


@scala.annotation.tailrec
def check_perfect_number(n: Int, div: Int = 2, acc: Int = 1): Boolean =
    if(n/2 < div)
        { return acc == n }
    
    if(n % div == 0)
        { return check_perfect_number(n, div+1, acc + div) }
    else
        { return check_perfect_number(n, div+1, acc) }


@main def Main(): Unit =
    print("Informe um Número: ")
    val num: Int = scala.io.StdIn.readInt()

    if(check_perfect_number(num))
        { println(s"O Número $num é perfeito =D") }
    else
        { println(s"O Número $num não é perfeito :(") }
    
