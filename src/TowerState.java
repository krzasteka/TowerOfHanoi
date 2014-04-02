
public class TowerState {
	public int stateNum,
               g,
               h,
               f;
	public TowerState pNode;     // parent node
	public Tower tower;     // tower coinciding with with current state 
	public int[] config;    // current state configuration
	static int lState = 1;  // # of the last state
	
	public TowerState(int mDisk, int g, int h, Tower t, TowerState state){
		this.g = g;
		this.h = h;
		f = g + h;
		tower = t;
		pNode = state;
		//mDisk = disk which was moved, getDiskPeg(n) returns the peg of the associated disk
		config = new int[] { mDisk, g, h, t.getDiskPeg(1), t.getDiskPeg(2), t.getDiskPeg(3) };
		stateNum = lState;
		lState++;
	}
	
	/** Display the current state **/
	public void display(){
		System.out.println("State Number: " + stateNum);
		System.out.println("f = " + f + " | " + "g = " + g + " | " + "h = " + h);
		System.out.println("Current State: " + "{" + config[1] + "} {" + config[2] + "} {" + config[3] + "} {" + config[4] + "} {" + config[5] + "}");
		
		for(int i = 2; i >= 0; i--){
			for(int j = 0; j < 3; j++){
				System.out.println("\t");
				if(tower.pegs.get(j).size() > i)
					System.out.print(" " + tower.pegs.get(j).get(tower.pegs.get(j).size() - i - 1) + " ");
				else
					System.out.print("   ");
			}
		    System.out.println("");
		}
		System.out.println("---\t---\t---");
		System.out.println(" A \t B \t C " + "\n");
		System.out.println("");
	}
}
