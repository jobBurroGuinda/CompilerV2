package mx.ipn.upiiz.theKatraTeam.gui;

import java.awt.*;
import java.util.LinkedList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import mx.ipn.upiiz.theKatraTeam.grammar.CustomObject;
import mx.ipn.upiiz.theKatraTeam.grammar.PDefs;
import mx.ipn.upiiz.theKatraTeam.lexer.Lexer;
import mx.ipn.upiiz.theKatraTeam.lexer.Token;
import mx.ipn.upiiz.theKatraTeam.lexer.TokenType;
import mx.ipn.upiiz.theKatraTeam.parser.Parser;
import mx.ipn.upiiz.theKatraTeam.grammar.Program;
import mx.ipn.upiiz.visitor.Runner;
import mx.ipn.upiiz.visitor.Printer;


public class Editor {

    /**
     * Main frame
     */
    public JFrame frame;
    /**
     * editor pane
     */
    private JTextPane editor;
    /**
     * Console pane
     */
    private JTextPane console;
    /**
     * Lexer instance
     */
    private Lexer lexer;

    /**
     * Constructor
     */
    public Editor() {
        initialize();
        lexer = new Lexer();
    }

    /**
     * Initialize contents of frame
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 50, 900, 700);
        frame.setTitle("The KTRA Team. Aldo Leija Pardo, Ramón Emmanuel García Murillo y Job Adolfo Salinas Hernádez.");
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar();
        frame.getContentPane().add(toolBar, BorderLayout.NORTH);

        JButton btnText1 = new JButton("Texto 1");
        btnText1.addActionListener(e -> {
            String textTest = "Ent main() {\n" +
            "  Cad nombre = \"Mtra. Karina\"; \n" +
            "  Cad msg;\n" +
            "  Ent privilegio = 0;\n" +
            "  ja (privilegio == 0) {\n" +
            "     msg = \"Hola \" + nombre + \". ¡Sí se pudo! :) \";\n" +
            "  } nein {\n" +
            "     msg = \"Privilegio incorrecto\";\n" +
            "  }\n" +
            "  regresa msg;\n" +
            "}";
            editor.setText(textTest);
        });
        toolBar.add(btnText1);
        
        JButton btnText2 = new JButton("Texto 2");
        btnText2.addActionListener(e -> {
            String textTest = "Ent main() {\n" +
            "  Ent i = 0;\n" +
            "  Ent num = 1;\n" +
            "  Bool isOK = true;\n" +
            "  Cad msg = \"Intentos: \";\n" +
            "  while (isOK) {\n" +
            "    num = num * 3;\n" +
            "    msg = msg + i;\n" +
            "    ja(num>7000){\n" +
            "      isOK = false;\n" +
            "    }\n" +
            "    nein {\n" +
            "      isOK = true;\n" +
            "      msg = msg + \", \";\n" +
            "      i++;\n" +
            "    }\n" +
            "  }\n" +
            "  msg = msg + \". Terminamos con: \" + num + \", en la iteración: \" + i + \".\";\n" +
            "  regresa msg;\n" +
            "}";
            editor.setText(textTest);
        });
        toolBar.add(btnText2);

        JButton btnParse = new JButton("Analizar");

        btnParse.addActionListener(e -> {

            String input = editor.getText();

            if (input != null && !input.isEmpty()) {

                LinkedList<Token> tokenList = lexer.lex(input);

                Parser parser = new Parser(tokenList);

                Printer printer = new Printer();

                try {

                    Program program = parser.parseProgram();

                    String parsedString = printer.visit((PDefs) program);

                    console.setText(parsedString);

                } catch (Exception ex) {
                    console.setText(ex.toString());
                }
            } else {

                console.setText("Nada que analizar!");

            }

        });

        toolBar.add(btnParse);

        JButton btnEval = new JButton("Ejetutar");

        btnEval.addActionListener(e -> {

            String input = editor.getText();

            if (input != null && !input.isEmpty()) {

                LinkedList<Token> tokenList = lexer.lex(input);

                Parser parser = new Parser(tokenList);

                Runner runner = new Runner();

                try {

                    Program program = parser.parseProgram();

                    CustomObject returnValue = runner.visit((PDefs) program);

                    System.out.println(runner.GLOBAL_SCOPE);

                    if (returnValue != null)
                        console.setText(returnValue.toString());

                } catch (Exception ex) {
                    console.setText(ex.toString());
                }
            } else {

                console.setText("Nada que ejecutar");

            }

        });

        toolBar.add(btnEval);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        splitPane.setOneTouchExpandable(true);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);

        editor = new JTextPane();

        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                highlight(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                highlight(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        editor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        editor.setFont(new Font("Consolas", Font.PLAIN, 22));
        editor.setMinimumSize(new Dimension(0, 500));
        splitPane.setLeftComponent(editor);

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 22));
        console.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        splitPane.setRightComponent(console);
    }

    /**
     * Highlights tokens
     *
     * @param e event
     */
    private void highlight(DocumentEvent e) {

        Runnable doHighlight = () -> {

            try {

                Document doc = e.getDocument();
                String code = doc.getText(0, doc.getLength());

                LinkedList<Token> tokens = lexer.lex(code);

                HashMap<String, Color> tokenColors = new HashMap<>();
                tokenColors.put(TokenType.FLOAT.name(), Color.RED);
                tokenColors.put(TokenType.INT.name(), Color.RED);
                tokenColors.put(TokenType.STRING.name(), Color.GRAY);
                tokenColors.put(TokenType.CHAR.name(), Color.GRAY);
                tokenColors.put(TokenType.TRUE.name(), Color.MAGENTA);
                tokenColors.put(TokenType.FALSE.name(), Color.MAGENTA);
                tokenColors.put(TokenType.RETURN.name(), Color.BLUE);
                tokenColors.put(TokenType.IF.name(), Color.BLUE);
                tokenColors.put(TokenType.ELSE.name(), Color.BLUE);
                tokenColors.put(TokenType.WHILE.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPEINT.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPEBOOL.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPECHAR.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPEFLOAT.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPESTRING.name(), Color.BLUE);
                tokenColors.put(TokenType.TYPEVOID.name(), Color.BLUE);

                HashMap<String, Style> tokenStyles = new HashMap<>();

                for (String key : tokenColors.keySet()) {
                    Color color = tokenColors.get(key);
                    Style style = editor.addStyle(key, null);
                    StyleConstants.setForeground(style, color);
                    tokenStyles.put(key, style);
                }

                Style defaultStyle = editor.addStyle("default", null);
                StyleConstants.setForeground(defaultStyle, Color.BLACK);

                for (Token token : tokens) {
                    Style tokenStyle = tokenStyles.get(token.getType().name());
                    if (tokenStyle != null)
                        editor
                                .getStyledDocument()
                                .setCharacterAttributes(token.getPosition(), token.getData().length(), tokenStyle, true);
                    else
                        editor
                                .getStyledDocument()
                                .setCharacterAttributes(token.getPosition(), token.getData().length(), defaultStyle, true);
                }
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        };

        SwingUtilities.invokeLater(doHighlight);
    }

}
