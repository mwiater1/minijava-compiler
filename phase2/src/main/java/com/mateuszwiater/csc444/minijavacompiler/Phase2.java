package com.mateuszwiater.csc444.minijavacompiler;

import com.mateuszwiater.csc444.minijavacompiler.listener.ListenerFactory;

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

        ListenerFactory lf = new ListenerFactory();

        parser.addParseListener(lf.getKlassListener());
        parser.addParseListener(lf.getMethodListener());
        parser.addParseListener(lf.getParameterListener());
        parser.addParseListener(lf.getStatementListener());
        parser.addParseListener(lf.getTypeListener());
        parser.addParseListener(lf.getVariableListener());
        parser.goal();
        parser.reset();

        lf.getKlasses().forEach(k -> {
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
                m.getParameters().forEach(p -> {
                    System.out.println("\t\t\t\t" + p.getVariable());
                });
                System.out.println("\t\t\tVariables:");
                m.getVariables().forEach(v -> {
                    System.out.println("\t\t\t\t" + v);
                });
            });
        });
    }
}
