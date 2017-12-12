package com.work.manager;

import java.util.ArrayList;
import java.util.HashMap;
public class CourseNode extends ITNode{
	public ArrayList<DescriptionNode> description;
	public ArrayList<ActivityNode> activities;
	public HashMap<String,HWNode> deadlines;
	public HashMap<String,String> grades;
	CourseNode(String name, ArrayList<DescriptionNode> description, ArrayList<ActivityNode> activities){
		this.name = name;
		this.description = description;
		this.activities = activities;
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitCourseNode(this);
	}
	@Override
	void visit_children(ITVisitor visitor) {
		for (int i=0; i <description.size(); i++) description.get(i).accept(visitor);
		for (int i=0; i <activities.size(); i++) activities.get(i).accept(visitor);
	}
}
