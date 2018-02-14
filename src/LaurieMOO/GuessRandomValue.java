/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaurieMOO;

import java.util.Arrays;

/**
 *
 * @author josephrunser
 */
public class GuessRandomValue {
    
    private final static java.util.Random RND_GEN = new java.util.Random();
    private final static int NUM_MAX = 9999;
    private final static int MAX_LNG = 4;
    private static short littleMooCount = 0;
    private static short bigMooCount = 0;
    private static int answer;
    
    // Will give a random answer.
    public void setAnswer(){
        
        answer = RND_GEN.nextInt(NUM_MAX);
 
    }
    
    // If an answer is within the range then it will be placed as the official
    // answer.
    public void setAnswer(int possibleAnswer) {
                
        if(possibleAnswer >= 0 && possibleAnswer <= NUM_MAX) { answer = possibleAnswer; }
        
    }
    
    // Retuns the answer as a string
    public String getAnswer() {
        
        return convertToString(answer);
        
    }
    
    // Returns the bigMooCount
    public int getBigMooCount(int guess) {
        mooCount(guess);
        return bigMooCount;
    }
    
    // Returns the littleMooCount
    public int getLittleMooCount(int guess) {
        mooCount(guess);
        return littleMooCount;
    }
    
    // If the guess is the same as the answer then it will return true and set the
    // mooCount's to reflect that. If the guess in incoret then it will propegate
    // a the mooCount's and return false.
    public boolean isCorrectGuess(int guess) {
        
        if (guess == answer) {
            littleMooCount = 0;
            bigMooCount = 4;
            return true;
        }
        else {
            mooCount(guess);
            return false;
        }
        
    }
    
    // Propogates the mooCount's according to the rules of the game.
    private void mooCount(int guess) {
        
        boolean[] hasAnswer = new boolean[MAX_LNG];
        char[] tmpChrAnswer = new char[MAX_LNG];
        char[] tmpChrGuess = new char[MAX_LNG];
        
        Arrays.fill(hasAnswer, Boolean.FALSE);
        Arrays.fill(tmpChrAnswer, '0');
        Arrays.fill(tmpChrGuess, '0');
        
        littleMooCount = 0;
        bigMooCount = 0;
        
        char tmpCharOne;
        char tmpCharTwo;
        
        tmpChrAnswer = (convertToString(answer)).toCharArray();
        tmpChrGuess  = (convertToString(guess)).toCharArray();
        
        
        
        // This will increment bigMooCount if there are any big moos
        for(int i = 0; i < MAX_LNG; i++) {
            
            tmpCharOne = tmpChrAnswer[i];
            
            for(int j = 0; j < MAX_LNG; j++) {
                
                tmpCharTwo = tmpChrGuess[j];
                
                // Checks for charecter equivalance AND position equivalance AND
                // if the loaction alread has an answer.
                if(tmpCharOne == tmpCharTwo && i == j && !hasAnswer[i]) {
                    bigMooCount++;
                    hasAnswer[i] = Boolean.TRUE;
                    tmpChrAnswer[i] = '-';
                    tmpChrGuess[j] = '-';
                    break;
                }
                
            }
        }
        
        // This will increment littleMooCount if there are any little moos
        for(int i = 0; i < MAX_LNG; i++) {
            
            tmpCharOne = tmpChrAnswer[i];
            
            for(int j = 0; j < MAX_LNG; j++) {
                
                tmpCharTwo = tmpChrGuess[j];
                
                // Checks for charecter equivalance AND position equivalance AND
                // if the loaction alread has an answer.
                if(tmpCharOne == tmpCharTwo && i != j && !hasAnswer[i]) {
                    littleMooCount++;
                    hasAnswer[i] = Boolean.TRUE;
                    tmpChrAnswer[i] = '-';
                    tmpChrGuess[j] = '-';
                    break;
                }
                
            }
        }
    }
    
    // A specialized convert to string method that makes sure int value is within
    // the confines of the game.
    private String convertToString(int value) {
        
        String tmpStr;
        
        if (value >= 1000) { tmpStr = value + ""; }
        else if(value < 1000 && value > 100){ tmpStr = "0" + value; }
        else if(value < 100 && value > 10){ tmpStr = "00" + value; }
        else if(value < 10 && value > 0){ tmpStr = "000" + value; }
        else { tmpStr = "0000" + value; }
        
        return tmpStr;
    }
        
}
