#ifndef BINARY_TREE_H
#define BINARY_TREE_H 0

/* Dependencies */
#include "./../../share.h"
#include "./Queue.h"


typedef struct bt BinaryTree;
BinaryTree* newBT(cmpFunc func);
void btFFree(BinaryTree* bt, freeFunc func);
bool btSome(BinaryTree* bt, bool(*func)(void*));
bool btAdd(BinaryTree* bt, void* nv);
void* btPopBy(BinaryTree* bt, const void* v, cmpFunc func);
void* btPop(BinaryTree* bt, const void* v);
unsigned int btSize(const BinaryTree* bt);
void* btSearchBy(BinaryTree* bt, void* v, cmpFunc cmpF);
void* btSearch(BinaryTree* bt, void* v);
unsigned int btHeight(const BinaryTree* bt);
SimpleLinkedQueue* btWInLevel(const BinaryTree* bt);
SimpleLinkedQueue* btWInOrder(const BinaryTree* bt);

#endif