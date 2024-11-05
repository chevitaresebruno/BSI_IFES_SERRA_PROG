package convert


def duodec_decimal(duodec: String): Int =
    var dec: Int = 0

    for (i <- duodec)
    {
        dec *= 12
        dec += i.toInt - '0'
    }

    return dec


def duodec_bin(duodec: String): String =
    return decimal_bin(duodec_decimal(duodec))


def duodec_oct(bin: String): String =
    return decimal_oct(duodec_decimal(bin))


def duodec_hex(bin: String): String =
    return decimal_hex(duodec_decimal(bin))


def duodec_vig(bin: String): String =
    return decimal_vig(oct_decimal(bin))


def duodec_get_fun(func: Int):  String => Any =
    return func match {
        case 2 => duodec_bin
        case 8 => duodec_oct
        case 10 => duodec_decimal
        case 16 => duodec_hex
        case 20 => duodec_vig
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
