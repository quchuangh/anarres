package com.chuang.anarres.ca;


import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.StringKit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckDomain {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        List<String> domains = inputDomains();
        System.out.println("======================(" + domains.size() + ")\n\n\n\n\n\n");
        for (int i = 0; i < domains.size(); i++) {
            hand(domains.get(i), i );
        }
        System.out.println("============done");
    }

    private static List<String> fileDomains() throws IOException {
        return FileKit.readLines("C:\\Users\\admin\\Desktop\\all-domains.txt", "utf-8");
    }

    private static void hand(String domain, int idx) {
        try {
            InetAddress.getByName(domain).getHostAddress();
//            System.out.println("domain: [" + domain + "], ip: " + ip);
        } catch (UnknownHostException e) {
            System.out.println(domain + "                                                         ->" + idx);
        }
    }

    private static List<String> inputDomains() {
        List<String> inputs = new ArrayList<>();
        do {
            System.out.print("请输入域名, 多个域名以(逗号，空格，回车)隔开, 按n结束:");
            String line = input.next();
            if("n".equalsIgnoreCase(line)) {
                break;
            }
            inputs.add(line);
        } while(true);

        return inputs.stream()
                .flatMap(SSLGenerator::toStream)
                .filter(StringKit::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
    }

}
