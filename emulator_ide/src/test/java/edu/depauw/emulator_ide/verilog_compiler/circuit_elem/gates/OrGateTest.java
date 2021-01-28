package edu.depauw.emulator_ide.verilog_compiler.circuit_elem.gates;
import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.misc_elem.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrGateTest {
    @Test
    public void OrTest(){
    	Register input1 = new Register(false);
	Register input2 = new Register(false);

	Wire in1 = new Wire();
	Wire in2 = new Wire();
	Wire out1 = new Wire();
	
	CircuitElem orGate = new OrGate(out1, in1, in2); //put wires through a basic or gate
	
	Primitive table = new Primitive(2, 1); //two input one output table
	table.addRow(new Inputs(true, true), new Outputs(true));
	table.addRow(new Inputs(true, false), new Outputs(false));
	table.addRow(new Inputs(false, true), new Outputs(false));
	table.addRow(new Inputs(false, false), new Outputs(false));

	primitiveVerify(table, new RealInputs(input1, input2), new RealOutputs(out1)); //check if the boolean logic table playes out correctly in the circuit created
    }
    
}
