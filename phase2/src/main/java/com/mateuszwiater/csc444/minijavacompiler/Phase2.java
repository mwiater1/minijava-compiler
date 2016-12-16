package com.mateuszwiater.csc444.minijavacompiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Phase2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("Phase 2");
        final URL url = Phase2.class.getClassLoader().getResource("Test.java");
        final Phase1 phase1 = new Phase1(new File(url.toURI()));
        MiniJavaParser parser = phase1.getParser();
        parser.setBuildParseTree(true);
        ParseTreeWalker walker = new ParseTreeWalker();
        ParserRuleContext tree = parser.goal();
        MiniJavaWalkListener walkListener = new MiniJavaWalkListener();
        walker.walk(walkListener, tree);

        walkListener.getKlasses().forEach(k -> {
            System.out.println(k);
            if(k.getSuperKlass() != null) {
                System.out.println("\tExtends: " + k.getSuperKlass());
            }
            System.out.println("\tVariables:");
            k.getVariables().forEach(v -> {
                System.out.println("\t\t" + v);
            });
            System.out.println("\tMethods:");
            k.getMethods().forEach(m -> {
                System.out.println("\t\t" + m);
                System.out.println("\t\t\tParameters:");
                m.getParameters().forEach(p -> System.out.println("\t\t\t\t" + p.getVariable()));
                System.out.println("\t\t\tVariables:");
                m.getVariables().forEach(v -> System.out.println("\t\t\t\t" + v));
                System.out.println("\t\t\tStatements:");
                m.getStatements().forEach(s -> System.out.println("\t\t\t\t" + s));
            });
        });
    }
}
