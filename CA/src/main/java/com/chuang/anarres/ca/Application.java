package com.chuang.anarres.ca;

import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.StringKit;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 安装 snap
 * sudo yum install epel-release -y
 * sudo yum install snapd -y
 * sudo systemctl enable --now snapd.socket
 * sudo ln -s /var/lib/snapd/snap /snap
 * sudo snap install core; sudo snap refresh core
 *
 * 安装 certbot
 * sudo snap install --classic certbot
 * sudo ln -s /snap/bin/certbot /usr/bin/certbot
 *
 * 申请证书
 * sudo certbot --nginx  //申请证书，并配置nginx
 * sudo certbot certonly --nginx //申请证书，手动配置nginx
 *
 * 更新证书
 * sudo certbot renew --dry-run
 *
 * 失败提示：DNS problem
 *
 */
public class Application {
    private final static Scanner input = new Scanner(System.in);

    public static String path = Application.class.getResource("").getPath() + "/ssl/";

    public static final int BATCH = 300;

    public static final String inputData;

    static {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 150; i++) {
            s.append("1\r\n");
        }
        inputData = s.toString();
    }

    public static void main(String[] args) {

        int offset = batch();
        String dir = dir();

        Set<String> domains = inputDomains();
        boolean with3W = withWWW();

        System.out.println("您要申请证书的域名如下：");
        domains.forEach(s -> {
            if(with3W) {
                System.out.println(s + ", www." + s);
            } else {
                System.out.println(s);
            }
        });

        continueOrExit();

        List<List<String>> sets = new ArrayList<>();

        List<String> itemList = new ArrayList<>();
        sets.add(itemList);
        for(String domain: domains) {
            if(itemList.size() >= BATCH) {
                itemList = new ArrayList<>();
                sets.add(itemList);
            }
            itemList.add(domain);
        }

        for (int i = 0; i < sets.size(); i++) {
            hand(sets.get(i), dir, offset + i, with3W);
        }


        System.out.println("nohup ./run.sh < input.data &");
        System.out.println("程序结束");
    }

    private static void hand(List<String> domains, String dir, int batch, boolean with3w) {
        StringBuilder cmd = new StringBuilder();
        for (int i = 0; i < domains.size(); i++) {
            String domain = domains.get(i);
            cmd.append("echo \"=======================[").append(domain).append(":(").append(i).append(")]============================S\"").append("\n");
            if(with3w) {
                cmd.append("certbot certonly  --nginx -d ").append(domain).append(" -d www.").append(domain).append("\n");
            } else {
                cmd.append("certbot certonly --nginx -d ").append(domain).append("\n");
            }
            cmd.append("echo \"=======================[").append(domain).append(": (").append(i).append(")]============================E\"").append("\n\n\n");

            try {
                File file = FileKit.touch(path + dir + "/" + batch + "/nginx/" + domain + ".conf");

                FileKit.writeUtf8String(NGINX_CONFIG.replaceAll("\\$\\{\\{}}", domain), file);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        cmd.append("echo \"done!!!\"");
        try {
            FileKit.writeUtf8String(cmd.toString(), path + dir + "/" + batch + "/run.sh");
            FileKit.writeUtf8String(inputData, path + dir + "/" + batch + "/input.data");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }


    private static void continueOrExit() {
        System.out.print("请确认是否继续 (Y)es, (N)o:");
        String line = input.next();
        if("no".equalsIgnoreCase(line) || "n".equalsIgnoreCase(line)) {
            System.out.println("程序退出");
            System.exit(0);
        }
    }

    private static Set<String> inputDomains() {
        List<String> inputs = new ArrayList<>();
        do {
            System.out.print("请输入域名, 多个域名以(逗号，空格，回车)隔开, 按n结束:");
            String line = input.next();
            if("n".equalsIgnoreCase(line)){
                break;
            }
            inputs.add(line);
        } while(true);

        return inputs.stream()
                .flatMap(Application::toStream)
                .filter(StringKit::isNotBlank)
                .collect(Collectors.toSet());
    }

    public static Stream<String> toStream(String s) {
        return Stream.of(
                s.replaceAll("\r", "")
                        .replaceAll("\n", ",")
                        .replaceAll(" ", ",")
                        .replaceAll("\t", ",")
                        .split(",")
        );
    }

    private static boolean withWWW() {
        System.out.print("是否同时添加www二级域名 (Y)es, (N)o:");
        String line = input.next();
        return "yes".equalsIgnoreCase(line) || "y".equalsIgnoreCase(line);
    }

    private static String dir() {
        System.out.print("请输入路径:");
        return input.next();
    }

    private static int batch() {
        System.out.print("请输入批次:");
        return input.nextInt();
    }


    private final static String NGINX_CONFIG = "server {\n" +
            "    listen 80;\n" +
            "    server_name ${{}} www.${{}};\n" +
            "    return 301 https://www.${{}}$request_uri;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "server {\n" +
            "    listen  443 ssl http2;\n" +
            "    listen [::]:433 ssl http2;\n" +
            "    server_name ${{}} www.${{}};\n" +
            "\n" +
            "    ssl_certificate            /etc/letsencrypt/live/${{}}/fullchain.pem;\n" +
            "    ssl_certificate_key        /etc/letsencrypt/live/${{}}/privkey.pem;\n" +
            "    ssl_session_timeout        5m;\n" +
            "\n" +
            "#    location / {\n" +
            "#        root   /usr/share/nginx/html;\n" +
            "#        index  index.html index.htm;\n" +
            "#    }\n" +
            "\n" +
            "    charset utf-8;\n" +
            "\n" +
            "    location / {\n" +
            "      proxy_set_header        Host $host:$server_port;\n" +
            "      proxy_set_header        X-Real-IP $remote_addr;\n" +
            "      proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;\n" +
            "      proxy_set_header        X-Forwarded-Proto $scheme;\n" +
            "\n" +
            "      proxy_pass http://backserver;\n" +
            "      proxy_read_timeout  90;\n" +
            "\n" +
            "      proxy_http_version 1.1;\n" +
            "      proxy_request_buffering off;\n" +
            "\n" +
            "\n" +
            "    }\n" +
            "}\n" +
            "\n";
}
