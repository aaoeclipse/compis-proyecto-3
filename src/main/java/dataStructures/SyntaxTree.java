package dataStructures;

import reader.DefaultValues;

/**
 * this tree is used to create a DFA from a regular expression
 */
public class SyntaxTree {
    private String regularExpression;
    private NodeSyntax<Integer> tree;

    public SyntaxTree(){regularExpression = "";}


    /**
     * returns the string with concat character in them
     * @param input is the given string in which it's going to add the concat between them
     * @return new String with the added concat
     */
    public String withConcat(String input){
        StringBuilder toReturn = new StringBuilder();
        boolean concat = false;
        for (int i = 0; i < input.length(); i++) {
            if (DefaultValues.STAR == (int) input.charAt(i)){
                toReturn.append((char) DefaultValues.STAR);
                continue;
            }
            else if (concat){
                if (DefaultValues.parentesis.contains((int) input.charAt(i))){
                    concat = false;
                    toReturn.append((char) DefaultValues.CONCAT);
                }
                else if (DefaultValues.ignore.contains((int) input.charAt(i)))
                    concat = true;
                else if (DefaultValues.availableChars.contains((int) input.charAt(i))) {
                    toReturn.append((char) DefaultValues.CONCAT);
                }
                else {
                    concat = false;
                }

            } else if (DefaultValues.availableChars.contains((int) input.charAt(i)) ){
                concat = true;
            } else if(DefaultValues.ignore.contains((int) input.charAt(i))){
                concat = true;
            } else{
                // it's a symbol
                concat = false;
            }
            toReturn.append(input.charAt(i));
        }
        toReturn.append((char)DefaultValues.EOF);

        regularExpression = toReturn.toString();
        return regularExpression;
    }

    public void toPostFix(){

    }

    /**
     * construct the tree with the regularExpression variable
     * in the class
     */
    public void constructTree(){
        // currNode
        NodeSyntax<Integer> currNode = new NodeSyntax<>();

        NodeSyntax<Integer> leftChild = new NodeSyntax<>();
        NodeSyntax<Integer> rightChild = new NodeSyntax<>();

        int position = 1;
        int c = 0;

        for (char character:this.regularExpression.toCharArray()) {
            c = (int) character;
            if ()
        }

    }

    // computing follow pos
//    public void followpos(){
//        for (Node n: tree) {
//            // n is cat-node with left child c1 and right child c2
//            if (n.equals(c1 concat c2))
//                for (i : lastpos(c1)) {
//                    followpos(i) = followpos(i) union firstpos(c2);
//                }
//            else if (n.starNode()){
//                for (int i: lastpos(n)) {
//                    followpos(i) = followpos(i) union firstpos(n);
//                }
//            }
//        }
//    }
//
//    // converting RE to DFA
//    public DFA reToDfa(){
//        // Dstates to contain only the unmarked state firstpos(n0) where n0 is the root of syntax tree T for (r)
//        while (there is an unmarked state S in Dstates){
//            makr S;
//            for (input symbol a: sigma) {
//                let U be the union of followpos(p);
//                for all p in S taht correspon to a;
//                if (U is not in Dstates)
//                    add U as an unmarked state to Dstates;
//                Dtran[S,a] = U;
//            }
//        }
//    }


}
