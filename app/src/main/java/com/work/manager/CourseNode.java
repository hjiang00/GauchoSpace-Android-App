package com.work.manager;

import java.util.ArrayList;
import java.util.HashMap;
public class CourseNode extends ITNode{
	public String link;
	public String gradepagelink;
	public ArrayList<ActivityNode> activities;
	public HashMap<String,String> grades;
	CourseNode(String name, String link){
		this.name = name;
		this.link = link;
		this.activities = new ArrayList<>();
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitCourseNode(this);
	}
	@Override
	void visit_children(ITVisitor visitor) {
		for (int i=0; i <activities.size(); i++) activities.get(i).accept(visitor);
	}
}
