package com.mycompany.jsontodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class figureTrans {
    public String name;
    public String description;
    public int s;
    public String shape;
    public int x;
    public int y;
    public String code;
    public String saveName;
    public Map<String,Integer> links;
    public Map<String,Map<String,String>> varLinks;
    public Map<Integer,String> inVar = new HashMap<>();;
    public Map<Integer,String> outVar = new HashMap<>();;
    public int currentIndex;
    public figureTrans(String name,String description,int s,String shape,int x,int y,
            String code,List<String> inVar,List<String> outVar,
            Map<String,Integer> links, Map<String,Map<String,String>> varLinks, String saveName){
        this.name = name;
        this.description = description;
        int kkey = 0;
        if (outVar != null) {
            for (String ov : outVar){
                this.outVar.put(kkey, ov);
                kkey +=1;
            }
        }
        this.s = s;
        this.shape = shape;
        if (inVar != null) {
            for (String iv : inVar){
                this.inVar.put(kkey, iv);
                kkey +=1;
            }
        }
        this.x = x;
        this.y = y;
        this.code = code;
        this.saveName = saveName;
        this.links = links;
        this.varLinks = varLinks;
    }
    public void setIndex(int index){
        this.currentIndex = index;
    }
}