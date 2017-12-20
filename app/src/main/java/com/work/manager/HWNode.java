package com.work.manager;

import java.util.ArrayList;
public class HWNode extends EventNode{
	ArrayList<DescriptionNode> description;
	TextNode SubmissionStatus;
	TextNode GradingStatus;
	TextNode DueDate;
	String link;
	HWNode(){
		this.ifHWNode = true;
		this.description = new ArrayList<>();
	}
	HWNode (String name,
			ArrayList<DescriptionNode> description,
			TextNode SubmissionStatus,
			TextNode GradingStatus,
			TextNode DueDate){
		this.ifHWNode = true;
		this.name = name;
		this.description = description;
		this.SubmissionStatus = SubmissionStatus;
		this.GradingStatus = GradingStatus;
		this.DueDate = DueDate;
	}
	@Override
	void accept(ITVisitor visitor) {
		visitor.VisitHWNode(this);
	}
	@Override
	void visit_children(ITVisitor visitor) {
		for (DescriptionNode node : description) node.accept(visitor);
	}
}
