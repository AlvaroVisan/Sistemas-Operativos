#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
static const int NA = 5; /* número de algoritmos a utilizar */
static uint32_t aleatorio; /* usado para generar una solución aleatoria */
static const int SIN_DATO = 0; /* dato no válido */
/*
* Función que deben ejecutar los procesos.
* Esta función no debe modificarse.
*/
static int aplicarAlgoritmo ( const int alg )
{
    const uint32_t espera = ( arc4random() * (uint32_t)alg ) % 10000000;
    const int resultado = aleatorio & 65535;
    usleep ( espera + 1000000 );
    return resultado;
}

/*
* Anota en todas las casillas de la tabla que no contienen un
* dato válido. Esta función no debe modificarse.
*/
static void limpiarTabla ( int tabla[] )
{
    for ( int i = 0; i < NA; i++ )
        tabla[i] = SIN_DATO;
}
/*
* Función que permite obtener el resultado a partir del contenido
* de la tabla. Esta función no debe modificarse.
*/
static int obtenerResultado ( const int tabla[] )
{
    int i;
    for ( i = 0; i < NA && tabla[i] == SIN_DATO; i++ );
    return tabla[i];
}
static int* crearTablaResultados (void)
{
    const size_t tam = sizeof(int) * (size_t) NA;
    const int prot = PROT_READ | PROT_WRITE;
    const int flags = MAP_ANON | MAP_SHARED;
    return mmap( NULL, tam, prot, flags, -1, (off_t)0);
}
static void crearProcesos (int *tabla)
{
    for ( int i = 0; i< NA; i++)
        if (fork() == 0)
        {
            tabla[i] = aplicarAlgoritmo(i);
            exit(0);
        }
}
static void esperarResultado ()
{
    wait ( NULL );
}
int main ( void )
{
    aleatorio = arc4random();
    /* Acciones que debe hacer el programa:
    - Crear una tabla en memoria compartida con capacidad para
    almacenar 5 enteros y dejarla limpia (sin datos).
    */
   int* const tabla = crearTablaResultados();
   if (tabla == NULL)
        return -1;
    limpiarTabla(tabla);
   /*
    - Crear 5 procesos que ejecuten la función aplicarAlgoritmo()
    y guarden el entero que devuelve esta función en una posición
    de la tabla, distinta para cada proceso.
    */
    crearProcesos(tabla);
    /*
    - Esperar a que uno de los procesos creados termine.
    */
    esperarResultado();
   /*
    - Mandar terminar a todos los procesos que aún se están
    ejecutando, enviándoles la señal SIGTERM.
    */
    kill (-1, SIGTERM);
   /*
    - Esperar a que todos los procesos terminen.
    */
    while ( wait(NULL) > 0);
   /*
    - Escribir por la salida estándar el resultado obtenido.
    */
    const int resultado = obtenerResultado(tabla);
    printf ("El resultado ha sido %d\n", resultado);
    return 0;
}