package com.work.manager;

import java.net.URI;
public class WebNode extends DescriptionNode{
	URI uri;
	WebNode (){

	}
	WebNode (String name, URI uri){
		this.name  = name;
		this.uri = uri;
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitWebNode(this);
	}
	@Override
	void visit_children(ITVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
