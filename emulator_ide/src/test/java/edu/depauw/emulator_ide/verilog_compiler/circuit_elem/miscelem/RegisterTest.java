package edu.depauw.emulator_ide.verilog_compiler.circuitelem.miscelem;

import edu.depauw.emulator_ide.verilog_compiler.circuitelem.gates.*;
import edu.depauw.emulator_ide.verilog_compiler.circuitelem.CircuitElem;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegisterTest{
    @Test
    public void BasicRegisterTest(){
	Register PULLUP = new Register(true);
	Register PULLDOWN = new Register(false);

	assertTrue(PULLUP.getSignal());
	assertTrue(!PULLDOWN.getSignal());
    }
    @Test
    public void ToggleSignalTest(){
	Register value = new Register(true);
        CircuitElem and = new AndGate(value, value);
	assertTrue(and.getSignal());
	value.setSignal(false);
	assertTrue(!and.getSignal());
    }
}