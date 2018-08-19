/*
 * ADC.h
 *
 *  Created on: Jul 12, 2018
 *      Author: pcmcc
 */
#include "std_types.h"
#include "micro_config.h"
#include "common_macros.h"

#ifndef SRC_ADC_H_
#define SRC_ADC_H_

typedef enum {
	AREF, AVCC, Internal = 3
} VoltageReference;

typedef enum {
	PA_0, PA_1, PA_2, PA_3, PA_4, PA_5, PA_6, PA_7
} Channel;

void ADC_init(VoltageReference ref);
uint16 ADC_readChannel(Channel pin);

#endif /* SRC_ADC_H_ */
