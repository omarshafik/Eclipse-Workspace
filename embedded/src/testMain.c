#include "avr/io.h"
#include <avr/interrupt.h>
#include "Timer.h"

uint8 flag = 0;

void flag_inc(void) {
	flag++;
}

int main(void) {
///////////////////////SETUP/////////////////////////////////////////
	Timer1init(PWM_OCR, Toggle, Toggle, Clock_64, 32768, 32768, flag_inc, 3);
	DDRD |= (1<<4) | (1<<5);

///////////////////////LOOP/////////////////////////////////////////

	for (;;) {

	}
	return 0;
}
