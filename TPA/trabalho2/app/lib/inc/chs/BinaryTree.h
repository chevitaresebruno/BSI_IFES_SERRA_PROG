#ifndef BINARY_TREE_H
#define BINARY_TREE_H 0

/* Dependencies */
#include "./BinaryTree.h"


typedef struct bt BinaryTree;
BinaryTree* newBT();
bool btAdd(BinaryTree* bt, void* nv, cmpFunc cmpF);
void* btPop(BinaryTree* bt, const void* v, cmpFunc func);
unsigned int btSize(const BinaryTree* bt);
void* btSeacrh(BinaryTree* bt, void* v, cmpFunc cmpF);
unsigned int btHeight(const BinaryTree* bt);
SimpleLinkedQueue* btWInLevel(const BinaryTree* bt);
SimpleLinkedQueue* btWInOrder(const BinaryTree* bt);

#endif