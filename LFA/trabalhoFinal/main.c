#include "./scripts/inc/shared.h" 

#include "./scripts/src/vetor/Vetor.c"
#include "./scripts/src/ordenacao/oQuickSort.c"

 
int main() 
{ 
	Vetor* v = vetorBuild(10);
	register iterator i;

	srand(SEED);
	vetorFill(v, MAX_ELEMENT_SIZE);

	for(i = 0; i < v->size; i++)
		printf("%u ", v->data[i]);
	
	
	printf("\n");
	quickSort(v);
	for(i = 0; i < v->size; i++)
		printf("%u ", v->data[i]);

	vetorFree(v);
	return 0; 
} 

