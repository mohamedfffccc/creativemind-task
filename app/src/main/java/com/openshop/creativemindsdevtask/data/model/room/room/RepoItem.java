package com.openshop.creativemindsdevtask.data.model.room.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public
class RepoItem {
    @PrimaryKey(autoGenerate = true)


    int i;
    String repo_name;
    String repo_desc;
    String owner_username;
    String html_url;
    String owner_htmlurl;

    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public String getRepo_desc() {
        return repo_desc;
    }

    public void setRepo_desc(String repo_desc) {
        this.repo_desc = repo_desc;
    }

    public String getOwner_username() {
        return owner_username;
    }

    public void setOwner_username(String owner_username) {
        this.owner_username = owner_username;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getOwner_htmlurl() {
        return owner_htmlurl;
    }

    public void setOwner_htmlurl(String owner_htmlurl) {
        this.owner_htmlurl = owner_htmlurl;
    }

    public RepoItem(String repo_name, String repo_desc, String owner_username, String html_url, String owner_htmlurl) {
        this.repo_name = repo_name;
        this.repo_desc = repo_desc;
        this.owner_username = owner_username;
        this.html_url = html_url;
        this.owner_htmlurl = owner_htmlurl;
    }
}
