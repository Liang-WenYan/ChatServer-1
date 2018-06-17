package com.nt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@MapperScan(basePackages = "com.nt.mapper")
@ServletComponentScan
@EnableTransactionManagement 
public class ChatserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatserverApplication.class, args);
	}
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		 // 1、需要先定义一个 convert 转换消息的对象;
	    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();        
	    //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
	    FastJsonConfig fastJsonConfig = new FastJsonConfig();
	    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);       
	    //3、在convert中添加配置信息.
	    fastConverter.setFastJsonConfig(fastJsonConfig);
	    HttpMessageConverter<?> converter = fastConverter;
	    return new HttpMessageConverters(converter);
	}
}
