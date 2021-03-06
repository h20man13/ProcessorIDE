package edu.depauw.emulator_ide.verilog_compiler.ast.expression;

import edu.depauw.emulator_ide.verilog_compiler.ast.AstNode;
import edu.depauw.emulator_ide.verilog_compiler.visitor.ExpressionVisitor;
import edu.depauw.emulator_ide.common.Position;

/** The empty.expression class is designed as a place holder to par.E an empty expression
 * @author Jacob Bauer
 */
public class EmptyExpression extends Expression{

    /** The empty.expression constructor only takes a position then it pas Es that up to the Expression constructor
     *  @param position Position of the empty.expression
     */
    
    public EmptyExpression(Position position){
	super(position);
    }

    /**The accept method will make it so the visitor interface will work
     * @param astNodeVisitor the visitor object we want to use to visit another member of a class
     */
    public <ExprVisitType> ExprVisitType accept(ExpressionVisitor<ExprVisitType> exprVisitor, Object... argv){
	return exprVisitor.visit(this, argv);
    }
}
