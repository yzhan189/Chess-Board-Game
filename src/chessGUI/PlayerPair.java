package chessGUI;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/* Class for each pair of player*/

public class PlayerPair {
	
	Tuple<String,String> PlayerPairNames;
	Tuple<Integer,Integer> PlayerPairScores;
	
	public PlayerPair(String player0Name, String player1Name) {
		
		/*create new distinct names for this pair of players*/
		PlayerPairNames = new Tuple<String,String>(player0Name,player1Name);
		
		PlayerPairScores = new Tuple<Integer,Integer>(0,0);
		
	}
	
	/* increment score for the winner*/
	public void incrementScore(int winner) {

		if (winner == 0)
			PlayerPairScores = new Tuple<Integer,Integer>(
				PlayerPairScores.getA()+1,PlayerPairScores.getB());
		if (winner == 1)
			PlayerPairScores = new Tuple<Integer,Integer>(
				PlayerPairScores.getA(),PlayerPairScores.getB()+1);
	
	}
	
	/* returns string of score history*/
	public static String getScores(Vector<PlayerPair> playerPairs) {
		String ret = "";
		
		PlayerPair currPlayerPair;
	    Iterator<PlayerPair> i = playerPairs.iterator();
	    
	    while (i.hasNext()) {
	    		currPlayerPair = i.next();
	    		
	    		ret += currPlayerPair.PlayerPairNames.getA()+"   VS   "
	    				+currPlayerPair.PlayerPairNames.getB()
	    				+"\n       "
	    				+ currPlayerPair.PlayerPairScores.getA()
	    				+ "                    "
	    				+currPlayerPair.PlayerPairScores.getB()+"\n\n";
	    		
	    }
	    
		return ret;
	}

	
	
    /* helper function to generate unique names*/
    public static String generateName() {
        String SALTCHARS = 
        		"ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    

}
