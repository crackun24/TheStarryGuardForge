package xyz.starrylandserver.thestarryguardforge;

import net.minecraftforge.fml.loading.FMLPaths;
import xyz.starrylandserver.thestarryguardforge.Adapter.TgAdapter;

import java.io.IOException;

//模组的主处理类
public class TgMain {
   TsConfig config;//配置对象
    TgAdapter adapter;//不同加载器的适配器
    Lang lang;//语言类

    private TgMain()//私有构造函数
    {

    }
    public void LoadLang() throws IOException {
       this.lang = Lang.LoadLang(this.adapter.GetLangFilePath());//加载语言文件
    }

    public void LoadConf() throws IOException//加载配置文件
    {
        String conf_path = FMLPaths.CONFIGDIR.get().toString();
        this.config = TsConfig.LoadConfig(conf_path);//加载配置文件
    }

    public void start()//启动服务
    {
        try{

            LoadLang();
            LoadConf();//加载配置文件
        }catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    public static TgMain getInstance(TgAdapter adapter)//获取实例
    {
        TgMain temp = new TgMain();
        temp.adapter = adapter;//设置适配器
        return temp;
    }
}
