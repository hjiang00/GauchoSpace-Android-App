package com.work.manager;

abstract public class ITNode {
	String name;
	abstract void accept(ITVisitor visitor);
	abstract void visit_children(ITVisitor visitor);
}
