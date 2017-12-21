package com.work.manager;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import static com.work.manager.TwoActivity.coursemap;
import static com.work.manager.TwoActivity.coursetitle;

public class NetworkUtils {
    public static ArrayList<String> coursename = new ArrayList<>();
    public static ArrayList<String> coursename2 = new ArrayList<>();
    public static ArrayList<String> hwname = new ArrayList<>();
    public static ArrayList<Integer> hwcount = new ArrayList<>();
    public static ArrayList<String> hwdeadlines = new ArrayList<>();
    public static ArrayList<String> hwdetail = new ArrayList<>();
    public static ArrayList<String> coursegrade = new ArrayList<>();
    public static int count;

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

    public static void dashboard_parser(Document doc,UserNode userNode){
        try{
            Elements contents = doc.getElementsByAttributeValue("class","course_title");
            for (Element content : contents) {
                Elements links = content.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    System.out.println(linkText);
                    System.out.println(linkHref);
                    userNode.courses.add(new CourseNode(linkText,linkHref));
                    coursename.add(linkText);
                    coursetitle.add(linkText);
                    coursemap.put(linkText, linkHref);
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void activity_parser(Document doc,CourseNode coursenode){
        try{
            Elements activities = doc.getElementsByAttributeValue("class","section main clearfix");
            for (Element activity : activities) {
                Elements activity_name = activity.getElementsByAttributeValue("class","sectionname");
                for (Element name : activity_name) {
                    ActivityNode activityNode = new ActivityNode(name.text());
                    coursenode.activities.add(activityNode);
                } //week activity name
                Elements events = activity.getElementsByAttributeValue("class","activityinstance");
                Iterator<ActivityNode> activityNodeIteratorIter = coursenode.activities.iterator();
                for (Element event : events){
                    WebNode webNode = new WebNode();
                    Elements event_links = event.getElementsByTag("a");
                    for (Element link : event_links) {
                        String linkHref = link.attr("href");
                        webNode.uri = URI.create(linkHref);
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
                        webNode.name = eventname;
                        if(activityNodeIteratorIter.hasNext()){

                            if(ishomework){
                                count++;
                                HWNode hwnode = new HWNode();
                                hwnode.name = eventname;
                                hwnode.link = webNode.uri.toString();
                                hwnode.description.add(webNode);
                                System.out.println(eventname);
                                hwname.add(eventname);
                                activityNodeIteratorIter.next().events.add(hwnode);
                            }
                            else{
                                SlidesNode slidesNode = new SlidesNode();
                                slidesNode.name = eventname;
                                slidesNode.description.add(webNode);
                                System.out.println(eventname);
                                activityNodeIteratorIter.next().events.add(slidesNode);
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void homework_detail_parser(Document doc,HWNode hwNode){
        try{
            Elements items = doc.getElementsByTag("tbody");
            for (Element item : items) {
                Elements value_keys = item.getElementsByTag("tr");
                for (Element value_key : value_keys){
                    Elements key_strings = value_key.getElementsByAttributeValue("class","cell c0");
                    for (Element key_string : key_strings){
                        switch (key_string.text()){
                            case "Submission status":
                                hwNode.SubmissionStatus = new TextNode(key_string.nextElementSibling().text());
                                System.out.println(key_string.nextElementSibling().text());
                                hwdetail.add(hwNode.SubmissionStatus.name);
                                break;
                            case "Grading status":
                                hwNode.GradingStatus = new TextNode(key_string.nextElementSibling().text());
                                System.out.println(key_string.nextElementSibling().text());
                                break;
                            case "Due date":
                                if(key_string.nextElementSibling().text() != null && !key_string.nextElementSibling().text().isEmpty())  hwNode.DueDate = new TextNode(key_string.nextElementSibling().text());
                                else hwNode.DueDate = new TextNode("N/A");
                                System.out.println(hwNode.DueDate.name);
                                hwdeadlines.add(hwNode.DueDate.name);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void overview_grade_parser(Document doc,UserNode userNode){
        try{
            Elements course_grades_wrap = doc.getElementsByAttributeValue("id","overview-grade");
            for (Element course_grades : course_grades_wrap) {
                Elements course_grade_page = course_grades.getElementsByAttributeValue("class","cell c0");
                for (Element content : course_grade_page) {
                    Elements links = content.getElementsByTag("a");
                    Iterator<CourseNode> courseNodeIterator = userNode.courses.iterator();
                    for (Element link : links) {
                        if(courseNodeIterator.hasNext()) {
                            String linkHref = link.attr("href");
                            String linkText = link.text();
                            courseNodeIterator.next().gradepagelink = linkHref;
                            System.out.println(linkText);
                            System.out.println(linkHref);
                            coursename2.add(linkText);
                        }
                    }
                }
                Elements course_grade = course_grades.getElementsByAttributeValue("class","cell c1");
                for (Element grade : course_grade) {
                    String grade_string = grade.text();
                    if(grade_string != null && !grade_string.isEmpty()) {
                        System.out.println(grade_string);
                        coursegrade.add(grade_string);
                    }
                }
            }

        }catch (Exception e){
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void course_grade_parser(Document doc,CourseNode courseNode){
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
}

