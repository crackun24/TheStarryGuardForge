package xyz.starrylandserver.thestarryguardforge;

import net.minecraftforge.fml.loading.FMLPaths;
import xyz.starrylandserver.thestarryguardforge.Adapter.TgAdapter;
import xyz.starrylandserver.thestarryguardforge.DataBaseStorage.DataBase;
import xyz.starrylandserver.thestarryguardforge.DataBaseStorage.Mysql;
import xyz.starrylandserver.thestarryguardforge.DataBaseStorage.Sqlite;
import xyz.starrylandserver.thestarryguardforge.DataType.QueryTask;
import xyz.starrylandserver.thestarryguardforge.Operation.DataQuery;
import xyz.starrylandserver.thestarryguardforge.Operation.DataStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//模组的主处理类
public class TgMain {
   TsConfig config;//配置对象
    TgAdapter adapter;//不同加载器的适配器
    Lang lang;//语言类
    DataBase database;//数据库管理类
    DataStorage dataStorage;//数据存储类
    DataQuery dataQuery;//数据查询类


    private TgMain()//私有构造函数
    {

    }

    private void InitDatabase()//初始化数据库
    {
        adapter.LOGGER_INFO(this.lang.getVal("initializing_database"));
        switch (this.config.GetValue("data_storage_type"))
        {
            case "mysql" ->
            {
                this.database = Mysql.GetMysql(this.config);
            }
            case "sqlite"->
            {
                String sqlite_file_path = this.adapter.GetConfigFilePath();
                sqlite_file_path += "/TheStarryGuard" + Sqlite.FILE_NAME;//构建存放数据库文件的路径
                adapter.LOGGER_WARN(sqlite_file_path);//FIXME
                this.database = Sqlite.GetSqlite(new File(sqlite_file_path));
            }
            default -> throw new RuntimeException("Could not confirm database type you need.");
        }
        adapter.LOGGER_INFO(this.lang.getVal("database_connected"));
        this.dataQuery = DataQuery.GetDataQuery(this.database,this.adapter,this.lang);
        this.dataStorage = DataStorage.GetDataStorage(this.database,this.adapter);

        this.dataQuery.start();//启用查询服务
        this.dataStorage.start();//启用存储服务
        adapter.LOGGER_INFO("database initialized.");
    }

    public void LoadLang() throws IOException {
        Path file_path = Paths.get( this.adapter.GetLangFilePath() );

        if(!Files.exists( file_path)) {//判断语言文件夹是否存在
            Files.createDirectories(file_path);//不存在则直接创建
        }

        String str_file_path =  file_path.toString() + "/" + this.config.GetValue("lang") + ".properties";
        this.lang = Lang.LoadLang(str_file_path);//加载语言文件
    }

    public void LoadConf() throws IOException//加载配置文件
    {
        String conf_path = FMLPaths.CONFIGDIR.get().toString();
        this.config = TsConfig.LoadConfig(conf_path);//加载配置文件
    }

    public void start()//启动服务
    {
        try{
            LoadConf();//加载配置文件
            LoadLang();//加载语言文件
            InitDatabase();//初始化数据库
        }catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    public synchronized DataQuery getDataQuery()
    {
        return this.dataQuery;
    }

    public synchronized DataStorage dataStorage()
    {
        return this.dataStorage;
    }

    public static TgMain getInstance(TgAdapter adapter)//获取实例
    {
        TgMain temp = new TgMain();
        temp.adapter = adapter;//设置适配器
        return temp;
    }

}
