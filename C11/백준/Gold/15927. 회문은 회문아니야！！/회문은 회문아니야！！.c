#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX_SIZE 500001

char input[MAX_SIZE];
bool isPal = true, isAll = true;
int len;

int main(void) {
	scanf("%s", input);

	len = strlen(input);
	
	for (int i = 0; i <= len / 2; i++) {
		if (input[i] != input[len -1 - i]) {
			isPal = false;
			isAll = false;
			break;
		}
		else if (input[i] != input[0]) {
			isAll = false;
		}
	}
	if (isAll) {
		printf("-1");
	}
	else if (isPal) {
		printf("%d", len-1);
	}
	else {
		printf("%d", len);
	}

	return 0;
}