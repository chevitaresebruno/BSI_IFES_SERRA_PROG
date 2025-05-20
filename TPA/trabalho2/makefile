all: lib main

main:
	gcc main.c ./app/lib/bin/BinaryTreeAvl.o -o main.exe

lib: btl btal

btl:
	gcc -c ./app/lib/src/chs/BinaryTree.c -o ./app/lib/bin/BinaryTree.o

btal:
	gcc -c ./app/lib/src/chs/BinaryTreeAvl.c -o ./app/lib/bin/BinaryTreeAvl.o
