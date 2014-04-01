import java.util.ArrayList;

public class Astar extends Tower{
	public static ArrayList<State> open;   
	public static ArrayList<State> closed;
	
	public static void main(String[] args){
		open = new ArrayList<State>();
		closed = new ArrayList<State>();
		
		/* Starting State */
		State sState = new State(0, 0, 6, new Tower(), null);
		open.add(sState);
		//System.out.println("Starting State:\n");
		sState.display();
		System.out.println("Open Queue Size Before While Loop: " + open.size());
		open.get(0).tower.display();
		while(true){
			/* fail check */
			if(open.size() == 0){
				//System.out.println("Size of open: " + open.size()); 
				//System.out.println("Failed");
				return;
			}
			
			/* check if goal state */
			if(open.get(0).h == 0){
				//System.out.println("Found Solution");
				//System.out.println("Path from goal to start:");
				displayPath(open.get(0));
				return;
			}
			/* state info */
			//System.out.println("---------------------------------");
			//System.out.println("Open List:");
			//System.out.println("");
			//System.out.println("State #\t\tg\th\tf");
			//System.out.println("-------\t\t-\t-\t-");
			for(int i = 0; i < open.size(); i++){
				//System.out.println("   " + open.get(i).stateNum + "\t\t" + open.get(i).g + "\t" + open.get(i).h + "\t" + open.get(i).f );
			}
			//System.out.println("");
			
			/* remove top node and add to closed list */
			State cState = open.get(0);
			System.out.print("cState tower config");
			cState.tower.display();
			System.out.println("");
			open.remove(0);
			closed.add(cState);
			
			//System.out.println("Expanding state number: " + cState.stateNum + "\n");
			cState.display();
			genStates(cState);
		}
	}
	/*State generator*/
	static void genStates(State state){
		int numGenStates = 0; // keep track of how many states were generated for the currentState
		
		for(int i = 0; i < 3; i++){
			if(state.tower.pegs.get(i).size() == 0){
				//System.out.println("checking state.tower.pegs.get(i).size() == 0 " + state.tower.pegs.get(i).size());
				continue; }; //peg has no disks, can't move anything
			/* Iterate through remaining pegs for valid moves */
			for(int j = 0; j < 3; j++){
				if(i == j){
				//System.out.println("Checking if pegs are equal i == j ???? " + (j == i));
				//System.out.println("j = " + j + "  |  i = " + i);
				continue; 
				}; // skip if it's the same peg
				
				/* check for valid moves */
				state.tower.display();
				int bool1 = state.tower.pegs.get(j).size();
			    //System.out.println("i = " + i + " | j = " + j);
				//System.out.println( bool1 + " " );
				if(i == 1 && j == 2){
					
					//System.out.println("i = 1, j = 2 IF STATEMENT ");
					//System.out.println("size of i = 1, j = 2: " + state.tower.pegs.get(i));
					//System.out.println("size of i = 1, j = 2 TO STRING: " + state.tower.pegs.get(j).toString());
					int k = state.tower.pegs.get(j).get(0);
					//System.out.println("k: " + k);
					
				}
				if(state.tower.pegs.get(j).isEmpty() || state.tower.pegs.get(j) == null || state.tower.pegs.get(j).size() == 0){
					//System.out.println("If is empty i = " + i + " | j = " + j);
					//System.out.println("state.tower.pegs.get(j).size() == 0 is " + state.tower.pegs.get(j).size());	
					//System.out.println("state.tower.pegs.get(i).get(0) is " + state.tower.pegs.get(i).get(0));
					state.tower.display();
					/* create move */
					Tower tower =  new Tower(state.tower);
					System.out.print("new Tower(State.tower): ");
					tower.display();
					System.out.println("");
					int disk = tower.pegs.get(i).get(0);
					System.out.println("Value of Disk = " + disk);
					System.out.println("i = " + i + " and j = "+ j);
					System.out.println("Open Queue: (size): " + open.size());
					for(int z = 0; z < open.size() ; z++){
						open.get(z).tower.display();
					}
					System.out.println("Closed Queue: (size): " + closed.size());
					for(int q = 0; q < closed.size() ; q++){
						closed.get(q).tower.display();
					}
					tower.pegs.get(i).remove(0);
					tower.pegs.get(j).add(0, disk);
					/* calculate using heuristic */
					int g = state.g + disk;
					int h = 6 - tower.getSumOfPeg(2);
					/* check if tower configuration was used */
					System.out.println("RIGHT BEFORE !used tower");
					tower.display();
					if(!usedTower(tower)){
						System.out.println("Inside !used tower");
						/* Generate new state */
						State nState = new State(disk, g, h, tower, state);
						int insert = getInsertPos(nState);
						open.add(insert, nState);
						numGenStates++;
						/*Display State*/
						//System.out.println("New State:\n---------");
						nState.display();
					}
					}else if(state.tower.pegs.get(i).isEmpty()){continue; }
					
					else if(state.tower.pegs.get(i).get(0) < state.tower.pegs.get(j).get(0)){
						//System.out.println("Else if tower peg < tower peg i = " + i + " | j = " + j);
						//System.out.println("state.tower.pegs.get(j).size() == 0 is " + state.tower.pegs.get(j).size());	
						//System.out.println("state.tower.pegs.get(i).get(0) is " + state.tower.pegs.get(i).get(0));
						//System.out.println("state.tower.pegs.get(j).get(0) is " + state.tower.pegs.get(j).get(0));
						state.tower.display();
						/* create move */
						Tower tower =  new Tower(state.tower);
						System.out.print("new Tower(State.tower): ");
						tower.display();
						System.out.println("");
						int disk = tower.pegs.get(i).get(0);
						System.out.println("Value of Disk = " + disk);
						tower.pegs.get(i).remove(0);
						tower.pegs.get(j).add(0, disk);
						/* calculate using heuristic */
						int g = state.g + disk;
						int h = 6 - tower.getSumOfPeg(2);
						/* check if tower configuration was used */
						System.out.println("RIGHT BEFORE !used tower");
						tower.display();
						if(!usedTower(tower)){							///NOT MAKING PAST ! usedTower!!!!!!!!!
							System.out.println("Inside !used tower");
							/* Generate new state */
							State nState = new State(disk, g, h, tower, state);
							int insert = getInsertPos(nState);
							open.add(insert, nState);
							numGenStates++;
							/*Display State*/
							//System.out.println("New State:\n---------");
							nState.display();
						}else{continue; }
				}
			}
		}
		/* check if states were generated */
		if(numGenStates == 0){
			//System.out.println("No generated states from state number: " + state.stateNum);
		}
	}
	
	static boolean usedTower(Tower tower){
		for(int i = 0; i < closed.size(); i++){
			/* check if tower is in closed list */
			if (towersEqual(tower, closed.get(i).tower)){
				//System.out.println("Tower was used in closed list");
				return true;
			}
		}
		for(int i = 0; i < open.size(); i++){	
			/* check if tower is in open list */
			if (towersEqual(tower, open.get(i).tower)){
				//System.out.println("Tower was used in open list");
				return true;
			}
		}
		/*if tower not found return false */
		return false;
	}
	
	static int getInsertPos(State state){
		/* check if open list is empty, if it is insert at [0] */
		if(open.size() == 0){
			return 0;
		}
		
		/* if open list is not empty find insert position */
		for(int i = 0; i < open.size(); i++){
			if(state.f < open.get(i).f){
				return i;
			}
		}
		return open.size();
	}
	
	/*Display path for current state*/
	static void displayPath(State state){
		State cState = state; //current state
		while(cState != null){
			cState.display();
			cState = cState.pNode;
		}
	}
}
