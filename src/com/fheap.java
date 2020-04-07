package com;

import java.util.HashMap;

public class fheap {
    private String Max;
    private HashMap<String, node> hashtable;  //hashtable like a dictionary that help search whether a hashtag exists.
    public fheap(){
        this.hashtable=new HashMap<String, node>();
        this.Max=null;
    }

    public void insertintoheadlist(node p){
        node rightsible= this.hashtable.get(this.Max).getrightsibling();
        this.hashtable.get(Max).setrightsibling(p);
        p.setrightsibling(rightsible);
        p.setLeftsibling(hashtable.get(this.Max));
        rightsible.setLeftsibling(p);
    }

    public void insert(String hashtag, int frequency){
     //   System.out.println(hashtag);
        if(hashtable.containsKey(hashtag)){         //check whether the hashtag is included
            increasekey(hashtag,frequency);
        }else{
            node p= new node(hashtag,frequency);   //create new node
            p.setLeftsibling(p);
            p.setrightsibling(p);

            //insert this node into heap
            if(hashtable.isEmpty()){
                this.Max=hashtag;
            } else{
                if(frequency>hashtable.get(Max).getFrequency()){ //if the fre of new node is larger than max, new node will be max.
                     insertintoheadlist(p);
                     this.Max=hashtag;
                } else{
                     insertintoheadlist(p); }}
            this.hashtable.put(hashtag,p);  //put new node into hashtable

        }

        //System.out.println("right:"+hashtable.get(hashtag).getrightsibling().gethashtag());
    }
    public node makechild(HashMap<Integer,node> degreeTree,node n1, node n2){
        if(n1.getChild()==null){
            n2.setParent(n1);
            n1.setChild(n2);
        }else{
            node child = n1.getChild();
            n2.setParent(n1);
            n1.setChild(n2);
            n2.setrightsibling(child);
            n2.setLeftsibling(child.getleftsibling());
            child.setLeftsibling(n2);
        }

        //check there is a tree has the same degree as new tree
        if(degreeTree.containsKey(n1.getdegree())){
            node p=hashtable.get(n1.getdegree());
            degreeTree.remove(p);
            //combine 2 trees
            node newp;
            if(n1.getFrequency()>p.getFrequency()){
                newp=makechild(degreeTree,n1,p);
            }else {
                newp=makechild(degreeTree,p,n1);
            }
        }
        return n1;
    }


    public void meld(){
        HashMap<Integer, node> degreemap= new HashMap<Integer, node>();

        if(hashtable.isEmpty()){
            return;
        }
        if(hashtable.get(Max).getrightsibling()==null||hashtable.get(Max).getrightsibling()==hashtable.get(Max)){
            return;
        }

        degreemap.put(hashtable.get(Max).getdegree(),hashtable.get(Max));
        node begin=hashtable.get(Max).getrightsibling();

        begin=hashtable.get(Max).getrightsibling();

        while(begin!=null&&begin.gethashtag()!=hashtable.get(Max).gethashtag()){
            if(hashtable.containsKey(begin.getdegree())){
                node p=hashtable.get(begin.getdegree());
                degreemap.remove(p);
                //combine 2 trees
                node newp;
                if(begin.getFrequency()>p.getFrequency()){
                    newp=makechild(degreemap,begin,p);
                }else {
                    newp=makechild(degreemap,p,begin);
                }
                degreemap.put(newp.getdegree(),newp);
                begin=newp.getrightsibling();

            }else{
                degreemap.put(begin.getdegree(),begin);
                begin=begin.getrightsibling();
            }

        }

    }

    public node findMax(node p){ //find the maximum from list of node p
        if(p.getrightsibling()==null||p.getrightsibling()==p){   //only p in the list
            return p;
        }else {
            node max_node=p;
            node cur_node=p.getrightsibling();
            while(cur_node!=null&&cur_node!=p){
                //System.out.print(cur_node.gethashtag()+" "+cur_node.getFrequency());
                if(max_node.getFrequency()<cur_node.getFrequency()){
                    max_node=cur_node;
                }
                cur_node=cur_node.getrightsibling();
            }
            return max_node;
        }
    }



