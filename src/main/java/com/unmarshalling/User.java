package com.unmarshalling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)//the actual payload contain lots of fields so we need to mention allthe fields to this class or we need to add this annotation in order to ignore the rest of teh fields
public class User {
    public String login;
    public  int id;
    @JsonProperty("public_repos")//it is user if we want to put different name for the payload field
    public int publicRepos;

    public String getlogin(){return login;}
    public int getid(){return id;}
    public int getpublicRepos(){return publicRepos;}
}
