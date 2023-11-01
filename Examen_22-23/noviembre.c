#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
static void uso(void);
static void redimensionar(const char* fich_imagen, const char* dir_resultados,int tamano);
int main(int argc, char** argv)
{
    const char* dir_resultados;
    if (argc < 3) {
        uso( argv[0] );
        exit(EX_USAGE);
    }
    dir_resultados = argv[1];
    for (int i = 2; i < argc; i++) {
        for (int tamano = 10; tamano <= 80; tamano *= 2) {
            redimensionar(argv[i], dir_resultados, tamano);
        }
    }
    exit(EX_OK);
}
static void uso( const char* nombre )
{
    fprintf(stderr, "\nUso: %s dir_resultados fich_imagen...\n", nombre);
    fprintf(stderr, "\nEjemplo: %s redim orig/*.jpg\n\n", nombre);
}
static void redimensionar(const char* fich_imagen, const char* dir_resultados,int tamano)
{
    const char* nombre_base;
    char nombre_destino[MAXPATHLEN];
    char orden[MAXPATHLEN*3];
    char tamano_s[20];
    nombre_base = strrchr(fich_imagen, '/');
    if (nombre_base == NULL)
        nombre_base = fich_imagen;
    else
        nombre_base++;
    snprintf(nombre_destino, sizeof(nombre_destino), "%s/%s-%d.jpg",dir_resultados, nombre_base, tamano);
    snprintf(tamano_s, sizeof(tamano_s), "%d%%", tamano);
    snprintf(orden, sizeof(orden), "convert '%s' -resize %s '%s'", fich_imagen,tamano_s, nombre_destino);
    printf("ORDEN: %s\n", orden);
    system(orden); // NO SE PERMITE USAR system() EN EL EJERCICIO ENTREGADO
}