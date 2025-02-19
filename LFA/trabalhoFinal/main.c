#include "./scripts/inc/shared.h" 

 
int main() 
{ 
	Vetor* v;
	if(loadDotenv(0) == false)
		return 1;
	if(readEnvorimentVriables() == false)
		return 2;

	v = vetorBuild(gArraySize);

	executeBenchmark(v, quickSort);

	vetorFree(v);
	return 0; 
} 

