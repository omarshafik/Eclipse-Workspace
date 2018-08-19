/*
 * Timer.h
 *
 *  Created on: Jul 8, 2018
 *      Author: pcmcc
 */
#include "std_types.h"
#include "micro_config.h"
#include "common_macros.h"

#ifndef SRC_TIMER_H_
#define SRC_TIMER_H_

typedef enum {
	Normal = 0,
	Clear_Compare_OCR = 4,
	PWM_256 = 5,
	PWM_512 = 6,
	PWM_1024 = 7,
	PWM_OCR = 15
} Timer1_Mode;

typedef enum {
	No_Output, Toggle, Clear, Set
} Output_Mode;

typedef enum {
	No_Prescale = 1, Clock_8, Clock_64, Clock_256, Clock_1024
} Prescaler;

void Timer1init(Timer1_Mode mode, Output_Mode op_mode_A, Output_Mode op_mode_B,
		Prescaler prescaler, uint16 compare_valueA, uint16 compare_valueB,
		void (*callback_fn)(void), uint8 channel);

#endif /* SRC_TIMER_H_ */
