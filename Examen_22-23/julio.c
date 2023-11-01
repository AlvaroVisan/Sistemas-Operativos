#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
static void uso ( void );
void procesar ( const int argc, const char* argv[] );
                static void montar (
                const char foto1[], /* nombre del fichero que contiene la primera foto */
                const char foto2[], /* nombre del fichero que contiene la segunda foto */
                const int n, /* número de secuencia único para cada operación */
                const int max, /* número máximo de procesos que pueden ejecutarse concurrentemente */
                const char dir_resultados[] /* nombre del directorio donde se van a dejar los resultados*/
                );
int main ( const int argc, const char* argv[] )
{
    if ( argc < 5 )
    {
        uso();
        exit(EX_USAGE);
    }
    else
    {
        procesar ( argc, argv );
        exit(EX_OK);
    }
}
void procesar ( const int argc, const char* argv[] )
{
    const int max = atoi ( argv[1] );
    const char* dir_resultados = argv[2];
    for ( int i = 3; i < argc-1; i++ )
        montar ( argv[i], argv[i+1], i - 2, max, dir_resultados );
}
static void uso(void)
{
    fprintf ( stderr, "\nUso: ./examen max_procesos_concurrentes dir_resultados fich_imagen...\n" );
    fprintf ( stderr, "Se deben indicar al menos 2 ficheros de imágenes\n" );
    fprintf ( stderr, "\nEjemplo: examen 5 combinadas orig/*.jpg\n\n" );
}
static void montar ( const char foto1[], const char foto2[], const int n, const int max,
                    const char dir_resultados[] )
{
    char destino[MAXPATHLEN];
    char orden[MAXPATHLEN*3];
    snprintf ( destino, sizeof(destino), "%s/%s-%d.jpg", dir_resultados, "montaje", n );
    snprintf ( orden, sizeof(orden), "montage -resize 200%% %s %s %s", foto1,foto2, destino);
    printf ( "ORDEN: %s\n", orden );
    system ( orden ); // NO SE PERMITE USAR system() EN EL EJERCICIO ENTREGADO
}