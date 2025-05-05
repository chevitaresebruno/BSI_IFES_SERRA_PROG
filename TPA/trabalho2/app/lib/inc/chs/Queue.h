#ifndef QUEUE_H
#define QUEUE_H 0

/* Dependencies */
#include <stdlib.h>
#include "./../../share.h"

typedef struct slq SimpleLinkedQueue;

SimpleLinkedQueue* newSlq();
void slqFree(SimpleLinkedQueue* q);
void slqNFree(SimpleLinkedQueue* q);
void slqForeach(SimpleLinkedQueue* q, foreachF func);
bool slqFFree(SimpleLinkedQueue* q, freeFunc func);
bool slqAdd(SimpleLinkedQueue* q, void* v);
void* slqPop(SimpleLinkedQueue* q);

#endif