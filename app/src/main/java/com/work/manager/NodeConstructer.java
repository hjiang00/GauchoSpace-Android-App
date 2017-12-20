package com.work.manager;

import android.os.AsyncTask;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

import static com.work.manager.NetworkUtils.cookie_retriever;
import static com.work.manager.NetworkUtils.course_grade_parser;
import static com.work.manager.NetworkUtils.dashboard_parser;
import static com.work.manager.NetworkUtils.activity_parser;
import static com.work.manager.NetworkUtils.homework_detail_parser;
import static com.work.manager.NetworkUtils.html_retriver;
import static com.work.manager.NetworkUtils.overview_grade_parser;
import static com.work.manager.TwoActivity.userNode;

public class NodeConstructer extends AsyncTask<String, Void, Void> {


    @Override
    protected Void doInBackground(String... params) {
        String cookie = cookie_retriever(params[0],params[1]);
        Document doc = html_retriver("https://gauchospace.ucsb.edu/courses/my/",cookie);
        dashboard_parser(doc,userNode);
        userNode.name = params[0];
        Document gradepage = html_retriver("https://gauchospace.ucsb.edu/courses/grade/report/overview/index.php",cookie);
        overview_grade_parser(gradepage,userNode);
        for (CourseNode course : userNode.courses){
            //set CourseNode ddl and grades
//            Document coursegradepage = html_retriver(course.gradepagelink,cookie);
//            course_grade_parser(coursegradepage,course);
            //create ActivityNode
            Document coursePage = html_retriver(course.link,cookie);
            activity_parser(coursePage,course);
            System.out.println("create ActivityNode finished");
        }
        for (CourseNode course : userNode.courses){
            for (ActivityNode activityNode : course.activities){
                for (EventNode event : activityNode.events){
                    if (event.ifHWNode == true){
                        HWNode hwNode = (HWNode)event;
                        Document hwPage = html_retriver(hwNode.link,cookie);
                        homework_detail_parser(hwPage,hwNode);
                    }
                }
            }
            System.out.println("create hwnode finished");
        }



        return null;
    }
}