package edu.depauw.emulator_ide.verilog_compiler.main;

import edu.depauw.emulator_ide.common.io.Destination;
import edu.depauw.emulator_ide.common.io.Source;

import edu.depauw.emulator_ide.common.debug.InfoLog;
import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.CircuitElem;
import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.misc_elem.Register;
import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.misc_elem.Wire;
import edu.depauw.emulator_ide.verilog_compiler.token.Token;

import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.test_utils.Primitive;
import edu.depauw.emulator_ide.verilog_compiler.circuit_elem.test_utils.Tuple;

import static edu.depauw.emulator_ide.verilog_compiler.main.test_utils.TestUtils.*;

import org.junit.Test;
import java.io.StringReader;

public class LexerTest{
        @Test
	public void testIdentifiers() {
	        System.err.println("-----Identifier Test----");
		String input = "This is test1";
		
		prepareErrorLog(0);
		prepareLexer(new Tuple(Token.Type.IDENT,
				       Token.Type.IDENT,
				       Token.Type.IDENT)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

        @Test
	public void testMacroIdentifiers() {
	        System.err.println("-----MacroIdentifier Test----");
		String input = "`define `COOL `cooler `define";
		
		prepareErrorLog(0);
		prepareLexer(new Tuple(Token.Type.MACRODEF,
				       Token.Type.MACROIDENT,
				       Token.Type.MACROIDENT,
				       Token.Type.MACRODEF)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
	
	@Test
	public void testKeyWords() {
	        System.err.println("-----Keyword Test----");
		String input = "initial allways begin end module endmodule task endtask function endfunction assign posedge negedge or and nand nor xor xnor not if else while wait forever repeat for integer real reg wire output input";

		prepareErrorLog(0);	
		prepareLexer(new Tuple(Token.Type.INITIAL,
				       Token.Type.ALLWAYS,
				       Token.Type.BEGIN,
				       Token.Type.END,
				       Token.Type.MODULE,
				       Token.Type.ENDMODULE,
				       Token.Type.TASK,
				       Token.Type.ENDTASK,
				       Token.Type.FUNCTION,
				       Token.Type.ENDFUNCTION,
				       Token.Type.ASSIGN,
				       Token.Type.POSEGE,
				       Token.Type.NEGEGE,
				       Token.Type.ORGATE,
				       Token.Type.ANDGATE,
				       Token.Type.NANDGATE,
				       Token.Type.NORGATE,
				       Token.Type.XORGATE,
				       Token.Type.XNORGATE,
				       Token.Type.NOTGATE,
				       Token.Type.IF,
				       Token.Type.ELSE,
				       Token.Type.WHILE,
				       Token.Type.WAIT,
				       Token.Type.FOREVER,
				       Token.Type.REPEAT,
				       Token.Type.FOR,
				       Token.Type.INTEGER,
				       Token.Type.REAL,
				       Token.Type.REG,
				       Token.Type.WIRE,
				       Token.Type.OUTPUT,
				       Token.Type.INPUT)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

	@Test
	public void testDecimal() {
	        System.err.println("-----Basic Decimal Test----");
		String input = "0 11 9999 0123456789 'D893084 4'd98349";
		prepareErrorLog(0);
		
		prepareLexer(new Tuple(Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

        @Test
	public void testHexidecimal() {
	        System.err.println("-----Basic Hexidecimal Test----");
		String input = "'h98f08 4'Hfffff 54'h0984903";
		prepareErrorLog(0);
		
		prepareLexer(new Tuple(Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM
				       )
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

        @Test
	public void testOctal() {
	        System.err.println("-----Basic Octal Test----");
		String input = "'o07 4'O74343 34'O01713";
		prepareErrorLog(0);
		
		prepareLexer(new Tuple(Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM
				       )
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

        @Test
	public void testBinary() {
	        System.err.println("-----Basic Binary Test----");
		String input = "'b0101010 4'b011101 34'B011010";
		prepareErrorLog(0);
		
		prepareLexer(new Tuple(Token.Type.NUM,
				       Token.Type.NUM,
				       Token.Type.NUM
				       )
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
	
	@Test
	public void testStrings() {
	        System.err.println("-----String Test----");
		String input = "\"\" \"testing\" \"!@#$%^&(*)-_=+\"";
		
		prepareErrorLog(0);
		
		prepareLexer(new Tuple(Token.Type.STRING,
				       Token.Type.STRING,
				       Token.Type.STRING
				       )
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
	
	@Test
	public void testComments() {
	        System.err.println("-----Comment Test----");
	        String input = "/* this is a comment */ //this is another comment";
		prepareErrorLog(0);
		prepareLexer(new Tuple());
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
	
	@Test
	public void testOperators() {
	        System.err.println("-----Operator Test----");
		String input = "(){}[],+-*%/?:<><=>=;@$= == === != !== &&||!&~ | ^^~~^ ~& ~| <<>>";
		
		prepareErrorLog(0);
		prepareLexer(new Tuple(Token.Type.LPAR,
				       Token.Type.RPAR,
				       Token.Type.LCURL,
				       Token.Type.RCURL,
				       Token.Type.LBRACK,
				       Token.Type.RBRACK,
				       Token.Type.COMMA,
				       Token.Type.PLUS,
				       Token.Type.MINUS,
				       Token.Type.TIMES,
				       Token.Type.MOD,
				       Token.Type.DIV,
				       Token.Type.QUEST,
				       Token.Type.COLON,
				       Token.Type.LT,
				       Token.Type.GT,
				       Token.Type.LE,
				       Token.Type.GE,
				       Token.Type.SEMI,
				       Token.Type.AT,
				       Token.Type.DOLLAR,
				       Token.Type.EQ1,
				       Token.Type.EQ2,
				       Token.Type.EQ3,
				       Token.Type.NE1,
				       Token.Type.NE2,
				       Token.Type.LAND,
				       Token.Type.LOR,
				       Token.Type.LNEG,
				       Token.Type.BAND,
				       Token.Type.BNEG,
				       Token.Type.BOR,
				       Token.Type.BXOR,
				       Token.Type.BXNOR,
				       Token.Type.BXNOR,
				       Token.Type.BNAND,
				       Token.Type.BNOR,
				       Token.Type.LSHIFT,
				       Token.Type.RSHIFT)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
	
	@Test
	public void testErrors() {
	        System.err.println("-----Error Test----");
		String input = "\"unclosed!";
		
		prepareErrorLog(1);
		
		prepareLexer(new Tuple());
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}

        @Test
	public void testMixed() {
	    System.err.println("-----Mixed(half adder) Test----");
	    String input = "module half_adder (\n" +
		"i_bit1,\n" +
		"i_bit2,\n" +
		"o_sum,\n"+
		"o_carry\n"+
		");\n"+
		
		"input  i_bit1;\n"+
		"input  i_bit2;\n"+
		"output o_sum;\n"+
		"output o_carry;\n"+
		
		"assign o_sum   = i_bit1 ^ i_bit2; // bitwise xor\n"+
		"assign o_carry = i_bit1 & i_bit2; // bitwise and\n"+
 
		"endmodule // half_adder\n";
		
		prepareErrorLog(0);
		
	        prepareLexer(new Tuple(Token.Type.MODULE,
				       Token.Type.IDENT,
				       Token.Type.LPAR,
				       Token.Type.IDENT,
				       Token.Type.COMMA,
				       Token.Type.IDENT,
				       Token.Type.COMMA,
				       Token.Type.IDENT,
				       Token.Type.COMMA,
				       Token.Type.IDENT,
				       Token.Type.RPAR,
				       Token.Type.SEMI,
				       Token.Type.INPUT,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.INPUT,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.OUTPUT,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.OUTPUT,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.ASSIGN,
				       Token.Type.IDENT,
				       Token.Type.EQ1,
				       Token.Type.IDENT,
				       Token.Type.BXOR,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.ASSIGN,
				       Token.Type.IDENT,
				       Token.Type.EQ1,
				       Token.Type.IDENT,
				       Token.Type.BAND,
				       Token.Type.IDENT,
				       Token.Type.SEMI,
				       Token.Type.ENDMODULE)
			     );
		
		testLexer(new Lexer(new Source(new StringReader(input)), new InfoLog(new Destination(System.out))));
	}
}
