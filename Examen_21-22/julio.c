#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#define RES_ENCONTRADO 0
#define RES_NO_ENCONTRADO 1
#define RES_INCIDENCIAS 2
#define RES_CANCELADO -1
/* funciones auxiliares que se necesiten */
int main ( int argc, char *argv[] )
{
    if (argc < 3) /* No hay necesidad de comprobar más cosas */
    {
        return EXIT_FAILURE;
    }
    /* código a desarrollar para resolver el problema */
    return EXIT_SUCCESS; /* si llega hasta aquí termina sin error */
}