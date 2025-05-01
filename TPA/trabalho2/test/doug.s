.text
.globl add

/*
    rax/eax/ax/al -> Registrador para retorno de função (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    rdi/edi/di/dil -> Registrador 1 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    rsi/esi/si/sil -> Registrador 2 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    rdx/edx/dx/dl -> Registrador 3 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    rcx/ecx/cx/cl -> Registrador 4 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    r8/r8d/r8w/r8b -> Registrador 5 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
    r9/r9d/r9w/r9b -> Registrador 6 de argumento para inteiros/ponteiros (8 bytes / 4 bytes / 2 bytes / 1 bytes)
*/


add: 
    /* Armazenar contextos na pilha de memória */
    push    %rdi; /* Guardou o contexto do registrador de argumento */
    mov     $0, %eax; /* (eax) a = 0 */

forloop:
    cmp     $0, %rdi; /* (rdi) x == NULL (0) ? */
    je      end; /* Caso seja nulo, finaliza o for */
    add     (%rdi), %eax; /* (eax) a += x->val (*rdi) */
    add     $4, %rdi; /* (rdi) x = x->next (rdi + 8) */
    jmp     forloop; /* Reinicia o for */
    
end:
    pop %rdi; /* Recuperou o contexto do registrador de argumento */
    ret;
