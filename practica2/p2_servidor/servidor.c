#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sysexits.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define RES_FIN_CORRECTO 1
#define RES_ERROR 2
#define RES_DATO_INVALIDO 3

struct argumentos {
	int puerto_escucha;
};

static int recoger_argumentos(int argc, char** argv, struct argumentos* argumentos);
static void uso(void);
static int activar_escucha(const int puerto_escucha);
static int aceptar_cliente(const int s_escucha);
static void atender_cliente(const int s_cliente);
static int procesar_sesion(const int s_cliente);
static long buscar_primo_superior(const long n);
static int es_primo(const long n);

int main(int argc, char** argv)
{
	int s_escucha, s_cliente;
	struct argumentos argumentos;

	if (recoger_argumentos(argc, argv, &argumentos)) {
		uso();
		exit(EX_USAGE);
	}

	s_escucha = activar_escucha(argumentos.puerto_escucha);
	if (s_escucha == -1) {
		fprintf(stderr, "ERROR: no se ha podido activar la escucha en el puerto %d.\n", argumentos.puerto_escucha);
		fprintf(stderr, "Recomendaciones: mata todos los procesos de pruebas anteriores (servidor y nc). Como alternativa, prueba con un puerto diferente (> 1024).\n");
		exit(EX_UNAVAILABLE);
	}
	printf("INFO PID %d: escucho en el puerto %d.\n", getpid(), argumentos.puerto_escucha);

	do {
		s_cliente = aceptar_cliente(s_escucha);
		if (s_cliente != -1) {
			atender_cliente(s_cliente);
			// Descomentar en el servidor multiproceso para que tanto el padre como el hijo cierren sus respectivos descriptores del socket.
			// close(s_cliente);
		}
	} while (s_cliente != -1);
	
	close(s_escucha);
	exit(EX_OK);
}

static void atender_cliente(const int s_cliente)
{
	int res_proc;

	res_proc = procesar_sesion(s_cliente);
	if (res_proc == RES_DATO_INVALIDO)
		printf("AVISO: el cliente ha introducido un dato inválido.\n");
}

static int procesar_sesion(const int s_cliente)
{
	FILE* f_cliente;
	char linea[100];
	long numero, primo_superior;
	int fin;

	printf("INFO PID %d: atiendo a cliente nuevo.\n", getpid());
	if ((f_cliente = fdopen(s_cliente, "r+")) == NULL) {
		perror("fdopen");
		return RES_ERROR;
	}
	setlinebuf(f_cliente);
	fprintf(f_cliente, "Bienvenido. Mi PID es %d.\n", getpid());
	do {
		fprintf(f_cliente, "Número: ");
		fin = (fgets(linea, sizeof linea, f_cliente) == NULL || strcmp(linea, "fin\n") == 0);
		if (! fin) {
			numero = atol(linea);
			if (numero > 0) {
				fprintf(f_cliente, "Calculando...\n");
				primo_superior = buscar_primo_superior(numero);
				fprintf(f_cliente, "%ld es el primer primo superior a %ld.\n", primo_superior, numero);
			}
		}
	} while (! fin && numero > 0);
	fprintf(f_cliente, "Adios\n");
	fclose(f_cliente);
	printf("INFO PID %d: fin de la sesión.\n", getpid());
	if (fin)
		return RES_FIN_CORRECTO;
	else
		return RES_DATO_INVALIDO;
}

static int activar_escucha(const int puerto_escucha)
{
	int s_escucha;
	struct sockaddr_in sa_escucha;

	if ((s_escucha = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
		perror("socket");
		return -1;
	}
	bzero(&sa_escucha, sizeof sa_escucha);
	sa_escucha.sin_family      = AF_INET;
	sa_escucha.sin_port        = htons(puerto_escucha);
	sa_escucha.sin_addr.s_addr = htonl(INADDR_ANY);
	if (bind(s_escucha, (struct sockaddr*) &sa_escucha, sizeof sa_escucha) == -1) {
		perror("bind");
		return -1;
	}
	if (listen(s_escucha, 10) == -1) {
		perror("listen");
		return -1;
	}
	return s_escucha;
}

static int aceptar_cliente(const int s_escucha)
{
	int s_cliente;

	s_cliente = accept(s_escucha, NULL, NULL);
	if (s_cliente == -1)
		perror("accept");
	return s_cliente;
}

static long buscar_primo_superior(const long n)
{
	long p;

	for (p = n+1; ! es_primo(p); p++);
	return p;
}

static int es_primo(const long n)
{
	long i;

	for (i = 2; i < n; i++)
		if (n % i == 0)
			return 0;
	return 1;
}

static int recoger_argumentos(int argc, char** argv, struct argumentos* argumentos)
{
	int opc;

	argumentos->puerto_escucha = 1234;
	while ((opc = getopt(argc, argv, "p:")) != -1) {
		switch(opc) {
			case 'p':
				argumentos->puerto_escucha = atoi(optarg);
				if (argumentos->puerto_escucha < 1024 || argumentos->puerto_escucha > 65535) {
					fprintf(stderr, "Error: número de puerto inválido.\n");
					return 1;
				}
				break;
			case '?':
				return 1;
		}
	}
	if (optind < argc) {
		fprintf(stderr, "Error: sobran argumentos.\n");
		return 1;
	}
	return 0;
}

static void uso(void)
{
	fprintf(stderr, "\nUso: servidor [-p puerto_escucha]\n");
	fprintf(stderr, "\nEjemplo: servidor -p 4321\n\n");

}
