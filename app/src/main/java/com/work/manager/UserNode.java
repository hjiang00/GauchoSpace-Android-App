package com.work.manager;

import java.util.ArrayList;
public class UserNode extends ITNode{
	ArrayList<CourseNode> courses;
	UserNode(){
		this.courses = new ArrayList<>();
	}
	UserNode(String name, ArrayList<CourseNode> courses){
		this.name = name;
		this.courses = courses;
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitUserNode(this);	
	}

	@Override
	void visit_children(ITVisitor visitor) {
		for (CourseNode node : courses) node.accept(visitor);
	}

}
