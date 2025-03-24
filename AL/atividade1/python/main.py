from MatrizEquacoes import MatrizEquacoes
from time import time

def main() -> int:
    t1 = time()
    m = MatrizEquacoes(10, 10)
    m.irredutibilizar()
    m.escalonar()
    t2 = time()

    print(t2-t1)
    return 0


if __name__ == "__main__":
    main()

