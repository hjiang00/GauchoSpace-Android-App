package com.work.manager;

import android.os.AsyncTask;
import org.jsoup.nodes.Document;

import static com.work.manager.NetworkUtils.cookie_retriever;
import static com.work.manager.NetworkUtils.dashboard_parser;
import static com.work.manager.NetworkUtils.html_retriver;

public class NodeConstructer extends AsyncTask<String, Void, String> {

    private Document doc;

    @Override
    protected String doInBackground(String... params) {
        String cookie = cookie_retriever(params[0],params[1]);
        Document doc = html_retriver("https://gauchospace.ucsb.edu/courses/my/",cookie);
        dashboard_parser(doc);
        return cookie;
    }
}