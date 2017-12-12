package com.work.manager;

import java.util.ArrayList;
public class ActivityNode extends ITNode{
	ArrayList<EventNode> events;
	ActivityNode (String name, ArrayList<EventNode> events){
		this.name = name;
		this.events = events;
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitActivityNode(this);
	}

	@Override
	void visit_children(ITVisitor visitor) {
		for (int i=0; i<events.size(); i++) events.get(i).accept(visitor);
	}
}
