package convert


def hex_decimal(hex: String): Int =
    var dec: Int = 0

    for (i <- hex)
    {
        dec *= 16
        dec += i.toInt - '0'
    }

    return dec


def hex_bin(hex: String): String =
    return decimal_bin(duodec_decimal(hex))


def hex_oct(hex: String): String =
    return decimal_oct(duodec_decimal(hex))


def hex_duodec(bin: String): String =
    return decimal_duodec(duodec_decimal(bin))


def hex_vig(bin: String): String =
    return decimal_vig(hex_decimal(bin))


def hex_get_fun(func: Int):  String => Any =
    return func match {
        case 2 => hex_bin
        case 8 => hex_oct
        case 10 => hex_decimal
        case 12 => hex_duodec
        case 20 => hex_vig
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
