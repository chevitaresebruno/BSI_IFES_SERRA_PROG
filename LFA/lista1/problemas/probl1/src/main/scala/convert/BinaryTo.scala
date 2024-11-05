package convert


def bin_decimal(bin: String): Int =
    var dec: Int = 0

    for (i <- bin)
    {
        dec *= 2
        dec += i.toInt - '0'
    }

    return dec


def bin_octal(bin: String): String =
    return decimal_oct(bin_decimal(bin))


def bin_duodec(bin: String): String =
    return decimal_duodec(bin_decimal(bin))


def bin_hex(bin: String): String =
    return decimal_hex(bin_decimal(bin))


def bin_vig(bin: String): String =
    return decimal_vig(bin_decimal(bin))


def bin_get_fun(func: Int):  String => Any =
    return func match {
        case 8 => bin_octal
        case 10 => bin_decimal
        case 12 => bin_duodec
        case 16 => bin_hex
        case 20 => bin_vig
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
