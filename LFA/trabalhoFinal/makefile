# A customizable makefile to make your life easier

# Bidings
PROJECT_NAME=ProjectName
MAIN_FILE=main.c
COMPILER=gcc

PROJECT_SCRIPTS_DIR_NAME=scripts
SRC_DIR_NAME=src
BIN_DIR_NAME=bin
INC_DIR_NAME=inc

LIB_DIR_NAME=lib
LIB_SRC_DIR_NAME=src
LIB_INC_DIR_NAME=inc

# Debug Flag In Gcc: -g
COMPILER_FLAGS= -W \
				-Wall \
				-ansi \
				-pedantic

# PACKAGES = SomePackage1 SomePackage2, 
PACKAGES = matriz ordenacao
LIB_PACKAGES = 




# DANGER ZONE
SRC_PATH=./$(PROJECT_SCRIPTS_DIR_NAME)/$(SRC_DIR_NAME)
INC_PATH=./$(PROJECT_SCRIPTS_DIR_NAME)/$(INC_DIR_NAME)
BIN_PATH=./$(BIN_DIR_NAME)

LIB_PATH=./$(PROJECT_SCRIPTS_DIR_NAME)/$(LIB_DIR_NAME)
LIB_SRC_PATH=$(LIB_PATH)/$(LIB_SRC_DIR_NAME)
LIB_INC_PATH=$(LIB_PATH)/$(LIB_INC_DIR_NAME)
LIB_BIN_PATH=$(BIN_PATH)/$(LIB_DIR_NAME)

