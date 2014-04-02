import java.util.ArrayList;
import java.util.List;

public class Astar{
	public static List<TowerState> open;   
	public static List<TowerState> closed;
	
	public static void main(String[] args){

//		Tower t1 = new Tower();
//		Tower t2 = new Tower(t1);
//		t2.pegs.remove(0);
//		System.out.println(towersEqual(t1, t2));
		
		open = new ArrayList<TowerState>();
		closed = new ArrayList<TowerState>();
		
		/* Starting State */
		TowerState initialState = new TowerState(0, 0, 6, new Tower(), null);
		open.add(initialState);
		System.out.println("Starting State:\n");
		initialState.display();
		while(true){
			System.out.println("");
			System.out.println("************************");
			System.out.println("Beginning of WHILE loop!");
			System.out.println("************************");
			System.out.println("");
			/* fail check */
			if(open.size() == 0){
				System.out.println("Size of open: " + open.size());
				System.out.println("Failed");
				return;
			}
			
			/* check if goal state */
			if(open.get(0).h == 0){
				System.out.println("************************");
				System.out.println("************************");
				System.out.println("****Found Solution******");
				System.out.println("************************");
				System.out.println("************************");
				//System.out.println("Path from goal to start:");
				//displayPath(open.get(0));
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
			TowerState currentState = open.get(0);
			open.remove(0);
			closed.add(currentState);
			System.out.println("Expanding state number: " + currentState.stateNum + "\n");
			System.out.println("");
			genStates(currentState);
		}
	}
	/*State generator*/
	static void genStates(TowerState state){
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
						TowerState nState = new TowerState(disk, g, h, tower, state);
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
						Tower tower =  new Tower(state.tower);
						int disk = tower.pegs.get(i).get(0);
						tower.pegs.get(i).remove(0);
						tower.pegs.get(j).add(0, disk);
						/* calculate using heuristic */
						int g = state.g + disk;
						int h = 6 - tower.getSumOfPeg(2);
						/* check if tower configuration was used */
						if(!usedTower(tower)){							///NOT MAKING PAST ! usedTower!!!!!!!!!
							/* Generate new state */
							TowerState nState = new TowerState(disk, g, h, tower, state);
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
				return true;
			}
		}
		for(int i = 0; i < open.size(); i++){	
			/* check if tower is in open list */
		
				if (towersEqual(tower, open.get(i).tower)){
					return true;
				}
			
		}
		/*if tower not found return false */
		return false;
	}
	
	static int getInsertPos(TowerState state){
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
	static void displayPath(TowerState state){
		TowerState currentState = state; //current state
		while(currentState != null){
			currentState.display();
			currentState = currentState.pNode;
		}
	}
	
	/* Java's equals() method returns true iff the two objects are the same objects
	 *  this is a custom method to check if two towers are exactly the same. */
	public static boolean towersEqual(Tower tower1, Tower tower2){
		
		
		for(int i = 0; i < tower1.pegs.size(); i++){
			if(tower1.pegs.get(i).size() != tower2.pegs.get(i).size()){
				return false;
			}
			//check if the disks on the pegs are the same.
			for(int j = 0; j < tower1.pegs.get(i).size(); j++){
				if(tower1.pegs.get(i).get(j) != tower2.pegs.get(i).get(j)){
					return false;
				}
			}
		}
		return true;
	}
}
