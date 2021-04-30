package com.chuang.anarres.ca;

import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.StringKit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
 * sudo certbot renew //--dry-run
 *
 * 失败提示：DNS problem
 *
 */
public class SSLGenerator {
    private final static Scanner input = new Scanner(System.in);
    private static final String SAVE_PATH = "C:\\Users\\admin\\Desktop\\ssl";
    private static final boolean WITH_3W = true;
    private static final int BATCH = 300;//一波最多300条，不要改

    public static void main(String[] args) {
//        genSH(SAVE_PATH + "\\sh"); //生成脚本

//        genNginxCfg(SAVE_PATH + "\\cfg"); //生成配置文件
        genNginxCfgJoin(SAVE_PATH + "\\cfg"); //生成配置文件

        System.out.println("完成！");
    }

    public static void genSH(String dir) {
        Set<String> domains = userInput(dir);

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
            genSH(sets.get(i), dir, i);
        }

        System.out.println("=========教程=======\r\n将生成的 input.data 和 run.sh 上传到相应的服务器上。运行如下命令\r\n1. chmod 777 run.sh\r\n2. nohup ./run.sh < input.data &");
    }

    public static void genNginxCfgJoin(String dir) {
        Set<String> domains = userInput(dir);

        StringBuilder cfg = new StringBuilder();
        for(String domain: domains) {
            domain = domain.replaceAll("/", "");
            cfg.append(NGINX_CONFIG.replaceAll("\\$\\{\\{}}", domain)).append("\r\n");
        }
        try {
            File file = FileKit.touch(dir + "/all.conf");

            FileKit.writeUtf8String(cfg.toString(), file);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void genNginxCfg(String dir) {
        Set<String> domains = userInput(dir);

        for(String domain: domains) {
            try {
                File file = FileKit.touch(dir + "/" + domain + ".conf");

                FileKit.writeUtf8String(NGINX_CONFIG.replaceAll("\\$\\{\\{}}", domain), file);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }

    private static void genSH(List<String> domains, String dir, int batch) {
        StringBuilder cmd = new StringBuilder();
        for (int i = 0; i < domains.size(); i++) {
            String domain = domains.get(i);
            cmd.append("echo \"=======================[").append(domain).append(":(").append(i).append(")]============================S\"").append("\n");
            if(WITH_3W) {
                cmd.append("certbot certonly  --nginx -d ").append(domain).append(" -d www.").append(domain).append("\n");
            } else {
                cmd.append("certbot certonly --nginx -d ").append(domain).append("\n");
            }
            cmd.append("echo \"=======================[").append(domain).append(": (").append(i).append(")]============================E\"").append("\n\n\n");
        }

        cmd.append("echo \"done!!!\"");
        try {
            FileKit.writeUtf8String(cmd.toString(), dir + "/" + batch + "/run.sh");
            FileKit.writeUtf8String(inputData, dir + "/" + batch + "/input.data");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    private static Set<String> inputDomains() {
        List<String> inputs = new ArrayList<>();
//        do {
//            System.out.print("请输入域名, 多个域名以(逗号，空格，回车)隔开, 按n结束:");
//            String line = input.next();
//            if("n".equalsIgnoreCase(line)){
//                break;
//            }
//            inputs.add(line);
//        } while(true);
        System.out.println("请输入域名, 多个域名以(逗号，空格，回车)隔开, 按n结束:");
        String line;
        do {
            line = input.nextLine();
            inputs.add(line);
        } while(!StringKit.isBlank(line));

        return inputs.stream()
                .flatMap(SSLGenerator::toStream)
                .filter(StringKit::isNotBlank)
                .collect(Collectors.toSet());
    }

    private static void continueOrExit() {
        System.out.print("请确认是否继续 (Y)es, (N)o:");
        String line = input.next();
        if("no".equalsIgnoreCase(line) || "n".equalsIgnoreCase(line)) {
            System.out.println("程序退出");
            System.exit(0);
        }
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

    private static Set<String> userInput(String dir) {
        FileKit.del(dir);
        FileKit.mkdir(dir);

        Set<String> domains = inputDomains();
        System.out.println("共计" + domains.size() + "条记录");
        continueOrExit();
        return domains;
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

    public static final String inputData;

    static {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 150; i++) {
            s.append("1\r\n");
        }
        inputData = s.toString();
    }
}
