package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Alice;
import chessLibrary.Bishop;
import chessLibrary.Chess;
import chessLibrary.MadHatter;

public class CustomChessTests {

	/* Test rules check method in Alice*/
	@Test
	public void aliceTests() throws Exception{
		Chess alice = new Alice(0,2,2);
		
		assert(alice.representive=='a');
		
		assert(alice.checkRulesForTest(0,3));
		assert(!alice.checkRulesForTest(0,2));
		assert(alice.checkRulesForTest(5,2));
		assert(alice.checkRulesForTest(3,4));
	}
	@Test
	public void madHatterTests() throws Exception{
		Chess madHatter0 = new MadHatter(0,5,5);
		Chess madHatter1 = new MadHatter(1,2,5);
		
		assert(madHatter0.representive=='m');
		assert(madHatter1.representive=='M');

		assert(madHatter0.checkRulesForTest(0,0));
		assert(!madHatter0.checkRulesForTest(0,2));
		assert(!madHatter1.checkRulesForTest(7,1));
		assert(madHatter1.checkRulesForTest(5,2));
	}

}
