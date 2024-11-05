package scala

import scala.collection.mutable.Stack


def exec(string: String, stack: Stack[Char]): Boolean =
    for (i <- string)
    {
        if(i == '(') { stack.push(i) }
        else if(i == ')')
        {
            if(stack.size <= 0) { return false }
            stack.pop()
        }
    }

    return stack.size == 0

@main def Main(): Unit =
    print("Forneça a String de Entrada: ")

    val s: String = scala.io.StdIn.readLine()
    val stack: Stack[Char] = Stack[Char]()


    if(exec(s, stack))
        { println("A String está bem formatada :)") }
    else
        { println("A String está mal formatada :(") }

        