package mx.ipn.upiiz.theKatraTeam.lexer;

public enum TokenType {

    // comment block
    COMMENTLINE("//(.*)\n"),
    COMMENTBLOCK("/\\*.*\n.*\\*\\/"),
    SCOPESTART("\\{"),
    SCOPEEND("\\}"),
    BRASTART("\\("),
    BRAEND("\\)"),
    ARRSTART("\\["),
    ARREND("\\]"),
    // type patterns
    FLOAT("-?[0-9]+\\.[0-9]+"),
    INT("-?[0-9]+"),
    STRING("\"(.*?)\""),
    CHAR("'(.*?)'"),
    //BOOLEAN("true|false"),
    INCREMENT("\\+\\+"),
    DECREMENT("--"),
    PLUS("\\+"),
    MINUS("-"),
    PROD("\\*"),
    DIV("/"),
    GREATEROREQUALS(">="),
    LESSOREQUALS("<="),
    GREATER(">"),
    LESS("<"),
    EQUALS("=="),
    NOTEQUAL("!="),
    AND("\\&\\&"),
    OR("\\|\\|"),
    ASSIGNMENT("="),
    SEMICOLON(";"),
    ASSIGNTYPE(":"),
    WHITESPACE("[\t\f\n\r]+"),
    // type name patterns
    TYPEINT("Ent"),
    TYPEFLOAT("Dec"),
    TYPESTRING("Cad"),
    TYPECHAR("Char"),
    TYPEBOOL("Bool"),
    TYPEVOID("Void"),
    TRUE("true"),
    FALSE("false"),
    // keywords
    FOR("for"),
    WHILE("while"),
    IF("ja"),
    ELSE("nein"),
    RETURN("regresa"),
    BREAK("break"),
    CONTINUE("continue"),
    // ident (varable or function name)
    IDENT("[a-zA-Z0-9_]+"),
    EOF("EOF"),
    VAL("val"),
    VAR("var");

    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }

}
