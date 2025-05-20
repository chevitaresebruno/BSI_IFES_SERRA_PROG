#ifndef BINARY_TREE_AVL_H
#define BINARY_TREE_AVL_H 0

#include "./BinaryTree.h"

typedef struct bt BinaryTreeAvl;
BinaryTreeAvl* newBTAvl(cmpFunc cmp);
void btaFFree(BinaryTreeAvl* bta, freeFunc func);
bool btaAdd(BinaryTreeAvl* bta, void* nv);
void* btaPop(BinaryTreeAvl* bta, void* v);
void* batPopBy(BinaryTreeAvl* bta, void* v, cmpFunc func);

#endif