    public void removeMax(){
        if(hashtable.isEmpty()){
            return;
        } else {
           //find new max, find max in sibling of max and max of child. then compare
            //There are two situations: 1.Max in its sibling; 2. Max in its child
            //Also there are 4 situations:1.No sibling, No Child 2. No sibiling but child 3. sibling but no child 4.sibling and child
            String cur_max=Max;
            node cur_max_node=hashtable.get(cur_max);

            if(cur_max_node.getrightsibling()==null || cur_max_node.getrightsibling()==cur_max_node){
                if (cur_max_node.getChild() == null) {
                    return;
                }else{
                    Max=findMax(cur_max_node.getChild()).gethashtag();
                    //remove Max
                    node child =cur_max_node.getChild();
                    child.setParent(null);
                    cur_max_node.setChild(null);
                    hashtable.remove(cur_max_node.gethashtag());
                }
            }else{
//                if(hashtable.containsKey("sunday")){
//                    node n=hashtable.get("sunday");
//                    System.out.println(n.getFrequency());
//                    if(n.getParent()!=null){
//                        System.out.println("11111");
//                    }
//                }
                if(cur_max_node.getChild() == null){
                    node right=cur_max_node.getrightsibling();
                    right.setLeftsibling(cur_max_node.getleftsibling());
                    right.getleftsibling().setrightsibling(right);
                    hashtable.remove(cur_max_node.gethashtag());
                    Max=findMax(right).gethashtag();
                   // System.out.println("RemoveMin:"+Max);
                }else {
                    System.out.println("4");

                    node right=cur_max_node.getrightsibling();
                    node child=cur_max_node.getChild();
                    cur_max_node.setChild(null);
                    child.setParent(null);
                    right.getleftsibling().setrightsibling(child);
                    child.getleftsibling().setrightsibling(right);
                    node child_left=child.getleftsibling();
                    child.setLeftsibling(right.getleftsibling());
                    right.setLeftsibling(child_left);
                    Max=findMax(child).gethashtag();
                    hashtable.remove(cur_max_node.gethashtag());


                }

            }
//            if(hashtable.containsKey("sunday")){
//                node n=hashtable.get("sunday");
//                if(n.getParent()!=null){
//                    System.out.println("11111");
//                }
//            }


            //meld
            meld();
        }


    }






    public void removeKey(node p){
        node parent= p.getParent();
        p.setParent(null);
        parent.setChild(null);
        p.getleftsibling().setrightsibling(p.getrightsibling());
        p.getrightsibling().setLeftsibling(p.getleftsibling());
        insertintoheadlist(p);
        p.setChildcutstatus();
    }

    public void increasekey(String hashtag, int frequency){
        node p=hashtable.get(hashtag);
        if(hashtag.equals(Max)){
            hashtable.get(hashtag).increaseFrequency(frequency);
        }else if (p.getParent()==null){
            hashtable.get(hashtag).increaseFrequency(frequency);
            if(p.getFrequency()>hashtable.get(Max).getFrequency()){
                Max=hashtag;
            }
        }else if(p.getFrequency()+frequency<=p.getParent().getFrequency()){
            hashtable.get(hashtag).increaseFrequency(frequency);

        }else {
            p.increaseFrequency(frequency);
            node parent=p.getParent();
            removeKey(p);
            if(p.getFrequency()>hashtable.get(Max).getFrequency()){
                Max=hashtag;
            }
            if(!parent.getChildcutstatus()){
                parent.setChildcutstatus();
            }else{
                while(!parent.getChildcutstatus()){
                    removeKey(parent);
                    parent=parent.getParent();
                }
                parent.setChildcutstatus();
            }

        }

    }

    public node getMax() {
        return hashtable.get(Max);
    }

    public HashMap<String, node> getHashtable() {
        return hashtable;
    }
}
