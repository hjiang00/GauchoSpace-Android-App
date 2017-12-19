package com.work.manager;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NetworkUtils {
    public static String cookie_retriever(String myusername, String mypassword){
        try{
            Connection.Response res = Jsoup.connect("https://gauchospace.ucsb.edu/courses/login/index.php")
                    .data("username", myusername, "password", mypassword,"anchor","")
                    .method(Connection.Method.POST)
                    .execute();
            System.out.println(res.statusCode());
            String cookie = res.cookie("MoodleSessiontng28new");
            System.out.println(cookie);
            return cookie;
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    public static Document html_retriver(String uri, String Cookie){
        try{
            Document doc = Jsoup.connect(uri)
                    .cookie("MoodleSessiontng28new", Cookie)
                    .get();
            Log.d("finished2","html_retriever");
            return doc;
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
            Document doc2 = Jsoup.parse("<html><head><title>First parse</title></head>"
                    + "<body><p>Parsed HTML into a doc.</p></body></html>");
            System.out.println("error");
            return doc2;
        }
    }

    public static void dashboard_parser(Document doc){
        try{
            Elements contents = doc.getElementsByAttributeValue("class","course_title");
            for (Element content : contents) {
                Elements links = content.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    System.out.println(linkText);
                    System.out.println(linkHref);
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void activity_parser(Document doc){
        try{
            Elements activities = doc.getElementsByAttributeValue("class","section main clearfix");
            for (Element activity : activities) {
                Elements activity_name = activity.getElementsByAttributeValue("class","sectionname");
                for (Element name : activity_name) { System.out.println(name.text());} //week activity name
                Elements events = activity.getElementsByAttributeValue("class","activityinstance");
                for (Element event : events){
                    Elements event_links = event.getElementsByTag("a");
                    for (Element link : event_links) {
                        String linkHref = link.attr("href");
                        System.out.println(linkHref);
                    }
                    Elements event_names = event.getElementsByAttributeValue("class","instancename");
                    for (Element name : event_names) {
                        Elements homeworks = name.getElementsByAttributeValue("class","accesshide ");
                        Boolean ishomework = false;
                        for (Element homework : homeworks) {
                            if (homework.ownText().equals("Assignment")){ishomework = true;}
                        }
                        String eventname = name.ownText();
                        if(ishomework){
                            System.out.println(eventname);
                        }
                        else{
                            System.out.println(eventname);
                        }
                    }
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void course_grade_parser(Document doc){
        try{
            Elements items = doc.getElementsByTag("tbody");
            for (Element item : items) {
                Elements value_strings = item.getElementsByTag("tr");
                for (Element value_string : value_strings){
                    String value = value_string.text();
                    System.out.println(value);
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void overview_grade_parser(Document doc){
        try{
            Elements course_grades_wrap = doc.getElementsByAttributeValue("id","overview-grade");
            for (Element course_grades : course_grades_wrap) {
                Elements course_grade_page = course_grades.getElementsByAttributeValue("class","cell c0");
                for (Element content : course_grade_page) {
                    Elements links = content.getElementsByTag("a");
                    for (Element link : links) {
                        String linkHref = link.attr("href");
                        String linkText = link.text();
                        System.out.println(linkText);
                        System.out.println(linkHref);
                    }
                }
                Elements course_grade = course_grades.getElementsByAttributeValue("class","cell c1");
                for (Element grade : course_grade) {
                    String grade_string = grade.text();
                    if(grade_string != null && !grade_string.isEmpty()) {System.out.println(grade_string);}
                }
            }

        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void homework_detail_parser(Document doc){
        try{
            Elements items = doc.getElementsByTag("tbody");
            for (Element item : items) {
                Elements value_blocks = item.getElementsByTag("tr");
                for (Element value_block : value_blocks){
                    Elements value_strings = value_block.getElementsByAttributeValue("class","cell c0");
                    for (Element value_string : value_strings) {
                        String value = value_string.text();
                        if (value.equals("Submission status")){
                            String submit_stat = value_block.text().replace("Submission status ","");
                            System.out.println(submit_stat);
                        }
                        if (value.equals("Due date")){
                            String due_date = value_block.text().replace("Due date ","");
                            System.out.println(due_date);
                        }
                    }
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

//    public static String uri_get_title(Document doc){
//        try{
//            String title = doc.title();
//            return title;
//
//        }catch (Exception e){
//            String errorMessage = e.getMessage();
//            System.out.println(errorMessage);
//            System.out.println("error in get title");
//            return errorMessage;
//        }
//    }

}

