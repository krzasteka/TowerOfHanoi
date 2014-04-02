import java.util.ArrayList;


public class Tower implements Cloneable {
	public ArrayList<ArrayList<Integer>> pegs; //List array to represent the pegs
	
	/** Initialize the Start State Tower TOWER(PEG 1 (1, 2, 3), PEG 2 (null), PEG 3 (null) ); **/
	public Tower(){
		this.pegs = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> peg1 = new ArrayList<Integer>();
		peg1.add(1);
		peg1.add(2);
		peg1.add(3);
        ArrayList<Integer> peg2 = new ArrayList<Integer>();
        ArrayList<Integer> peg3 = new ArrayList<Integer>();
        pegs.add(peg1);
        pegs.add(peg2);
        pegs.add(peg3);
	};

	/* Java uses references so we need to create a whole new *
	 * object with the passed towers values                  */
	
	public Tower(Tower tower){
		this.pegs = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> peg1 = new ArrayList<Integer>();
		ArrayList<Integer> peg2 = new ArrayList<Integer>();
        ArrayList<Integer> peg3 = new ArrayList<Integer>();
        pegs.add(peg1);
        pegs.add(peg2);
        pegs.add(peg3);
		for(int i = 0 ; i < tower.pegs.size(); i++){
			if(tower.pegs.get(i).size() != 0){
				for(int j = 0; j < tower.pegs.get(i).size(); j++){
					this.pegs.get(i).add(tower.pegs.get(i).get(j)); 
				}
			}
		}
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
}
