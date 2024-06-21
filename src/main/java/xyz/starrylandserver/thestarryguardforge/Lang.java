package xyz.starrylandserver.thestarryguardforge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Lang {//语言文件类
    private Properties prop;
    private String langFilePath;//语言文件的路径

    private Lang() {

    }
    public synchronized void ReloadConfig() throws IOException {//重新加载语言文件
        File lang_file = new File(this.langFilePath);
        Reader reader = new InputStreamReader(new FileInputStream(lang_file), StandardCharsets.UTF_8);//以UTF-8的字符集读取配置文件数据
        prop.load(reader);
    }

    public synchronized static Lang LoadLang(String file_path) throws IOException {
        Lang temp = new Lang();
        temp.prop = new Properties();//创建一个properties文件的解析对象
        temp.langFilePath = file_path;//设置配置文件的路径
        temp.ReloadConfig();
        return temp;
    }
    public synchronized String getVal(String key)
    {
       return this.prop.getProperty(key);//获取语言
    }
}
