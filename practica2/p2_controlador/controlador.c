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

static void uso(void);
static void convertir(const char* fich_imagen, const char* dir_resultados);
static void esperarHijos();
static void mandarTerminar();


int main(int argc, char** argv)
{
	const char* dir_resultados;
	const int cores = 4;
	int j = 0;

	if (argc < 3) {
		uso();
		exit(EX_USAGE);
	}
	dir_resultados = argv[1];
	for (int i = 2; i < argc; i++)
	{
		for (int j = 0; j < cores && i+j < argc; j++)
		{
			if (fork() == 0)
			{
				convertir(argv[i+j], dir_resultados);
				exit(EX_SOFTWARE);
			}
			if(j==3)
			{
				wait(NULL);
				i=i+j;
			}
		}
	}
	mandarTerminar();
	esperarHijos();
	exit(EX_OK);
}

static void uso(void)
{
	fprintf(stderr, "\nUso: paralelo dir_resultados fich_imagen...\n");
	fprintf(stderr, "\nEjemplo: paralelo difuminadas orig/*.jpg\n\n");
}

static void convertir(const char* fich_imagen, const char* dir_resultados)
{
	const char* nombre_base;
	char nombre_destino[MAXPATHLEN];
	char orden[MAXPATHLEN*3];

	nombre_base = strrchr(fich_imagen, '/');
	if (nombre_base == NULL)
		nombre_base = fich_imagen;
	else
		nombre_base++;
	snprintf(nombre_destino, sizeof(nombre_destino), "%s/%s", dir_resultados, nombre_base);
	snprintf(orden, sizeof(orden), "convert '%s' -blur 0x8 '%s'", fich_imagen, nombre_destino);
	fprintf(stderr, "AVISO: La versión baśica del programa usa system() para lanzar procesos nuevos. Los estudiantes deben cambiarla por fork-exec-wait\n");
	system(orden);
}
static void esperarHijos()
{
	while ( wait(NULL) != -1);
}
static void mandarTerminar ( void )
{
    fprintf ( stdout, "Voy a mandar terminar a los hijos\n" );
    signal ( SIGTERM, SIG_IGN );
    kill ( -getpgid(0), SIGTERM );
    fprintf ( stdout, "He mandado terminar a los hijos\n" );
}
