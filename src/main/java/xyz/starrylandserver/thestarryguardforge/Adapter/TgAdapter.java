package xyz.starrylandserver.thestarryguardforge.Adapter;

public interface TgAdapter {//不同的模组加载器的适配接口
   public String GetConfigFilePath();//获取配置文件夹的路径
   public String GetLangFilePath();//获取语言文件的路径
   public void LOGGER_INFO(String string);//记录器的输出接口
   public void LOGGER_WARN(String string);
   public void LOGGER_ERROR(String string);
   public void LOGGER_DEBUG(String string);
}
