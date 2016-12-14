package com.mateuszwiater.csc444.minijavacompiler;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Phase1 {
    private static final Logger logger = LoggerFactory.getLogger("Phase 1");
    private final String fileName;
    private final MiniJavaParser parser;
    private final List<String> fileLines;

    public static void main(String[] args) throws IOException, URISyntaxException {
        final URL url = Phase1.class.getClassLoader().getResource("Test.java");
        new Phase1(new File(url.toURI()));
    }

    Phase1(final File file) throws IOException {
        fileName = file.getName();
        fileLines = Files.readAllLines(file.toPath());
        parser = new MiniJavaParser(new CommonTokenStream(new MiniJavaLexer(new ANTLRInputStream(new FileInputStream(file)))));
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());
        parser.addErrorListener(new MiniJavaSyntaxErrorListener());
        parser.goal();
    }

    private class MiniJavaSyntaxErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            logger.error("Syntax Error in File '" + fileName + "' on Line " + line + '\n'
                    + fileLines.get(line - 1) + '\n'
                    + IntStream.range(0, charPositionInLine + 1).mapToObj(i -> i == charPositionInLine ? "^" : " ").collect(Collectors.joining()) + '\n'
                    + msg + '\n');
        }
    }
}
