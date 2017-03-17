package com.gen.jpa.main;

import com.gen.jpa.utils.AnalysisDB;
import com.gen.jpa.utils.Consts;
import com.gen.jpa.utils.TableMeta;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

/**
 * 生成代码入口
 * @author	zhanglikun
 * @date	2013-7-17
 * @desc	XXX
 */
public class GenerateMain {
    public static void main(String[] args) throws Exception {

        List<TableMeta> tableList ;
        Writer out = null ;
        String targetDir = Consts.TARGET_DIR ;

        tableList = AnalysisDB.readDB() ;
        AnalysisDB.readTables(tableList) ;
        // 输出到文件
        File dir = new File(targetDir) ;
        if(!dir.isDirectory()) {
            dir.mkdirs() ;
        }

        Configuration cfg = new Configuration() ;
        cfg.setDirectoryForTemplateLoading(new File("genEntity/src/main/resources")) ;
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template tpl = cfg.getTemplate("model.ftl") ;
        if(tableList != null) {
            for(TableMeta tm : tableList) {
                if(StringUtils.isBlank(tm.getClassName()))continue ;
                out = new FileWriter(new File(targetDir + tm.getClassName() + ".java")) ;
                tpl.process(tm, out) ;
                System.out.println("===文件 " + tm.getClassName() + ".java" + " 生成成功===");
            }
        }

        out.flush() ;
        out.close() ;

    }

}
