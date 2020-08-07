package com.shawn.functionalprogramming;

import java.util.List;

/** 艺术家 个人/团队
 * @author ysq
 * @date 2020/7/13
 */
public class Artist {

    /** 名称  eg：甲壳虫乐队*/
    private String name;
    /** 成员 eg：约翰·列侬 */
    private List<String> members;
    /** 来自哪里 eg：利物浦*/
    private String origin;

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getOrigin() {
        return origin;
    }
}
