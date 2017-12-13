package com.work.manager;

import java.util.HashMap;

public class ITVisitor {
	static HashMap<String, CourseInfo> courses = new HashMap<>();
	String currentWeek;
	EventInfo currentEventInfo;
	CourseInfo currentCourseInfo;
	void indent() { currentEventInfo.description += "	";}
	void nextline() { currentEventInfo.description += "\n";}
	ITVisitor (UserNode ivtree){ ivtree.accept(this); }
	//HashMap<String, CourseInfo> Get_Info() { return courses; }
	void VisitActivityNode (ActivityNode node){
		currentWeek = node.name;
		node.visit_children(this);
	}
	void VisitCourseNode (CourseNode node){
		currentCourseInfo = new CourseInfo();
		courses.put(node.name,currentCourseInfo);
		node.visit_children(this);
	}
	void VisitHWNode (HWNode node){
		currentEventInfo = new EventInfo();
		currentEventInfo.weekid = currentWeek;
		currentCourseInfo.deadlines.put(node.name, node);
		currentEventInfo.description = node.name;
		nextline(); indent();
		currentEventInfo.description += "Due: "+ node.DueDate.name;
		nextline(); indent();
		currentEventInfo.description += "Submission Status: "+ node.SubmissionStatus.name;
		nextline(); indent();
		currentEventInfo.description += "Grading Status" + node.GradingStatus.name;
		nextline(); indent();
		node.visit_children(this);
		currentCourseInfo.events.add(currentEventInfo);
	}
	void VisitTextNode (TextNode node){currentEventInfo.description += node.name;}
	void VisitWebNode (WebNode node){currentEventInfo.description +=  node.name; currentEventInfo.links.put(node.name,node.uri.toString());}
	void VisitUserNode (UserNode node){ node.visit_children(this);}
}
