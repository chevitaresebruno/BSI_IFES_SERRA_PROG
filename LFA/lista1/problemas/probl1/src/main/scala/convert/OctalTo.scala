package convert


def oct_decimal(oct: String): Int =
    var dec: Int = 0

    for (i <- oct)
    {
        dec *= 8
        dec += i.toInt - '0'
    }

    return dec


def oct_bin(oct: String): String =
    return decimal_bin(oct_decimal(oct))


def oct_duodec(bin: String): String =
    return decimal_duodec(oct_decimal(bin))


def oct_hex(bin: String): String =
    return decimal_hex(oct_decimal(bin))


def oct_vig(bin: String): String =
    return decimal_vig(oct_decimal(bin))


def oct_get_fun(func: Int):  String => Any =
    return func match {
        case 2 => oct_bin
        case 10 => oct_decimal
        case 12 => oct_duodec
        case 16 => oct_hex
        case 20 => oct_vig
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
