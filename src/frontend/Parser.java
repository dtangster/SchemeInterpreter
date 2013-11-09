package frontend;

import intermediate.IntermediateCode;
import intermediate.SymbolTable;
import intermediate.SymbolTableEntry;
import intermediate.SymbolTableStack;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    protected SymbolTableStack symbolTableStack;
    protected SymbolTable symbolTable;
    protected ArrayList<IntermediateCode> topLevelLists;
    protected Scanner scanner;
    protected int counter = 0;
    private boolean initial = true;

    public Parser(Scanner scanner) {
        symbolTableStack = new SymbolTableStack();
        topLevelLists = new ArrayList<IntermediateCode>();
        this.scanner = scanner;
    }

    public void parse() throws IOException {
        System.out.println("\n----------Printing Tokens---------\n");

        while (scanner.peekChar() != Source.EOF) {
            symbolTable = new SymbolTable();
            symbolTableStack.push(symbolTable);
            IntermediateCode root = parseList().getCar();
            topLevelLists.add(root);
        }
    }

    public IntermediateCode parseList() {
        IntermediateCode newNode = null;

        if(!initial && counter == 0) {
            initial = true;
            return null;
        }
        initial = false;

        try {
            Token token = nextToken(); // Consume (
            System.out.print("\t" + token.getText() + "\t");

            if (TokenType.RESERVED_WORDS.containsKey(token.getText())) {
                System.out.println("Reserved Word");
            }
            else if (TokenType.RESERVED_SYMBOLS.containsKey(token.getText())) {
                System.out.println("Reserved Symbol");
            }
            else if (token.getType() == TokenType.REGULAR_SYMBOL) {
                System.out.println("Symbol");
            }
            else if (token.getType() == TokenType.INTEGER) {
                System.out.println("Integer");
            }
            else if (token.getType() == TokenType.REAL) {
                System.out.println("Real");
            }

            if (scanner.getPosition() == 0) {
                System.out.println(scanner);
            }

            switch (token.getType()) {
                case LEFT_PAREN:
                    counter++;
                    newNode = new IntermediateCode();
                    newNode.setCar(parseList());
                    newNode.setCdr(parseList());
                    break;
                case RIGHT_PAREN:
                    counter--;
                case END_OF_FILE:
                    break;
                case REGULAR_SYMBOL:
                    SymbolTableEntry entry = new SymbolTableEntry(token.getText(), symbolTable);
                    symbolTable.put(token.getText(), entry);
                default:
                    newNode = new IntermediateCode();
                    newNode.setText(token.getText());
                    newNode.setType(token.getType());
                    newNode.setCdr(parseList());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return newNode;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ArrayList<IntermediateCode> getICodes() {
        return topLevelLists;
    }

    public SymbolTableStack getSymTabStack() {
        return symbolTableStack;
    }

    public Token currentToken() {
        return scanner.currentToken();
    }

    public Token nextToken() throws IOException {
        return scanner.nextToken();
    }
}
