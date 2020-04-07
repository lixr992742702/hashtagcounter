package com;

public class node {
    //Attributes of nodes: hashtag, frequency, parent node, child nodes, degree, leftsibling, rightsibling, childcutstatus
    private String hashtag;
    private int frequency;
    private node parent;
    private node child;
    private int degree;
    private boolean isroot;
    private node leftsibling;
    private node rightsibling;
    private boolean childcutstatus;

    //operation
    public node(String hashtag,int frequency){   //constructor
        this.hashtag=hashtag;
        this.frequency=frequency;
        this.childcutstatus=false;
        this.leftsibling=null;
        this.rightsibling=null;
        this.parent=null;
        this.child=null;
        this.degree=0;
        this.isroot=true;
    }

    public void increaseFrequency(int frequency) {
        this.frequency =this.frequency+frequency;
    }

    public int getFrequency(){
        return this.frequency;
    }

    public String gethashtag(){
        return this.hashtag;
    }

    public void setParent(node p){
        this.parent=p;
    }

    public node getParent(){
        return this.parent;
    }

    public void setChild(node p){
        this.child=p;
    }

    public node getChild(){
        return this.child;
    }

    public void increasedegree(){
        this.degree++;
    }

    public void decreasedegree(){
        this.degree--;
    }

    public int getdegree(){
        if(isroot==false){
            return 0;
        }else if(child==null){
            return 0;
        }else{
            int num=1;
            node firstchild= child;
            node p=child.getrightsibling();
            while(p!=firstchild || p!=null){
                num++;
                p=p.getrightsibling();
            }
            return num;
        }
    }

    public void setLeftsibling(node p){
        this.leftsibling=p;
    }

    public void removeleftsibling(){
        if(this.leftsibling!=null){
            this.leftsibling=this.leftsibling.leftsibling;
        }else{
            this.leftsibling=null;
        }

    }

    public node getleftsibling(){
        return this.leftsibling;
    }

    public void setrightsibling(node p){
        this.rightsibling=p;
    }

    public boolean haverightsibling(){
        if(this.rightsibling==null){
            return false;
        }
        return true;
    }

    public void removerightsibling(){
        if(this.rightsibling!=null){
            this.rightsibling=this.rightsibling.rightsibling;
        }else{
            this.rightsibling=null;
        }
    }

    public node getrightsibling(){
        return this.rightsibling;
    }

    public void setChildcutstatus(){
        this.childcutstatus=true;
    }

    public boolean getChildcutstatus(){
        return this.childcutstatus;
    }

    public boolean comparefrequency(node p){
        if(this.frequency>p.getFrequency()){
            return true;
        }
        return false;
    }

    public boolean getisroot(){
        return this.isroot;
    }

    public void changeisroot(){
        if(this.isroot==true){
            this.isroot=false;
        }else{
            this.isroot=true;
        }
    }

}
