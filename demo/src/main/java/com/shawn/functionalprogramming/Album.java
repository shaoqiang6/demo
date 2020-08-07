package com.shawn.functionalprogramming;

import java.util.List;

/** 专辑
 * @author ysq
 * @date 2020/7/13
 */
public class Album {

    /** 专辑名称 */
    private String name;
    /** 专辑中的多个曲目*/
    private List<Track> tracks;
    /** 参加创作的艺术家列表 */
    private List<String> musicians;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<String> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<String> musicians) {
        this.musicians = musicians;
    }
}
