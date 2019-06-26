package org.mybatis.generator.internal;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: Generator.java, V 0.1 2019/3/5 上午11:11 wanggengen Exp $$
 **/
public class Generator {

    /**
     * 运行方法生成：domin、dao、mapper
     * @throws Exception
     */
    @Test
    public void generate() throws Exception{
        List<String> warnings = new ArrayList<String>();
        File configFile = new File("src/main/resources/Mybatis-Gen/generateConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }


}
