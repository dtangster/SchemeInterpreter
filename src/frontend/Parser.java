package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import intermediate.*;
import backend.*;

/**
 * A simple Scheme parser.
 * @author Ronald Mak
 */
public class Parser
{
    private Scanner scanner;
    private TreeMap<String, SymbolTableEntry> symtab;
    private ArrayList<IntermediateCode> topLevelLists;
    private boolean isDefine;
    private String functionName;
    private int level;
    private SymbolTable topLevel;
    private SymbolTableStack symbolTableStack;
    private Stack<Integer> parenthesisCount;


    /**
     * Constructor.
     * @param scanner the simple Scheme scanner.
     */
    public Parser(Scanner scanner)
    {
        this.scanner = scanner;
        this.symtab = new TreeMap<String, SymbolTableEntry>();
        this.topLevelLists = new ArrayList<IntermediateCode>();
        this.isDefine = false;
        this.functionName = null;
    }
    /**
     * The parse method.
     * This version also builds parse trees.
     */
    public void parse()
    {
        Token token;

        // Loop to get tokens until the end of file.
        while ((token = nextToken()).getType() != TokenType.END_OF_FILE) {
            TokenType tokenType = token.getType();

            if (tokenType == TokenType.LEFT_PAREN) {
                topLevelLists.add(parseList());
            }
        }
    }

    /**
     * Get and return the next token from the scanner.
     * Enter identifiers and symbols into the symbol table.
     * @return the next token.
     */
    private Token nextToken()
    {

        Token token = null;
        try {
            token = scanner.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TokenType tokenType = token.getType();
          //Linking parser tree and symbol table.
          if(isDefine)
        {
            isDefine = false;
            functionName = token.getText();
            SymbolTableEntry entry = new SymbolTableEntry(functionName);
            token.setEntry(entry);
            topLevel.addEntry(functionName, new SymbolTableEntry(functionName));
        }

        switch (token.getType()) {
            case LEFT_PAREN:
                if (!parenthesisCount.empty()) {
                    parenthesisCount.push(parenthesisCount.pop() + 1);
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

                        /*if (scanner.currentChar() == ')' || scanner.peekChar() == ')') {
                            parseList();
                        } */
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
        }

        return token;
    }



    /**
     * Parse a list and build a parse tree.
     * @return the root of the tree.
     */
    private IntermediateCode parseList()
    {
        IntermediateCode root = new IntermediateCode();
        IntermediateCode currentNode = null;

        // Get the first token after the opening left parenthesis.
        Token token = nextToken();
        TokenType tokenType = token.getType();

        // Loop to get tokens until the closing right parenthesis.
        while (tokenType != TokenType.RIGHT_PAREN) {


            parenthesisCount = new Stack<Integer>();

            // Set currentNode initially to the root,
            // then move it down the cdr links.
            if (currentNode == null) {
                currentNode = root;
            }
            else {
                IntermediateCode newNode = new IntermediateCode();
                currentNode.setCdr(newNode);
                currentNode = newNode;
            }

            // Left parenthesis: Parse a sublist and return the root
            // of the subtree which is set as the car of the current node.
            // Otherwise, set the token as the data of the current node.
            if (tokenType == TokenType.LEFT_PAREN) {
                currentNode.setCar(parseList());
            }
            else {
                currentNode.setText(token.getText());
                currentNode.setType(token.getType());
            }

            // Get the next token for the next time around the loop.
            token = nextToken();
            tokenType = token.getType();
        }

        return root;  // of the parse tree
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

}
