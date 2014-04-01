import java.util.ArrayList;
import java.util.List;

public class Astar{
	public static List<State> open;   
	public static List<State> closed;
	
	public static void main(String[] args){
		open = new ArrayList<State>();
		closed = new ArrayList<State>();
		
		/* Starting State */
		State initialState = new State(0, 0, 6, new Tower(), null);
		open.add(initialState);
		System.out.println("Starting State:\n");
		initialState.display();
		while(true){
			/* fail check */
			if(open.size() == 0){
				System.out.println("Size of open: " + open.size());
				System.out.println("Failed");
				return;
			}
			
			/* check if goal state */
			if(open.get(0).h == 0){
				System.out.println("Found Solution");
				System.out.println("Path from goal to start:");
				displayPath(open.get(0));
				return;
			}
			/* state info */
			System.out.println("---------------------------------");
			System.out.println("Open List:");
			System.out.println("");
			System.out.println("State #\t\tg\th\tf");
			System.out.println("-------\t\t-\t-\t-");
			for(int i = 0; i < open.size(); i++){
				System.out.println("   " + open.get(i).stateNum + "\t\t" + open.get(i).g + "\t" + open.get(i).h + "\t" + open.get(i).f );
			}
			System.out.println("");
			
			/* remove top node and add to closed list */
			State currentState = open.get(0);
			open.remove(0);
			closed.add(currentState);
			System.out.println("Expanding state number: " + currentState.stateNum + "\n");
			System.out.print("Current State Tower while loop");
			currentState.tower.display();
			System.out.print("Closed Queue Tower: while loop");
			closed.get(0).tower.display();
			System.out.println("");
			genStates(currentState);
		}
	}
	/*State generator*/
	static void genStates(State state){
		int numGenStates = 0; // keep track of how many states were generated for the currentState
		
		for(int i = 0; i < 3; i++){
			if(state.tower.pegs.get(i).size() == 0){
				continue; }; //peg has no disks, can't move anything
			/* Iterate through remaining pegs for valid moves */
			for(int j = 0; j < 3; j++){
				if(i == j){
				continue; 
				}; // skip if it's the same peg
				
				/* check for valid moves */
				if(state.tower.pegs.get(j).size() == 0){
					System.out.print("Closed Queue Tower:      if");
					closed.get(0).tower.display();
					/* create move */
					Tower tower =  new Tower(state.tower);
					int disk = tower.pegs.get(i).get(0);
					tower.pegs.get(i).remove(0);
					tower.pegs.get(j).add(0, disk);
					/* calculate using heuristic */
					int g = state.g + disk;
					int h = 6 - tower.getSumOfPeg(2);
					/* check if tower configuration was used */
					if(!usedTower(tower)){
						/* Generate new state */
						State nState = new State(disk, g, h, tower, state);
						int insert = getInsertPos(nState);
						open.add(insert, nState);
						numGenStates++;
						/*Display State*/
						System.out.println("New State:\n---------");
						nState.display();
					}
					}else if(state.tower.pegs.get(i).isEmpty()){continue; }
					
					else if(state.tower.pegs.get(i).get(0) < state.tower.pegs.get(j).get(0)){
						/* create move */				
						System.out.print("tower = \t  ");
						state.tower.display(); 
						
						Tower tower =  new Tower(state.tower);
						int disk = tower.pegs.get(i).get(0);
						tower.pegs.get(i).remove(0);
						tower.pegs.get(j).add(0, disk);
						
						System.out.print("tower = s.t\t"); 
						tower.display();
						System.out.print("Closed Queue Tower: else if");
						closed.get(0).tower.display();
						/* calculate using heuristic */
						int g = state.g + disk;
						int h = 6 - tower.getSumOfPeg(2);
						/* check if tower configuration was used */
						if(!usedTower(tower)){							///NOT MAKING PAST ! usedTower!!!!!!!!!
							/* Generate new state */
							State nState = new State(disk, g, h, tower, state);
							int insert = getInsertPos(nState);
							open.add(insert, nState);
							numGenStates++;
							/*Display State*/
							System.out.println("New State:\n---------");
							nState.display();
						}else{continue; }
				}
			}
		}
		/* check if states were generated */
		if(numGenStates == 0){
			System.out.println("No generated states from state number: " + state.stateNum);
		}
	}
	
	static boolean usedTower(Tower tower){
		for(int i = 0; i < closed.size(); i++){
			/* check if tower is in closed list */
			if (towersEqual(tower, closed.get(i).tower)){
				System.out.println("Tower was used in closed list");
//				System.out.print("tower = \t   ");
//				tower.display(); 
//				System.out.print("closed.get(i).tower"); 
//				closed.get(i).tower.display();
//				System.out.println("");
				return true;
			}
		}
		for(int i = 0; i < open.size(); i++){	
			/* check if tower is in open list */
			if (towersEqual(tower, open.get(i).tower)){
				System.out.println("Tower was used in open list");
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
		State currentState = state; //current state
		while(currentState != null){
			currentState.display();
			currentState = currentState.pNode;
		}
	}
	
	/* Java's equals() method returns true iff the two objects are the same objects
	 *  this is a custom method to check if two towers are exactly the same. */
	public static boolean towersEqual(Tower tower1, Tower tower2){
		for(int i = 0; i < tower1.pegs.size(); i++){
			//check if the disks on the pegs are the same.
			for(int j = 0; j < tower2.pegs.get(i).size(); j++){
				if(tower1.pegs.get(i).get(j) != tower2.pegs.get(i).get(j)){
					return false;
				}
			}
		}
		return true;
	}
}
