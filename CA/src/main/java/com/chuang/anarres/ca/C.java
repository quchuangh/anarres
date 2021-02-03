package com.chuang.anarres.ca;

import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.StringKit;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class C {

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();

        lines.addAll(FileKit.readLines("d://export0.log", "utf-8"));
        lines.addAll(FileKit.readLines("d://export.log", "utf-8"));

        Map<String, List<Line>> ipLineMap = lines.stream().filter(StringKit::isNotBlank).map(s -> {
            String[] strs = s.split(" ");
            Line line = new Line();
            line.setDate(strs[3]);
            line.setPath(strs[6]);
            String[] fi = strs[0].split(":");
            line.setFile(fi[fi.length - 2]);
            line.setIp(fi[fi.length - 1]);
            return line;
        }).collect(Collectors.groupingBy(Line::getIp));

        ipLineMap.forEach((s, lines1) -> {
            System.out.println("IP:-> " + s);
            lines1.forEach(line -> {
                System.out.println("时间:-> " + line.getDate() + ", 地址:" + line.getPath());
            });

            System.out.println("=================================\n\n\n\n");
        });
    }


    @Data
    static class Line {
        private String ip;
        private String file;
        private String date;
        private String path;
    }
}
