package lexer;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
/**
 *  This class is used to manage the source program input stream;
 *  each read request will return the next usable character; it
 *  maintains the source column position of the character
*/
public class SourceReader {
    private BufferedReader source;
    private int lineno = 0,   // line number of source program
                position;     // position of last character processed
    private boolean isPriorEndLine = true;  // if true then last character read was newline
                             // so read in the next line
    private String nextLine;
    private Queue<String> linesRead  = new LinkedList<String>();


/**
 *  Construct a new SourceReader
 *  @param sourceFile the String describing the user's source file
 *  @exception IOException is thrown if there is an I/O problem
*/
    public SourceReader(String sourceFile) throws IOException {
        source = new BufferedReader(new FileReader(sourceFile));
    }

    void close() {
        try {
            source.close();
        } catch (Exception e) {}
    }

/**
 *  read next char; track line #, character position in line<br>
 *  return space for newline
 *  @return the character just read in
 *  @IOException is thrown for IO problems such as end of file
*/
    public char read() throws IOException {
        if (isPriorEndLine) {
            lineno++;
            position = -1;
            nextLine = source.readLine();

            // Add current line to linesRead Queue, and include the line number
            // with proper indentation.
            if (lineno < 100) {
                if (lineno < 10)
                    linesRead.add("  "+lineno+". "+nextLine);
                else
                    linesRead.add(" "+lineno+". "+nextLine);
            }
            
            //if (nextLine != null) {
            //    System.out.println("READLINE:   "+nextLine);
            //}
            isPriorEndLine = false;
        }
        if (nextLine == null) {  // hit eof or some I/O problem
            throw new IOException();
        }
        if (nextLine.length() == 0) {
            isPriorEndLine = true;
            return ' ';
        }
        position++;
        if (position >= nextLine.length()) {
            isPriorEndLine = true;
            return ' ';
        }
        return nextLine.charAt(position);
    }

/**
 *  @return the position of the character just read in
*/
    public int getPosition() {
        return position;
    }

/**
 *  @return the line number of the character just read in
*/
    public int getLineno() {
        return lineno;
    }

    public String getLinesRead() {
        String lines="";
        for (String line : linesRead)
            lines+=line+'\n';
        return lines;
    }
}