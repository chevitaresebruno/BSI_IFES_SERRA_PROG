package tools


def num_to_string(n: Int): String =
    return n match {
        case 10 => "A"
        case 11 => "B"
        case 12 => "C"
        case 13 => "D"
        case 14 => "E"
        case 15 => "F"
        case 16 => "G"
        case 17 => "H"
        case 18 => "I"
        case 19 => "J"
        case _ => n.toString()
    }

