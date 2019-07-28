
/**
 * RunRuleNet.java
 * create a rule network & make deductions with it
 * @author Phil Green
 * This Version 23/1/2019
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class RunRuleNet{
 public static void main(String[] arg) {
   // create object for output
   EasyWriter scr = new EasyWriter();
   
   //make some rules
   
   //aunty
   ArrayList<String> aantes = new ArrayList<String>();
   aantes.add("?p parent of ?m");
   aantes.add("?a sister of ?p");
   RuleNode arule = new RuleNode("aunty rule",aantes,"?a aunty of ?m");
   ArrayList<RuleNode> asuccs = new ArrayList<RuleNode>();
   arule.setSuccessors(asuccs);

   //uncle
   ArrayList<String> uantes = new ArrayList<String>();
   uantes.add("?p parent of ?m");
   uantes.add("?a brother of ?p");
   RuleNode urule = new RuleNode("uncle rule",uantes,"?a uncle of ?m");
   ArrayList<RuleNode> usuccs = new ArrayList<RuleNode>();
   urule.setSuccessors(usuccs);
   
   //nephew
   ArrayList<String> nantes = new ArrayList<String>();
   nantes.add("?p parent of ?n");
   nantes.add("?au sibling of ?p");
   nantes.add("?n is male");
   RuleNode nrule = new RuleNode("nephew rule",nantes,"?n nephew of ?au");
   ArrayList<RuleNode> nsuccs = new ArrayList<RuleNode>();
   nrule.setSuccessors(nsuccs);
   
   //niece
   ArrayList<String> ncantes = new ArrayList<String>();
   ncantes.add("?p parent of ?n");
   ncantes.add("?au sibling of ?p");
   ncantes.add("?n is female");
   RuleNode ncrule = new RuleNode("niece rule",ncantes,"?n niece of ?au");
   ArrayList<RuleNode> ncsuccs = new ArrayList<RuleNode>();
   ncrule.setSuccessors(ncsuccs);
   
   //cousin
   ArrayList<String> cantes = new ArrayList<String>();
   cantes.add("?au parent of ?c");
   cantes.add("?md sibling of ?au");
   cantes.add("?md parent of ?m");
   RuleNode crule = new RuleNode("cousin rule",cantes,"?c cousin of ?m");
   ArrayList<RuleNode> csuccs = new ArrayList<RuleNode>();
   crule.setSuccessors(csuccs);
   
   //sister
   ArrayList<String> ssantes = new ArrayList<String>();
   ssantes.add("?s sibling of ?sa");
   ssantes.add("?s is female");
   RuleNode ssrule = new RuleNode("sister-is-sibling rule",ssantes,"?s sister of ?sa");
   ArrayList<RuleNode> sssuccs = new ArrayList<RuleNode>();
   sssuccs.add(arule);
   ssrule.setSuccessors(sssuccs);
   
   //brother
   ArrayList<String> bsantes = new ArrayList<String>();
   bsantes.add("?s sibling of ?sa");
   bsantes.add("?s is male");
   RuleNode bsrule = new RuleNode("brother-is-sibling rule",bsantes,"?s brother of ?sa");
   ArrayList<RuleNode> bssuccs = new ArrayList<RuleNode>();
   bssuccs.add(urule);
   bsrule.setSuccessors(bssuccs);
   
   //sibling
   ArrayList<String> santes = new ArrayList<String>();
   santes.add("?p parent of ?ba");
   santes.add("?p parent of ?bb");
   RuleNode srule = new RuleNode("sibling rule",santes,"?ba sibling of ?bb");
   ArrayList<RuleNode> ssuccs = new ArrayList<RuleNode>();
   ssuccs.add(bsrule);
   ssuccs.add(ssrule);
   ssuccs.add(crule);
   ssuccs.add(nrule);
   ssuccs.add(ncrule);
   srule.setSuccessors(ssuccs);
   
   //grandfather
   ArrayList<String> gfantes= new ArrayList<String>();
   gfantes.add("?gf father of ?p");
   gfantes.add("?p parent of ?c");
   RuleNode gfrule = new RuleNode ("grandfather rule",gfantes, "?gf grandfather of ?c");
   ArrayList<RuleNode> gfsuccs = new ArrayList<RuleNode>();
   gfrule.setSuccessors(gfsuccs);
   
   
   //grandmother
   ArrayList<String> gmantes= new ArrayList<String>();
   gmantes.add("?gm mother of ?p");
   gmantes.add("?p parent of ?c");
   RuleNode gmrule = new RuleNode ("grandmother rule",gmantes, "?gm grandmother of ?c");
   ArrayList<RuleNode> gmsuccs = new ArrayList<RuleNode>();
   gmrule.setSuccessors(gmsuccs);
   
   
   //father-is-parent
   ArrayList<String> fpantes = new ArrayList<String>();
   fpantes.add("?f father of ?c");
   RuleNode fprule = new RuleNode ("father-is-parent rule", fpantes, "?f parent of ?c");
   ArrayList<RuleNode> fpsuccs = new ArrayList<RuleNode>();
   fpsuccs.add(gfrule);
   fpsuccs.add(gmrule);
   fpsuccs.add(srule);
   fpsuccs.add(crule);
   fpsuccs.add(urule);
   fpsuccs.add(arule);
   fpsuccs.add(nrule);
   fpsuccs.add(ncrule);
   fprule.setSuccessors(fpsuccs);
   
   
   //mother-is-parent
   ArrayList<String> mpantes = new ArrayList<String>();
   mpantes.add("?f mother of ?c");
   RuleNode mprule = new RuleNode ("mother-is-parent rule", mpantes, "?f parent of ?c");
   ArrayList<RuleNode> mpsuccs = new ArrayList<RuleNode>();
   mpsuccs.add(gfrule);
   mpsuccs.add(gmrule);
   mpsuccs.add(srule);
   mpsuccs.add(crule);
   mpsuccs.add(arule);
   mpsuccs.add(urule);
   mpsuccs.add(nrule);
   mpsuccs.add(ncrule);
   mprule.setSuccessors(mpsuccs);
   
   //father-is-male
   ArrayList<String> fmantes = new ArrayList<String>();
   fmantes.add("?f father of ?c");
   RuleNode fmrule = new RuleNode ("father-is-male rule", fmantes, "?f is male");
   ArrayList<RuleNode> fmsuccs = new ArrayList<RuleNode>();
   fmsuccs.add(bsrule);
   fmsuccs.add(nrule);
   fmrule.setSuccessors(fmsuccs);
   
   //mother-is-female
   ArrayList<String> mfantes = new ArrayList<String>();
   mfantes.add("?f mother of ?c");
   RuleNode mfrule = new RuleNode ("mother-is-female rule", mfantes, "?f is female");
   ArrayList<RuleNode> mfsuccs = new ArrayList<RuleNode>();
   mfsuccs.add(ssrule);
   mfsuccs.add(ncrule);
   mfrule.setSuccessors(mfsuccs);

   
   
   // the set of rulenodes
   ArrayList<RuleNode> rset = new ArrayList<RuleNode>();
   rset.add(fprule);
   rset.add(gfrule);
   rset.add(mprule);
   rset.add(gmrule);
   rset.add(srule);
   rset.add(bsrule);
   rset.add(ssrule);
   rset.add(crule);
   rset.add(nrule);
   rset.add(urule);
   rset.add(arule);
   rset.add(fmrule);
   rset.add(mfrule);
   rset.add(ncrule);
   
   // make the rule net
   RuleNet rs = new RuleNet(rset);
   //initialise it - set up initial tokens
   rs.initialise(); 
   
   //add facts
   ArrayList<String> facts = new ArrayList<String>();
   //rs.addFact("H7 father of H8");
   //rs.addFact("H8 father of E");
   long startTime = System.currentTimeMillis();
   rs.addFact("Jill mother of David");
   rs.addFact("Jill mother of Shula");
   rs.addFact("David father of Pip");
   rs.addFact("Shula mother of Daniel");
   long stopTime = System.currentTimeMillis();
   scr.println("compute time (ms) " + (stopTime-startTime));
   //rs.addFact("Jill parent of David");
   //rs.addFact("Jill parent of Shula");
   //rs.addFact("David is male");
   //rs.addFact("Shula is female");
   
   //rs.addFact("David father of Pip");
   //rs.addFact("Shula mother of Daniel");
   //rs.addFact("Shula sibling of David");
   //rs.addFact("Daniel is male");
   //rs.addFact("Pip is female");

   //rs.addFact("David sibling of Shula");
   
   //rs.addFact("David sibling of Shula");
   //rs.addFact("David father of Pip");
   //rs.addFact("Shula mother of Daniel");
  }
}