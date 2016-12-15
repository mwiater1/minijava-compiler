package com.mateuszwiater.csc444.minijavacompiler;

import com.mateuszwiater.csc444.minijavacompiler.listeners.KlassListener;
import com.mateuszwiater.csc444.minijavacompiler.listeners.MethodListener;
import com.mateuszwiater.csc444.minijavacompiler.listeners.ParameterListener;
import com.mateuszwiater.csc444.minijavacompiler.listeners.TypeListener;
import com.mateuszwiater.csc444.minijavacompiler.listeners.VariableListener;

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
        Scope scope = new Scope();
        parser.addParseListener(new KlassListener(scope));
        parser.addParseListener(new VariableListener(scope));
        parser.addParseListener(new TypeListener(scope));
        parser.addParseListener(new MethodListener(scope));
        parser.addParseListener(new ParameterListener(scope));
        parser.goal();
        parser.reset();

        scope.getKlasses().forEach(k -> {
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
