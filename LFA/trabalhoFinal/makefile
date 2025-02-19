# A customizable makefile to make your life easier

# Bidings
PROJECT_NAME=main
MAIN_FILE=main.c
COMPILER=gcc

LIB_DIR_NAME=lib
LIB_SRC_DIR_NAME=src
LIB_INC_DIR_NAME=inc

# Debug Flag In Gcc: -g
# Compile x64 in Gcc: -m64
COMPILER_FLAGS= 


all: $(PROJECT_NAME)

$(PROJECT_NAME):
ifeq ($(OS),Windows_NT)
	$(COMPILER) $(MAIN_FILE) -o $@ $(COMPILER_FLAGS) -l pthread
else
	$(COMPILER) $(MAIN_FILE) -o $@ $(COMPILER_FLAGS)
endif

.PHONY: all