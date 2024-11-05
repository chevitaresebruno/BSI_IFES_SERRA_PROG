package convert

import tools.*


@scala.annotation.tailrec
def decimal_bin(num: Int, out: String = ""): String =
    if(num == 0) { return out }

    return decimal_bin(num/2, (num%2).toString().concat(out))


@scala.annotation.tailrec
def decimal_oct(num: Int, out: String = ""): String =
    if(num == 0) { return out }

    return decimal_oct(num/8, (num%8).toString().concat(out))


@scala.annotation.tailrec
def decimal_duodec(num: Int, out: String = ""): String =
    if(num == 0) { return out }

    return decimal_duodec(num/12, num_to_string(num%12).concat(out))


@scala.annotation.tailrec
def decimal_hex(num: Int, out: String = ""): String =
    if(num == 0) { return out }

    return decimal_hex(num/16, num_to_string(num%16).concat(out))


@scala.annotation.tailrec
def decimal_vig(num: Int, out: String = ""): String =
    if(num == 0) { return out }

    return decimal_vig(num/20, num_to_string(num%20).concat(out))


def decimal_get_fun(func: Int):  (Int, String) => String =
    return func match {
        case 2 => decimal_bin
        case 8 => decimal_oct
        case 12 => decimal_duodec
        case 16 => decimal_hex
        case 20 => decimal_vig
        case _ => throw new IllegalArgumentException("Código de conversão inválido")
    }
