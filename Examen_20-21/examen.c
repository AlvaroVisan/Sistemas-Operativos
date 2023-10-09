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
int main ( void )
{
    aleatorio = arc4random();
    /* Acciones que debe hacer el programa:
    - Crear una tabla en memoria compartida con capacidad para
    almacenar 5 enteros y dejarla limpia (sin datos).
    - Crear 5 procesos que ejecuten la función aplicarAlgoritmo()
    y guarden el entero que devuelve esta función en una posición
    de la tabla, distinta para cada proceso.
    - Esperar a que uno de los procesos creados termine.
    - Mandar terminar a todos los procesos que aún se están
    ejecutando, enviándoles la señal SIGTERM.
    - Esperar a que todos los procesos terminen.
    - Escribir por la salida estándar el resultado obtenido.
    */
    return 0;
}