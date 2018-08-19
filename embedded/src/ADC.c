#include "ADC.h"

typedef union {
	struct {
		uint8 mux :5;
		uint8 LeftAdj :1;
		uint8 reference :2;
	} bits;
	uint8 data;
} MuxSelect;

typedef union {
	uint8 data;
	struct {
		uint8 prescaler :3;
		uint8 interrupt_enable :1;
		uint8 interrupt_flag :1;
		uint8 auto_trigger_enable :1;
		uint8 start_conversion :1;
		uint8 enable :1;
	} bits;
} Control;

typedef union {
	struct {
		uint8 :4;
		uint8 high_speed_mode :1;
		uint8 auto_trigger_source :3;
	} bits;
	uint8 data;
} SpecialFnIO;

void ADC_init(){

}
