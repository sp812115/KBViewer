package com.koubou.kbviewer.entity;

import java.util.List;

public class VideoSource {
    String name;
    String version;
    String description;

    public VideoSource(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static int versionCompare(VideoSource a,VideoSource b){
        String [] aversions= a.getVersion().split(".");
        String [] bversions= b.getVersion().split(".");
        int length=aversions.length>bversions.length?aversions.length:bversions.length;
        for(int i=0;i<length;i++){
            if(Integer.parseInt(aversions[i])>Integer.parseInt(bversions[i]))
                return 1;
            else if(Integer.parseInt(aversions[i])>Integer.parseInt(bversions[i]))
                return -1;
        }
        if(aversions.length>bversions.length)
            return 1;
        else if (aversions.length<bversions.length)
            return -1;
        return 0;
    }

    public List<Video> getNormalVideos(){
        return null;
    }

    public Video getDetailVideo(){
        return null;
    }

    public List<Episode> getNormalEpisodes(){
        return null;
    }

    public Episode getDetailEpisode(){
        return null;
    }



}
