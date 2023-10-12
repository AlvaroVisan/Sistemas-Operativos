#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
/* funciones auxiliares que se necesiten */
static void lanzarProcesos (const char* prog, const int n, const char* prob)
{
    for (int i = 0; i < n; i++)
        if(fork()==0)
            execl (prog, prog, prob, NULL);
}
static int obtenerResultados (const int n)
{
    int suma;
    int cont, i;
    int restar;
    for (suma = cont = i = 0; i < n; i++)
    {
        int estado;
        wait( &estado );
        if ( WIFEXITED(estado))
        {
            suma = suma + WEXITSTATUS(estado);
            cont++;
        }
    }
    if (cont == 0)
        return -1;
    else
        return suma / cont;
}
int main ( const int argc, const char * const argv[] )
{
    #define ejecutable argv[1]
    #define problema argv[3]
    if ( argc != 4 ) /* no son necesarias comprobacioens adicionales */
        return -1;
    /* código a desarrollar para resolver el problema */
    const int instancias = atoi (argv[2]);
    lanzarProcesos(ejecutable, instancias, problema);
    const int resultado = obtenerResultados(instancias);
    if(resultado < 0)
        printf("Todas las instancias han fallado\n");
    else
        printf("resultado = %d\n", resultado);
    return 0; /* Si llega hasta aquí, termina sin error */
}