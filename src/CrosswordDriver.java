import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This program is used to help solve crossword puzzles!
 * Enter a word with the blanks filled in with question marks,
 * and the program will check the dictionary for similar words.
 *
 * EXAMPLE:
 * Looking for a word that satisfies the given: A _ _ L E for the puzzle.
 * Enter into the program a??le and it will output:
 *
 * Matching words:
 * agile aisle amble ample angle
 * ankle apple
 *
 */
public class CrosswordDriver {

    public static void main(String[] args) throws IOException{
        ArrayList<String> dictionary = new ArrayList<>();
        ArrayList<String> dicMatches;

        File file = new File("english.0");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){
            dictionary.add(scanner.next().toLowerCase());
        }

        System.out.print("Please enter a word with '?' for unknown letters: ");
        Scanner input = new Scanner(System.in);

        //Replace '?' letters with '[\w]'
        String word = input.next().toLowerCase().replaceAll("[?]", "[\\\\w]");

        //Checks for matching words and appends them to an array list
        dicMatches = findMatches(word,dictionary);

        //Iterate through array list of matches and print results.
        printMatches(dicMatches);
    }

    public static ArrayList<String> findMatches(String word, ArrayList<String> dictionary){
        ArrayList<String> matches = new ArrayList<>();

        Pattern r = Pattern.compile(word);

        //Iterates through the dictionary, if a match is found, append it to matches
        for(String check : dictionary){
            Matcher m = r.matcher(check);
            if(m.matches()){
                matches.add(check);
            }
        }

        return matches;
    }

    public static void printMatches(ArrayList<String> list){
        System.out.println("Matching words:");
        if(list.isEmpty()){
            System.out.println("No matches.");
            return;
        }

        //Makes the output not look terrible
        for(String matchingWord : list){
            System.out.print(matchingWord + " ");
            if((list.indexOf(matchingWord) + 1)%5 == 0){
                System.out.println("");
            }
        }
    }

}