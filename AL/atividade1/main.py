from MatrizEquacoes import MatrizEquacoes
from Fracao import Fracao


def main() -> int:
    f = Fracao.criarDeDecimal(-1.5).irredutivel()
    print(f)

    quit()
    m = MatrizEquacoes(3, 3)

    m.print()

    return 0


if __name__ == "__main__":
    main()

