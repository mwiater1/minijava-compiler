package com.mateuszwiater.csc444.minijavacompiler;

import com.mateuszwiater.csc444.minijavacompiler.expression.AddExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.AndExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.ArrayAccessExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.ArrayInstantiationExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.BooleanLiteralExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;
import com.mateuszwiater.csc444.minijavacompiler.expression.IdentifierExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.InstantiationExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.IntegerLiteralExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.LengthExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.LtExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.MethodCallExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.MulExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.NotExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.ParentExpression;
import com.mateuszwiater.csc444.minijavacompiler.expression.SubEspression;
import com.mateuszwiater.csc444.minijavacompiler.expression.ThisExpression;
import com.mateuszwiater.csc444.minijavacompiler.statement.ArrayAssignmentStatement;
import com.mateuszwiater.csc444.minijavacompiler.statement.IfElseStatement;
import com.mateuszwiater.csc444.minijavacompiler.statement.NestedStatement;
import com.mateuszwiater.csc444.minijavacompiler.statement.PrintStatement;
import com.mateuszwiater.csc444.minijavacompiler.statement.Statement;
import com.mateuszwiater.csc444.minijavacompiler.statement.VariableAssignmentStatement;
import com.mateuszwiater.csc444.minijavacompiler.statement.WhileStatement;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MiniJavaWalkListener extends MiniJavaBaseListener {
    private final Stack<Type> typeStack;
    private final Stack<Klass> klassStack;
    private final Stack<Method> methodStack;
    private final Stack<Variable> variableStack;
    private final Stack<Statement> statementStack;
    private final Stack<Parameter> parameterStack;
    private final Stack<Expression> expressionStack;

    public MiniJavaWalkListener() {
        typeStack       = new Stack<>();
        klassStack      = new Stack<>();
        methodStack     = new Stack<>();
        variableStack   = new Stack<>();
        statementStack  = new Stack<>();
        parameterStack  = new Stack<>();
        expressionStack = new Stack<>();
    }

    public List<Klass> getKlasses() {
        return klassStack.stream().collect(Collectors.toList());
    }

    public void print() {
        System.out.println("SIZE: " + typeStack.size() + " TYPES: " + typeStack);
        System.out.println("SIZE: " + klassStack.size() + " KLASSES: " + klassStack);
        System.out.println("SIZE: " + methodStack.size() + " METHODS: " + methodStack);
        System.out.println("SIZE: " + variableStack.size() + " VARIABLES: " + variableStack);
        System.out.println("SIZE: " + statementStack.size() + " STATEMENTS: " + statementStack);
        System.out.println("SIZE: " + parameterStack.size() + " PARAMETERS: " + parameterStack);
        System.out.println("SIZE: " + expressionStack.size() + " EXPRESSIONS: " + expressionStack);
    }

    @Override
    public void exitGoal(MiniJavaParser.GoalContext ctx) {
        print();
    }

    // ======================================KLASS START=======================================

    @Override
    public void exitMainClass(MiniJavaParser.MainClassContext ctx) {
        final String identifier = ctx.Identifier(0).getText();
        final Type methodType = new Type("void");
        final List<Parameter> methodParameters = Collections.singletonList(new Parameter(new Variable(new Type("String[]"), ctx.Identifier(1).getText())));
        final Method method = new Method("main", methodType, methodParameters, Collections.emptyList(), Collections.singletonList(statementStack.pop()), null);
        klassStack.push(new Klass(identifier, null, Collections.emptyList(), Collections.singletonList(method)));
    }

    @Override
    public void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        final List<Method> methods = IntStream.range(0, ctx.methodDeclaration().size())
                .mapToObj(i -> methodStack.pop()).collect(Collectors.toList());
        final List<Variable> variables = IntStream.range(0, ctx.fieldDeclaration().size())
                .mapToObj(i -> variableStack.pop()).collect(Collectors.toList());
        Collections.reverse(methods);
        Collections.reverse(variables);
        final Klass extendsKlass = ctx.extendClass() != null ? new Klass(ctx.extendClass().Identifier().getText()) : null;
        final String identifier = ctx.Identifier().getText();
        klassStack.push(new Klass(identifier, extendsKlass, variables, methods));
    }

    // ======================================METHOD START=======================================

    @Override
    public void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        final Expression expression = expressionStack.pop();
        final List<Statement> statements = IntStream.range(0, ctx.methodBody().statement().size())
                .mapToObj(i -> statementStack.pop()).collect(Collectors.toList());
        final List<Variable> variables = IntStream.range(0, ctx.methodBody().localDeclaration().size())
                .mapToObj(i -> variableStack.pop()).collect(Collectors.toList());
        final List<Parameter> parameters = IntStream.range(0, ctx.parameter().size())
                .mapToObj(i -> parameterStack.pop()).collect(Collectors.toList());
        final Variable variable = variableStack.pop();
        Collections.reverse(statements);
        Collections.reverse(variables);
        Collections.reverse(parameters);
        methodStack.push(new Method(variable.getIdentifier(), variable.getType(), parameters, variables, statements, expression));
    }


    // ======================================STATEMENT START=======================================

    @Override
    public void exitArrayAssignmentStatement(MiniJavaParser.ArrayAssignmentStatementContext ctx) {
        final String identifier = ctx.getToken(MiniJavaParser.Identifier, 0).getText();
        final Expression expression1, expression2;
        expression2 = expressionStack.pop();
        expression1 = expressionStack.pop();
        statementStack.push(new ArrayAssignmentStatement(identifier, expression1, expression2));
    }

    @Override
    public void exitIfElseStatement(MiniJavaParser.IfElseStatementContext ctx) {
        final Expression expression = expressionStack.pop();
        final Statement ifBlock, elseBlock;
        elseBlock = statementStack.pop();
        ifBlock = statementStack.pop();
        statementStack.push(new IfElseStatement(expression, ifBlock, elseBlock));
    }

    @Override
    public void exitVariableAssignmentStatement(MiniJavaParser.VariableAssignmentStatementContext ctx) {
        final String identifier = ctx.getToken(MiniJavaParser.Identifier, 0).getText();
        final Expression expression = expressionStack.pop();
        statementStack.push(new VariableAssignmentStatement(identifier, expression));
    }

    @Override
    public void exitWhileStatement(MiniJavaParser.WhileStatementContext ctx) {
        final Expression expression = expressionStack.pop();
        final Statement statement = statementStack.pop();
        statementStack.push(new WhileStatement(expression, statement));
    }

    @Override
    public void exitPrintStatement(MiniJavaParser.PrintStatementContext ctx) {
        statementStack.push(new PrintStatement(expressionStack.pop()));
    }

    @Override
    public void exitNestedStatement(MiniJavaParser.NestedStatementContext ctx) {
        final List<Statement> statements = IntStream.range(0, ctx.statement().size())
                .mapToObj(i -> statementStack.pop()).collect(Collectors.toList());
        Collections.reverse(statements);
        statementStack.push(new NestedStatement(statements));
    }

    // ======================================EXPRESSION START======================================

    @Override
    public void exitThisExpression(MiniJavaParser.ThisExpressionContext ctx) {
        expressionStack.push(new ThisExpression());
    }

    @Override
    public void exitIdentifierExpression(MiniJavaParser.IdentifierExpressionContext ctx) {
        expressionStack.push(new IdentifierExpression(ctx.getText()));
    }

    @Override
    public void exitIntLitExpression(MiniJavaParser.IntLitExpressionContext ctx) {
        expressionStack.push(new IntegerLiteralExpression(Integer.parseInt(ctx.getText())));
    }

    @Override
    public void exitBooleanLitExpression(MiniJavaParser.BooleanLitExpressionContext ctx) {
        expressionStack.push(new BooleanLiteralExpression(Boolean.parseBoolean(ctx.getText())));
    }

    @Override
    public void exitNotExpression(MiniJavaParser.NotExpressionContext ctx) {
        expressionStack.push(new NotExpression(expressionStack.pop()));
    }

    @Override
    public void exitParenExpression(MiniJavaParser.ParenExpressionContext ctx) {
        expressionStack.add(new ParentExpression(expressionStack.pop()));
    }

    @Override
    public void exitObjectInstantiationExpression(MiniJavaParser.ObjectInstantiationExpressionContext ctx) {
        expressionStack.push(new InstantiationExpression(ctx.getToken(MiniJavaParser.Identifier, 0).getText()));
    }

    @Override
    public void exitArrayLengthExpression(MiniJavaParser.ArrayLengthExpressionContext ctx) {
        expressionStack.push(new LengthExpression(expressionStack.pop()));
    }

    @Override
    public void exitArrayInstantiationExpression(MiniJavaParser.ArrayInstantiationExpressionContext ctx) {
        expressionStack.push(new ArrayInstantiationExpression(expressionStack.pop()));
    }

    @Override
    public void exitArrayAccessExpression(MiniJavaParser.ArrayAccessExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new ArrayAccessExpression(expression1, expression2));
    }

    @Override
    public void exitLtExpression(MiniJavaParser.LtExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new LtExpression(expression1, expression2));
    }

    @Override
    public void exitAndExpression(MiniJavaParser.AndExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new AndExpression(expression1, expression2));
    }

    @Override
    public void exitAddExpression(MiniJavaParser.AddExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new AddExpression(expression1, expression2));
    }

    @Override
    public void exitMulExpression(MiniJavaParser.MulExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new MulExpression(expression1, expression2));
    }

    @Override
    public void exitSubExpression(MiniJavaParser.SubExpressionContext ctx) {
        final Expression expression1, expression2;
        expression1 = expressionStack.pop();
        expression2 = expressionStack.pop();
        expressionStack.push(new SubEspression(expression1, expression2));
    }

    @Override
    public void exitMethodCallExpression(MiniJavaParser.MethodCallExpressionContext ctx) {
        final String identifier = ctx.getToken(MiniJavaParser.Identifier, 0).getText();
        final List<Expression> expressions = IntStream.range(0, ctx.expression().size() - 1)
                .mapToObj(i -> expressionStack.pop()).collect(Collectors.toList());
        Collections.reverse(expressions);
        expressionStack.push(new MethodCallExpression(identifier, expressionStack.pop(), expressions));
    }

    // =====================================TERMINALS START======================================

    @Override
    public void exitParameter(MiniJavaParser.ParameterContext ctx) {
        parameterStack.push(new Parameter(variableStack.pop()));
    }

    @Override
    public void exitType(MiniJavaParser.TypeContext ctx) {
        typeStack.push(new Type(ctx.getText()));
    }

    @Override
    public void exitTypeIdentifier(MiniJavaParser.TypeIdentifierContext ctx) {
        variableStack.push(new Variable(typeStack.pop(), ctx.getStop().getText()));
    }
}
