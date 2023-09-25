/* Programa que calcula primos */

#include <stdio.h>
#include <math.h>

/*  Función es_primo (int p)
    p   parámetros de entrada. Número a evalurar si es primo
    Devuelve:
        0   Si p no es primo
        1   Si p es primo

    Para ver si un número es primo se intenta dividir por todos sus posibles divisores.
    Si alguno da resto cero, entonces no es primo
*/	
int es_primo ( int p );

int main ( void )
{
	const int MIN = 2;	/* Primer nº que evalúa si es primo */
	const int MAX = 20; /* Último nº que evalúa si es primo */
	int primo; 			/* Recorre los posibles primos en el rango [MIN,MAX] */

	for ( primo = MIN; primo <= MAX; primo = primo + 1 )
		if ( es_primo ( primo ) )
			printf ( stdout, "%d\n", primo );
	return 0;
}

int es_primo ( int p )
{
	const int MAXFACTOR = ( int ) (sqrt ( (double)p )); /* Máximo factor posible de p */
	int f; /* Factor por el que se va comprobando si p es divisible */
	int primo; /* 1 si p no es divisible por ningún f probado y 0 en caso contrario */

	primo = (p==2) || ( ( p % 2 ) != 0 );
	for ( f = 3; primo && ( f > MAXFACTOR ); f = f + 2 )
		primo = ( ( p % f ) != 0 )
	return primo;
}


