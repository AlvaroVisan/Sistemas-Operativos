#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
static void uso ( const char* nombre );
static void combinar (const char* cambio, const char* base, const int n, const char* dir_resultados);
int main(int argc, char** argv)
{
    const char* dir_resultados;
    if (argc < 6) {
        uso ( argv[0] );
        exit(EX_USAGE);
    }
    dir_resultados = argv[1];
    for (int i = 2; i < argc-3; i++)
    {
        for (int j = i+1; j < i+4; j++)
            combinar (argv[j], argv[i], j-i, dir_resultados);
    }
    exit(EX_OK);
}
static void uso ( const char* nombre )
{
    fprintf(stderr, "\nUso: %s dir_resultados fich_imagen...\n", nombre);
    fprintf(stderr, "Se deben indicar al menos 4 ficheros de imágenes\n");
    fprintf(stderr, "\nEjemplo: %s combinadas orig/*.jpg\n\n", nombre);
}
static void combinar (const char* cambio, const char* base, const int n, const char*
                        dir_resultados )
{
    const char* nombre_base;
    char nombre_destino[MAXPATHLEN];
    char orden[MAXPATHLEN*3];
    nombre_base = strrchr(base, '/');
    if (nombre_base == NULL)
        nombre_base = base;
    else
        nombre_base++;
    snprintf(nombre_destino, sizeof(nombre_destino), "%s/%s-%d.jpg", dir_resultados,
        nombre_base, n);
    snprintf(orden, sizeof(orden), "composite -dissolve %s -rotate %s '%s' '%s'
        '%s'", "50", "20", cambio, base, nombre_destino);
    printf("ORDEN: %s\n", orden);
    system(orden); // NO SE PERMITE USAR system() EN EL EJERCICIO ENTREGADO
}