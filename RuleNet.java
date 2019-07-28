/**
 * RuleNet.java
 * A rule net for forward chaining
 * @author Phil Green
 * This Version 23/1/2019
 */

 import java.util.*;
 import java.io.*;
 import sheffield.*;

 public class RuleNet {

 	private ArrayList<RuleNode> rulenodes;        // the nodes in the network
 	private ArrayList<String> factsToPropagate; //facts not yet propagated
 	private ArrayList<ArrayList<RuleNode>> targetNodeLists; //each element is list of nodes which corresponding fact should be propagated to
 	EasyWriter scr = new EasyWriter();

 	/**
 	 * constructor is given the RuleNodes in the network
 	 */
 	public RuleNet (ArrayList<RuleNode> a){  //constructor is given the RuleNodes
 		rulenodes = a;
 		}

 	/**
 	 * accessor for the RuleNodes
 	 */
    public ArrayList<RuleNode> getRules() {return rulenodes;}

    /**
	 * Initialise all the RuleNodes
	 * to contain a single token: all antecedents, empty context
	 */
    public void initialise() { //initialise the rules - set initial tokens

    for (RuleNode r: rulenodes){
    	r.initialise(); //set initial tokens in each node
        factsToPropagate=new ArrayList<String>(); //set toPropagate & targetNodes empty
        targetNodeLists = new ArrayList<ArrayList<RuleNode>>();
        }
    }
    /**
     * add a fact and make all the deductions
     * by calling self.propagate
     */

    public void addFact(String f){ // add a fact & make all deductions
      factsToPropagate.add(f);
      targetNodeLists.add(rulenodes); //propagate to all nodes
      propagate();
    }

    /**
     * drive the propagation of facts
     * take next fact from factsToPropagate
     * take the list of nodes to propagate it to from targetNodeLists
     * do this using the node's propagate method
     * if this results in deductions, then for each deduction
     *    print it out
     *    add it to factsToPropagate
     *    add the rulenode's successors list to targetNodeLists
     * continue until no more facts to propagate
     */
    public void propagate(){ //drive the propagation of facts
    	while(factsToPropagate.size() > 0) {
    		
	    	//Getting facts and rules to propagate against
	    	String latestFact = factsToPropagate.get(factsToPropagate.size()-1);
	    	ArrayList<RuleNode> rules = targetNodeLists.get(targetNodeLists.size()-1);
	    	
	    	//Deleting from the lists (popping)
	    	factsToPropagate.remove(factsToPropagate.size()-1);
	    	targetNodeLists.remove(targetNodeLists.size()-1);
	    	
	    	//calls propagate() for each rule
	    	//Adds deductions to factsToPropagate
	    	//Adds successors of the rule to targetsNodeLists
	    	//Calls propagate when we add a new fact
	    	for(RuleNode rule : rules) {
	    		rule.propagate(latestFact);
	    		if (rule.getDeductions().size() > 0) {
	    			for(String deduction : rule.getDeductions()) {
	    				System.out.println("Deduction: "+deduction);
	    				factsToPropagate.add(deduction);
	    				targetNodeLists.add(rule.getSuccessors());
	    				propagate();
	    			}
	    		}
	    	}
    	}
    	
    }
}



