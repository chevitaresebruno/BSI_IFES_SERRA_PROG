# Name of the project
PROJ_NAME=main

# Clear Command
ifeq ($(OS),Windows_NT)
	REMOVE=del /Q .\lib\bin\*.o *.exe $(PROJ_NAME)
else
	REMOVE=rm -rf del /Q ./lib/bin/*.o *.exe $(PROJ_NAME) *~
endif


# files
BIN=lib/bin
SRC=lib/src
EXT=lib/extern

# Object files
OBJ=$(C_SOURCE:.c=.o)

# Compiler
CC=gcc

# Flags for compiler
CC_FLAGS=-c         \
         -g         \
         -I.        \
         -W         \
         -Wall      \
         -ansi      \
         -pedantic

#
# Compilation and linking
#
all: $(PROJ_NAME)
	$(CC) -o $(PROJ_NAME).exe $(BIN)/pbPlots.o $(BIN)/supportLib.o $(BIN)/main.o

$(PROJ_NAME): externLib
	$(CC) -o $(BIN)/main.o main.c -c

externLib: pbPlots

pbPlots:
	$(CC) -o $(BIN)/pbPlots.o -c $(EXT)/pbPlots/pbPlots.c
	$(CC) -o $(BIN)/supportLib.o -c $(EXT)/pbPlots/supportLib.c

clean:
	$(REMOVE)