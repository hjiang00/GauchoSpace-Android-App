package com.work.manager;

public class TextNode extends DescriptionNode{
	TextNode(String name) {this.name = name;}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitTextNode(this);	
	}

	@Override
	void visit_children(ITVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
