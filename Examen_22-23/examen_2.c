#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <errno.h>

static void uso ( const char* nombre );
static void procesar ( const char* problema, const char* programa );
static void mandarTerminar ( void );
static void esperarHijos (void);

int main ( const int argc, const char* argv[] )
{
    if ( argc < 3 )
    {
        uso ( argv[0] );
        exit ( EX_USAGE );
    }
    for ( int i = 2; i < argc; i++ )
        procesar ( argv[1], argv[i] );
    /* esperar a que termine uno de ellos */
    wait(NULL);
    /* ordenar terminar a todos los programas que aún no hayan terminado */
    mandarTerminar();
    /* esperar a que terminen todos los programas */
    esperarHijos();
    /* terminar con éxito */
    exit ( EX_OK );
}
static void uso ( const char* nombre )
{
    fprintf ( stderr, "Uso: %s problema aplicación...\n", nombre );
    fprintf ( stderr, "Ejemplo: %s 758hy2j9dv987 ./aplicacion*\n", nombre );
}
/* Función de ejemplo que ejecuta el programa en primer plano */
static void procesar ( const char* problema, const char* programa )
{
    char orden[MAXPATHLEN*3];
    snprintf ( orden, sizeof(orden), "'%s' '%s'", programa, problema );
    if (fork() == 0)
    {
        execl (programa, programa, problema, NULL);
        perror ("exec");
        exit(EX_SOFTWARE);
    }
}
/* ordena terminar a todos los hijos que aún no hayan terminado */
static void mandarTerminar ( void )
{
    printf ( stdout, "Voy a mandar terminar a los hijos\n" );
    signal ( SIGTERM, SIG_IGN );
    kill ( -getpgid(0), SIGTERM );
    fprintf ( stdout, "He mandado terminar a los hijos\n" );
}
static void esperarHijos(void)
{
    while ( wait(NULL) != -1);
}