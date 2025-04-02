/*
    @brief Estrutura responsável por conter as informações de uma lista simplemente encadeada
*/
typedef struct SLLH SimpleLinkedListHeader;

/*
    @brief Cria uma nova Lista Simplemente Encadeada Vazia

    @return
        - `SimpleLinkedListHeader*` Endereço de memória para o cabeçalho da lista; ela inicia vazia; e
        
        - `NULL` Em caso de memória insuficiente.
*/
SimpleLinkedListHeader* sllhCreate();


/*  
    @brief Chama a função `foreach` para cada valor armazenado na lista; se o valor armazenado for `NULL` ele pula essa execução a passa para próxima.
    

    @param SimpleLinkedListHeader* Pointeiro para o cabeçalho da lista;
    
    @param void(*foreach)(void*)
        Ponteiro para uma função com com retorno `void` e um parâmetro do tipo `void*`;
        É de responsabilidade da função fazer  typecast.
*/
void sllhForeachS(SimpleLinkedListHeader* SimpleLinkedList, void(*foreach)(void*));


/*
    @brief Chama a função `foreach` para cada valor armazenado na lista; se o valor armazenado for `NULL` ele passa nulo como argumento.


    @param SimpleLinkedListHeader* Pointeiro para o cabeçalho da lista;
    
    @param void(*foreach)(void*)
        Ponteiro para uma função com com retorno `void` e um parâmetro do tipo `void*`;
        É de responsabilidade da função fazer  typecast.
*/
void sllhForeach(SimpleLinkedListHeader* SimpleLinkedList, void(*foreach)(void*));


/*
    @brief Chama a função `foreach` para cada valor armazenado na lista e seu índice; se o valor armazenado for `NULL` ele passa nulo como argumento.

    
    @param SimpleLinkedListHeader* Pointeiro para o cabeçalho da lista;
    
    @param void(*foreach)(void*)
        Ponteiro para uma função com com retorno `void` e um parâmetro do tipo `void*`;
        É de responsabilidade da função fazer  typecast.
*/
void sllhForeachIndex(SimpleLinkedListHeader* SimpleLinkedList, void(*foreach)(void*, const unsigned int));


/*
    @brief Adiciona um elemento à lista.


    @param SimpleLinkedListHeader* Ponteiro para o cabeçalho da lista;

    @param void* valor a ser adicionado na lista; se o valor for nulo ele não é inserido.

    
    @return
        - 0 em caso de argumentos nulos;

        - 1 em casso de memória insuficiente; e
        
        - 2 caso tudo esteja correto.
*/
int sllhAddS(SimpleLinkedListHeader* SimpleLinkedList, void* value);


/*
    @brief Adiciona um elemento à lista.


    @param SimpleLinkedListHeader* Ponteiro para o cabeçalho da lista;

    @param void* valor a ser adicionado na lista; se o valor for nulo ele é inserido.

    
    @return
        - 0 em caso de argumentos nulos;

        - 1 em casso de memória insuficiente; e
        
        - 2 caso tudo esteja correto.
*/
int sllhAdd(SimpleLinkedListHeader* SimpleLinkedList, void* value);


/*
    @brief Remove e retorna o valor do primeiro nó da lista.  O nó removido será desalocado, e o próximo nó se tornará o primeiro da lista.
    
    @param SimpleLinkedListHeader* Ponteiro para o cabeçalho da lista.
    
    @return 
        - `void*` O valor armazenado no nó removido.

        - `NULL` Caso a lista esteja vazia.
*/
void* sllhPop(SimpleLinkedListHeader* SimpleLinkedList);


/*
    @brief Retorna o valor armazenado no nó de um índice específico da lista.
    
    @param SimpleLinkedListHeader* SimpleLinkedList 
        - Ponteiro para o cabeçalho da lista.
    
    @param const unsigned int index 
        - O índice do nó a ser acessado na lista.
    
    @return 
        - `void*` O valor armazenado no nó na posição indicada.
        - `NULL` Se o índice for inválido (fora do intervalo da lista).
*/
void* sllhGetAt(SimpleLinkedListHeader* SimpleLinkedList, const unsigned int index);


/*
    @brief Retorna o valor armazenado no primeiro nó da lista.
    
    @param SimpleLinkedListHeader* SimpleLinkedList 
        - Ponteiro para o cabeçalho da lista.
    
    @return 
        - `void*` O valor armazenado no primeiro nó da lista.
        - `NULL` Se a lista estiver vazia.
*/
void* sllhGetFirst(SimpleLinkedListHeader* SimpleLinkedList);


/*
    @brief Retorna o valor armazenado no último nó da lista.
    
    @param SimpleLinkedListHeader* SimpleLinkedList 
        - Ponteiro para o cabeçalho da lista.
    
    @return 
        - `void*` O valor armazenado no último nó da lista.
        - `NULL` Se a lista estiver vazia.
*/
void* sllhGetLast(SimpleLinkedListHeader* SimpleLinkedList);


/*
    @brief Retorna o tamanho (número de elementos) da lista.
    
    @param SimpleLinkedListHeader* SimpleLinkedList 
        - Ponteiro para o cabeçalho da lista.
    
    @return 
        - `unsigned int` O número de elementos na lista.
        - `0` Se a lista estiver vazia.
*/
unsigned int sllhGetSize(SimpleLinkedListHeader* SimpleLinkedList);


/*
    @brief Destrói a lista e libera toda a memória associada a ela.
    
    @param SimpleLinkedListHeader* SimpleLinkedList 
        - Ponteiro para o cabeçalho da lista a ser destruída.
    
    @param void(*destroyFunction)(void*) 
        - Ponteiro para uma função responsável por destruir os valores armazenados na lista.
        - A função `destroyFunction` deve liberar a memória associada ao valor de cada nó.
    
    @return 
        - `1` Se a destruição for bem-sucedida.
        - `0` Se ocorrer um erro durante a destruição.
*/
int sllhDestroy(SimpleLinkedListHeader* SimpleLinkedList, void(*detroyFunction)(void*));


/*
    @brief Adiciona "times" elementos dentro da lista baseado em uma função de geração dos valores.
*/
void sllhAddByFunc(SimpleLinkedListHeader* sllh, const unsigned int times, void*(*generateFunction)());