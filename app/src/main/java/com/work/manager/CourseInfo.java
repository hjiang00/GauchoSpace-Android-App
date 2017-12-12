package com.work.manager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nh54762 on 12/10/2017.
 */

public class CourseInfo {
    ArrayList<EventInfo> events= new ArrayList<>();
    public HashMap<String,HWNode> deadlines = new HashMap<>();
    public HashMap<String,String> grades = new HashMap<>();
}
