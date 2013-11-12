package frontend;

import intermediate.IntermediateCode;
import intermediate.SymbolTableEntry;
import intermediate.SymbolTableEntryAttribute;
import intermediate.SymbolTableStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    protected SymbolTableStack symbolTableStack;
    protected ArrayList<IntermediateCode> topLevelLists;
    protected Scanner scanner;
    private Stack<Integer> parenthesisCount;

    public Parser(Scanner scanner) {
        topLevelLists = new ArrayList<IntermediateCode>();
        symbolTableStack = new SymbolTableStack();
        this.scanner = scanner;
    }

    public IntermediateCode parse() throws IOException {
        System.out.println("\n----------Printing Tokens---------\n");

        while (scanner.peekChar() != Source.EOF) {
            parenthesisCount = new Stack<Integer>();
            IntermediateCode root = parseList();
            topLevelLists.add(root);
        }

        return null;
    }

    public IntermediateCode parseList() {
        IntermediateCode rootNode = new IntermediateCode();

        try {
            Token token = nextToken();
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

            if (scanner.getPosition() == 0 && scanner.peekChar() != '\0') {
                System.out.println(scanner);
            }

            switch (token.getType()) {
                case LEFT_PAREN:
                    if (!parenthesisCount.empty()) {
                        parenthesisCount.push(parenthesisCount.pop() + 1);
                    }

                    rootNode.setCar(parseList());

                    // Create extra parent node when DEFINE, LET, LETSTAR, or LETREC is read
                    IntermediateCode newRoot = null;
                    if (rootNode.getCar() != null && rootNode.getCar().getType() != null
                            && TokenType.SCOPE_STARTER.contains(rootNode.getCar().getType()))
                    {
                        newRoot = new IntermediateCode();
                        newRoot.setCar(rootNode);
                    }

                    rootNode.setCdr(parseList());

                    // Linking parse tree with symbol table
                    /*
                    if (rootNode.getCar() != null && rootNode.getCar().getType() != null
                            && rootNode.getCar().getType() == TokenType.REGULAR_SYMBOL
                            && rootNode.getCdr() != null && rootNode.getCdr().getCar() != null)
                    {
                        SymbolTableEntry entry = symbolTableStack.lookup(rootNode.getCar().getText());
                        entry.put(SymbolTableEntryAttribute.BIND, rootNode.getCdr().getCar());
                        rootNode.getCar().setEntry(entry);
                    }
                    */

                    // Set the new root if DEFINE, LET, LETSTAR, or LETREC is read
                    if (newRoot != null) {
                        rootNode = newRoot;
                    }
                    break;
                case RIGHT_PAREN:
                    if (!parenthesisCount.empty() && parenthesisCount.peek() > 0) {
                        parenthesisCount.push(parenthesisCount.pop() - 1);

                        if (parenthesisCount.peek() == 0) {
                            parenthesisCount.pop();

                            // TODO: Comment this line out if you want to see parse tree for debugging.
                            // TODO: This should stay in the final version.
                            symbolTableStack.pop();

                            if (scanner.currentChar() == ')' || scanner.peekChar() == ')') {
                                parseList();
                            }
                        }
                    }
                case END_OF_FILE:
                    return null;
                case LAMBDA:
                case LET:
                case LETSTAR:
                case LETREC:
                    symbolTableStack.push();

                    if (!parenthesisCount.empty()) {
                        parenthesisCount.push(parenthesisCount.pop() - 1);
                    }

                    parenthesisCount.push(1);
                case DEFINE:
                    rootNode.setText(token.getText());
                    rootNode.setType(token.getType());
                    break;
                case RESERVED_SYMBOL:
                case REGULAR_SYMBOL:
                    SymbolTableEntry symbol = symbolTableStack.lookup(token.getText());

                    if (symbol != null) {
                        // If it gets in here, that means the symbol has been defined elsewhere. So use it!

                        // TODO: This line is for debugging only. It should NOT be entered into local symbol table.
                        //symbolTableStack.enterLocal(token.getText());
                    }
                    else {
                        symbolTableStack.enterLocal(token.getText());
                    }
                default:
                    rootNode.setCar(new IntermediateCode());
                    rootNode.getCar().setText(token.getText());
                    rootNode.getCar().setType(token.getType());
                    rootNode.setCdr(parseList());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return rootNode;
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
