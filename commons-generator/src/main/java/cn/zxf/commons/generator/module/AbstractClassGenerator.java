package cn.zxf.commons.generator.module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

/**
 * 类抽象生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
@Slf4j
public abstract class AbstractClassGenerator implements ModuleConstant {

    protected final ClassTypeEnum    classType;
    protected final ModuleDescriptor desc;

    protected AbstractClassGenerator( ClassTypeEnum classType, ModuleDescriptor desc ) {
	super();
	this.classType = classType;
	this.desc = desc;
    }

    /**
     * 生成文件
     */
    public void generateFile() {
	if ( this.desc == null ) {
	    throw new NullPointerException( "desc null !!!" );
	}
	String format = this.getClassFormat();
	String content = format.replace( SIGN_AUTHOR, desc.author )//
	        .replace( SIGN_CLASS_NAME, desc.moduleEntityName + classType.classSuffix )//
	        .replace( SIGN_COMMENT, desc.moduleComment + " " + classType.commentSuffix )//
	        .replace( SIGN_DATE, LocalDate.now().format( DateTimeFormatter.ofPattern( "yyyy/M/d" ) ) )//
	        .replace( SIGN_MODULE, desc.moduleEntityName )//
	        .replace( SIGN_PACKAGE, desc.basicPackage + "." + desc.modulePackage )//
	        .replace( SIGN_API_PATH, desc.apiPath )//
	        .replace( SIGN_COLLECTION, desc.moduleEntityCollection )//
	;
	Path path = this.getFilePath();
	try {
	    Files.write( path, content.getBytes() );
	} catch ( IOException e ) {
	    log.error( "生成 " + this.classType.classSuffix + " 文件时出错！", e );
	}
    }

    // 获取类文件保存路径
    private Path getFilePath() {
	String fileName = desc.moduleEntityName + classType.classSuffix + ".java";
	return Paths.get( desc.basicPath + classType.parentPath, fileName );
    }

    /**
     * 获取类文件内容格式
     * 
     * @return
     */
    protected abstract String getClassFormat();

}
