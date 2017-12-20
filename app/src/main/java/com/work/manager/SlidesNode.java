package com.work.manager;

import java.util.ArrayList;
public class SlidesNode extends EventNode{
    ArrayList<DescriptionNode> description;
    SlidesNode(){
        this.ifHWNode = false;
        this.description = new ArrayList<>();
    }
    SlidesNode (String name){
        this.ifHWNode = false;
        this.name = name;
    }
    @Override
    void accept(ITVisitor visitor) {
        visitor.VisitSlidesNode(this);
    }
    @Override
    void visit_children(ITVisitor visitor) {
        for (DescriptionNode node : description) node.accept(visitor);
    }
}