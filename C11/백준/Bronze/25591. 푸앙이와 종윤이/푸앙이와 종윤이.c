#include <stdio.h>

int main(void) {
	int num1, num2;
	scanf("%d %d", &num1, &num2);

	int a, b, c, d, q, r, red, blue;
	a = 100 - num1;
	b = 100 - num2;
	c = 100 - a - b;
	d = a * b;
	q = d / 100;
	r = d % 100;
	red = c + q;
	blue = r;
	printf("%d %d %d %d %d %d\n", a, b, c, d, q, r);
	printf("%d %d", red, blue);

	return 0;
}