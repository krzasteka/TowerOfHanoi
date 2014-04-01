import java.util.ArrayList;


public class Tower {
	public ArrayList<ArrayList<Integer>> pegs; //List array to represent the pegs
	
	@SuppressWarnings("serial")
	/** Initialize the Start State Tower TOWER(PEG 1 (1, 2, 3), PEG 2 (null), PEG 3 (null) ); **/
	public Tower(){
		pegs = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> peg1 = new ArrayList<Integer>(){{add(1);
															add(2);
															add(3);
															}};
        ArrayList<Integer> peg2 = new ArrayList<Integer>();
        ArrayList<Integer> peg3 = new ArrayList<Integer>();
        pegs.add(peg1);
        pegs.add(peg2);
        pegs.add(peg3);
	};
	
//	public Tower(ArrayList<Integer> a, ArrayList<Integer> b,ArrayList<Integer> c){
//		pegs = new ArrayList<ArrayList<Integer>>();
//		pegs.add(a);
//		pegs.add(b);
//		pegs.add(c);
//	}
	/* Copy Constructor */
	public Tower(Tower tower){
		pegs = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> peg1 = new ArrayList<Integer>();
		peg1 = tower.pegs.get(0);
		ArrayList<Integer> peg2 = new ArrayList<Integer>();
		peg2 = tower.pegs.get(1);
		ArrayList<Integer> peg3 = new ArrayList<Integer>();
		peg3 = tower.pegs.get(2);
		pegs.add(peg1);
        pegs.add(peg2);
        pegs.add(peg3);
	}
	
	/* getDiskPeg returns the peg that the disk is currently on */
	public int getDiskPeg(int disk){
		for(int i = 0; i < pegs.size(); i++){
			for(int j = 0; j < pegs.get(i).size(); j++){
				if (pegs.get(i).get(j) == disk)
					return i+1;
			}
		}
		return -1; // default return statement ( -1 for error) 
	}
	
	/* getSumOfPeg returns the sum of the disks that are currently on a given peg*/
	public int getSumOfPeg(int peg){
		int sum = 0;
		for(int i = 0; i < pegs.get(peg).size(); i++){
			sum += pegs.get(peg).get(i);
		}
		return sum;
	}
	/* display current tower configuration */
	public void display(){
    	for(int i = 0; i < pegs.size(); i++){ 
    		System.out.print(pegs.get(i) + " ");
    	}
    	System.out.print("\n");
    }
	
	/* Java's equals() method returns true iff the two objects are the same objects
	 *  this is a custom method to check if two towers are exactly the same. */
	public static boolean towersEqual(Tower tower1, Tower tower2){
		for(int i = 0; i < tower1.pegs.size(); i++){
			//check if the disks on the pegs are the same.
			for(int j = 0; j < tower1.pegs.get(i).size(); j++){
				if(tower1.pegs.get(i).get(j) != tower2.pegs.get(i).get(j)){
					return false;
				}
			}
		}
		return true;
	}
	
//	@SuppressWarnings("serial")
//	public static void main(String[] args){
//		Tower t1 = new Tower();
//		ArrayList<Integer> peg1 = new ArrayList<Integer>(){{add(2);
//		add(3);
//		}};
//		ArrayList<Integer> peg2 = new ArrayList<Integer>(){{add(1);
//		}};
//		ArrayList<Integer> peg3 = new ArrayList<Integer>(){};
//		Tower t2 = new Tower(peg1, peg2, peg3);
//		
//		System.out.print(towersEqual(t1, t2));
//		
//		t1.display();
//		t2.display();
//	}
}
