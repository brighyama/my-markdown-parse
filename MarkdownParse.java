// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        
        // verify that the open-close pattern is appropriate

        //Test2-file.md
        //We are correctly reading opening bracket
        //We then read the closing bracket but that takes us to the very end 
        //Then we look for an open paren but there are no more open paren
        //This results in an infinite loop (which is the error we are gett)

        int currentIndex = 0;
        int nextOpenBracket = 0;
        int nextCloseBracket = markdown.indexOf("]");
        int openParen = markdown.indexOf("(");
        int closeParen = 0;
        
        while(currentIndex < markdown.length()) {
            if (nextCloseBracket > openParen){break;}
            nextOpenBracket = markdown.indexOf("[", currentIndex);
            nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            openParen = markdown.indexOf("(", nextCloseBracket);
            closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}