#include "Timer.h"

struct ControlRegA {
	uint8 WGM :2;		//waveform generation mode
	uint8 FOC :2;		//forced output compare
	uint8 COMB :2;		//compare output mode
	uint8 COMA :2;
};

struct ControlRegB {
	uint8 CS :3; 	//clock select (prescaler)
	uint8 WGM :2;	//wave generation mode
	uint8 :3;
};

void Timer1init(Timer1_Mode mode, Output_Mode op_mode_A, Output_Mode op_mode_B,
		Prescaler prescaler, uint16 compare_valueA, uint16 compare_valueB,
		void (*callback_fn)(void), uint8 channel) {
	cli();
	struct ControlRegA CRA;
	struct ControlRegB CRB;
	CRA.WGM = (mode & 0x0003);
	CRB.WGM = (mode >> 2);
	if (mode < 5) {
		CRA.FOC = 3;
		CRA.COMA = 0;
		CRA.COMB = 0;
		if (0 == mode) {
			ISR(TIMER1_OVF_vect)
			{
				callback_fn();
			}
		} else {
			ISR(TIMER1_COMPA_vect)
			{
				callback_fn();
			}
		}
	} else if (channel < 4) {
		CRA.FOC = 0;
		CRA.COMA = op_mode_A;
		CRA.COMB = op_mode_B;
	}
	OCR1A = compare_valueA;
	OCR1B = compare_valueB;
	CRB.CS = prescaler;
	TCCR1A = *(uint8*) (&CRA);
	TCCR1B = *(uint8*) (&CRB);
	TIMSK |= (channel << 3) | (1 << 2);
	sei();
//ISR
}