PACKAGES_C = $(foreach package, $(PACKAGES),$(wildcard $(SRC_PATH)/$(package)/*.c))
PACKAGES_O = $(subst $(SRC_PATH),$(BIN_PATH),$(subst .c,.o,$(PACKAGES_C)))
PACKAGES_SRC_DIR = $(foreach package, $(PACKAGES),$(SRC_PATH)/$(package))
PACKAGES_INC_DIR = $(foreach package, $(PACKAGES),$(INC_PATH)/$(package))
PACKAGES_BIN_DIR = $(foreach package, $(PACKAGES),$(BIN_PATH)/$(package))

ifeq ($(strip $(LIB_PACKAGES)),)
else
LIB_PACKAGES_C = $(foreach package, $(LIB_PACKAGES),$(wildcard $(LIB_SRC_PATH)/$(package)/*.c))
LIB_PACKAGES_O = $(subst $(LIB_SRC_PATH),$(LIB_BIN_PATH),$(subst .c,.o,$(LIB_PACKAGES_C)))
LIB_PACKAGES_SRC_DIR = $(foreach package, $(LIB_PACKAGES),$(LIB_SRC_PATH)/$(package))
LIB_PACKAGES_INC_DIR = $(foreach package, $(LIB_PACKAGES),$(LIB_INC_PATH)/$(package))
LIB_PACKAGES_BIN_DIR = $(foreach package, $(LIB_PACKAGES),$(LIB_BIN_PATH)/$(package))
endif

SHARED_H = $(INC_PATH)/shared.h

ifeq ($(OS), Windows_NT)
BREAK_LINE = @echo. >>
AUX = 
AUX2 = ^
$(eval SRC_PATH := $(subst /,\,$(SRC_PATH)))
$(eval INC_PATH := $(subst /,\,$(INC_PATH)))
$(eval BIN_PATH := $(subst /,\,$(BIN_PATH)))
$(eval LIB_PATH := $(subst /,\,$(LIB_PATH)))
$(eval PACKAGES_C := $(subst /,\,$(PACKAGES_C)))
$(eval PACKAGES_O := $(subst /,\,$(PACKAGES_O)))
$(eval PACKAGES_SRC_DIR := $(subst /,\,$(PACKAGES_SRC_DIR)))
$(eval PACKAGES_INC_DIR := $(subst /,\,$(PACKAGES_INC_DIR)))
$(eval PACKAGES_BIN_DIR := $(subst /,\,$(PACKAGES_BIN_DIR)))
$(eval LIB_BIN_PATH := $(subst /,\,$(LIB_BIN_PATH)))
ifeq ($(strip $(LIB_PACKAGES)),)
else
$(eval LIB_PACKAGES_SRC_DIR := $(subst /,\,$(LIB_PACKAGES_SRC_DIR)))
$(eval LIB_PACKAGES_INC_DIR := $(subst /,\,$(LIB_PACKAGES_INC_DIR)))
$(eval LIB_PACKAGES_BIN_DIR := $(subst /,\,$(LIB_PACKAGES_BIN_DIR)))
endif
$(eval SHARED_H := $(subst /,\,$(SHARED_H)))
else
BREAK_LINE = @echo "" >>
AUX ="
AUX2 =
endif

ifeq ($(strip $(LIB_PACKAGES)),)
PROJECT_STRUCT = $(PROJECT_SCRIPTS_DIR_NAME) $(BIN_PATH) $(SRC_PATH) $(INC_PATH) $(LIB_PATH) $(PACKAGES_SRC_DIR) $(PACKAGES_INC_DIR) $(PACKAGES_BIN_DIR) $(LIB_BIN_PATH)
else
PROJECT_STRUCT = $(PROJECT_SCRIPTS_DIR_NAME) $(BIN_PATH) $(SRC_PATH) $(INC_PATH) $(LIB_PATH) $(PACKAGES_SRC_DIR) $(PACKAGES_INC_DIR) $(PACKAGES_BIN_DIR) $(LIB_PACKAGES_SRC_DIR) $(LIB_PACKAGES_INC_DIR) $(LIB_BIN_PATH) $(LIB_PACKAGES_BIN_DIR)
endif

# Commands
all: $(PROJECT_NAME)

$(PROJECT_NAME): $(PACKAGES_O)
ifeq ($(OS), Windows_NT)
	$(COMPILER) $(MAIN_FILE) $(PACKAGES_O) -o $(PROJECT_NAME).exe $(COMPILER_FLAGS)
else
	$(COMPILER) $(MAIN_FILE) $(PACKAGES_O) -o $(PROJECT_NAME) $(COMPILER_FLAGS)
endif

$(PACKAGES_O): $(PACKAGES_C)
	$(COMPILER) -o $@ -c $< $(COMPILER_FLAGS)

build_project: $(PROJECT_STRUCT)

build_files: p_SharedFile p_MainFile

p_SharedFile:
	@echo $(AUX)#ifndef SHARED_H$(AUX) > $(SHARED_H)
	@echo $(AUX)#define SHARED_H 1$(AUX) >> $(SHARED_H)
	$(BREAK_LINE) $(SHARED_H)
	@echo $(AUX)#include $(AUX2)<stdlib.h$(AUX2)>$(AUX) >> $(SHARED_H)
	$(BREAK_LINE) $(SHARED_H)
	$(BREAK_LINE) $(SHARED_H)
	@echo $(AUX)#endif$(AUX) >> $(SHARED_H)

p_MainFile:
ifeq ($(OS), Windows_NT)
	@echo $(AUX)#include "$(SHARED_H)"$(AUX) > $(MAIN_FILE)
else
	@echo $(AUX)#include \"$(SHARED_H)\"$(AUX) > $(MAIN_FILE)
endif
	$(BREAK_LINE) $(MAIN_FILE)
	$(BREAK_LINE) $(MAIN_FILE)
	@echo $(AUX)int main()$(AUX) >> $(MAIN_FILE)
	@echo $(AUX){$(AUX) >> $(MAIN_FILE)
	@echo $(AUX)	return 0;$(AUX) >> $(MAIN_FILE)
	@echo $(AUX)}$(AUX) >> $(MAIN_FILE)
	$(BREAK_LINE) >> $(MAIN_FILE)

$(PROJECT_STRUCT):
ifeq ($(OS), Windows_NT)
	@mkdir $@
else
	@mkdir -p $@
endif

.PHONY: all build_project build_files