#include "./scripts/inc/shared.h" 

 
int main() 
{ 
	Vetor* v;
	loadDotenv(0);
	if(readEnvorimentVriables() == false)
		return 2;

	v = vetorBuild(gArraySize);

	// executeBenchmark(v, quickSort);

	executeBenchmark(v, mergeSort);

	vetorFree(v);
	return 0; 
} 

