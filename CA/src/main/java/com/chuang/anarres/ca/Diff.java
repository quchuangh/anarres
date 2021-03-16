package com.chuang.anarres.ca;

import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.StringKit;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Diff {

    public static void main(String[] args) throws IOException {
        Set<String> ignore = read("C:\\Users\\admin\\Desktop\\dns.txt", s -> s);
        for(int i = 1; i <= 25; i++) {
            Set<String> cvm = read("C:\\Users\\admin\\Desktop\\CVM\\CVM\\" + i + ".txt", s -> s);
            Set<String> ssl = read("C:\\Users\\admin\\Desktop\\CVM\\ssl\\" + i + ".txt", s -> s.substring(0, s.length() - 5));
            Set<String> all = new HashSet<>();
            all.addAll(ssl);
            all.addAll(ignore);
            write("C:\\Users\\admin\\Desktop\\CVM\\dif\\" + i + ".txt", CollectionKit.subtract(cvm, all, HashSet::new));
        }
    }

    private static void write(String filepath, Collection<String> lines) throws IOException {
        FileKit.touch(filepath);
        FileKit.writeLines(lines, filepath, "utf-8");
    }

    private static Set<String> read(String filepath, Function<String, String> map) throws IOException {
        if(!FileKit.isFile(filepath)) {
            return Collections.emptySet();
        }

        return FileKit.readLines(filepath, "utf-8").stream()
                .filter(StringKit::isNotBlank)
                .map(map)
                .collect(Collectors.toSet());
    }
}
