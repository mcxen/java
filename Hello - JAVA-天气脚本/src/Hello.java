import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Hello {
    public static void main(String[] args) throws IOException {
        aries a=new aries();
        a.output();
        System.out.println("hello");
    //今日诗词
        InputStream in,in2;
        in = new URL( "**" ).openStream();
        try {
            InputStreamReader inR = new InputStreamReader( in );
            BufferedReader buf = new BufferedReader( inR );
            String line;
            StringBuilder sb=new StringBuilder();
            while ( ( line = buf.readLine() ) != null ) {
                //System.out.println( line );
                sb.append(line);
            }
            JSONObject json = new JSONObject(sb.toString());
            JSONObject shi=json.getJSONObject("data").getJSONObject("origin");
            System.out.println("今日诗词： "+json.getJSONObject("data").get("content")+"\n作者是:"+shi.get("author")+"\n");
        } finally {
            in.close();
        }

        //查询天气；
        in2 = new URL( "***" ).openStream();
        try {
            InputStreamReader inR = new InputStreamReader( in2 );
            BufferedReader buf = new BufferedReader( inR );
            String line;
            StringBuilder sb=new StringBuilder();
            while ( ( line = buf.readLine() ) != null ) {
                //System.out.println( line );
                sb.append(line);
            }
            JSONObject json = new JSONObject(sb.toString());
            Object wjson=json.getJSONArray("HeWeather6").get(0);
            JSONObject wzjson = new JSONObject(wjson.toString()).getJSONObject("basic");
            System.out.println("我找到你啦，你就在"+wzjson.get("location")+"。对波？");
            JSONObject wdjson = new JSONObject(wjson.toString()).getJSONObject("now");
            System.out.println("目前温度为："+wdjson.get("tmp")+"。 风向为"+wdjson.get("wind_dir")+"。");
        } finally {
            in.close();
        }

    }
}
//颜色一样同一类元素。
