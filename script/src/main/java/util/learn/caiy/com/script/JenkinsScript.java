package util.learn.caiy.com.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 批量删除jenkins上的jobs
 * http://app.int.jumei.com:8080/jenkins/cli/
 * java -jar /Users/admin/Downloads/jenkins-cli.jar -s http://app.int.jumei.com:8080/jenkins/ delete-job
 * default_4.2_119754 default_4.2_122149  --username aaaaaa --password xxxxxx
 */
public class JenkinsScript {

    private static String jobs = "default_4.2_119754\n"
            + "default_4.9_release\n"
            + "default_pack_default_4.3_bd";

    public static void main(String[] args){

        String command = "java -jar /Users/admin/Downloads/jenkins-cli.jar -s http://app.int.jumei.com:8080/jenkins/ "
                + "delete-job";
        String[] jobArray = jobs.split("\\n");
        System.out.println(jobArray.length);
        StringBuilder builder = new StringBuilder();
        builder.append(command);
        for(int i=0;i<jobArray.length;i++){
            builder.append(" ").append(jobArray[i]);
        }
        builder.append(" --username aaaaaa --password xxxxxx");
        System.out.println(builder.toString());

        exeCmd(builder.toString());
    }

    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        BufferedReader br4Error = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println("Input流:\\n" + sb.toString());

            br4Error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line4Error = null;
            StringBuilder sb4Error = new StringBuilder();
            while ((line4Error = br4Error.readLine()) != null) {
                sb4Error.append(line4Error + "\n");
            }
            System.out.println("Error流:\\n" + sb4Error.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (br4Error != null)
            {
                try {
                    br4Error.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
