package convert

import tools.*


@scala.annotation.tailrec
def decimal_to_some(value: Int, base: Int, output: String = ""): String =
    if(value == 0) { return output }

    return decimal_to_some(value/base, base, num_to_string(value%base).concat(output))


def some_to_decimal(value: String, base: Int): Int =
    var dec: Int = 0

    for (i <- value)
    {
        dec *= base
        dec += i.toInt - '0'
    }

    return dec