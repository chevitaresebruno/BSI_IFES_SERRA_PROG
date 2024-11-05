package convert


def vig_decimal(vig: String): Int =
    var dec: Int = 0

    for (i <- vig)
    {
        dec *= 20
        dec += i.toInt - '0'
    }

    return dec


def vig_bin(vig: String): String =
    return decimal_bin(vig_decimal(vig))


def vig_oct(vig: String): String =
    return decimal_oct(vig_decimal(vig))


def vig_duodec(vig: String): String =
    return decimal_duodec(vig_decimal(vig))


def vig_hex(vig: String): String =
    return decimal_hex(vig_decimal(vig))


def vig_get_fun(func: Int):  String => Any =
    return func match {
        case 2 => vig_bin
        case 8 => vig_oct
        case 10 => vig_decimal
        case 12 => vig_duodec
        case 16 => vig_hex
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
