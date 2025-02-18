# A customizable makefile to make your life easier

# Bidings
PROJECT_NAME=main
MAIN_FILE=main.c
COMPILER=gcc

LIB_DIR_NAME=lib
LIB_SRC_DIR_NAME=src
LIB_INC_DIR_NAME=inc

# Debug Flag In Gcc: -g
COMPILER_FLAGS= -W \
				-Wall \
				-ansi \
				-pedantic


all: $(PROJECT_NAME)

$(PROJECT_NAME):
	$(COMPILER) $(MAIN_FILE) -o $@ $(COMPILER_FLAGS)

.PHONY: all