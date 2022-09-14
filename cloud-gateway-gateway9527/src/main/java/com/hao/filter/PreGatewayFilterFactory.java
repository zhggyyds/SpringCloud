package com.hao.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhouhao
 * @PackageName:com.hao.filter
 * @Description: 自定义局部过滤器
 * @date 2022/4/26 13:06
 **/
@Component
@Slf4j
// 命名规则就按照XxxxGatewayFilterFactory，在yml文件中配置自定义过滤器的name属性就是Xxxx
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config>{
    public PreGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // 传入的参数名与下面Config类中的属性名一致
        return Arrays.asList("name");
    }

    // 后置自定义过滤器的实现
    @Override
    public GatewayFilter apply(Config config) {
        // grab configuration from Config object
        return (exchange, chain) -> {
            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            //builder可以修改request的各种属性
            if (exchange.getRequest().getQueryParams().getFirst("uname") == null){
                log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    // 后置自定义过滤器的实现
//    @Override
//    public GatewayFilter apply(Config config) {
//        // grab configuration from Config object
//        return (exchange, chain) -> {
                // post方法和pre方法的区别就是这里不一样
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                ServerHttpResponse response = exchange.getResponse();
//                //Manipulate the response in some way
//            }));
//        };
//    }

    public static class Config {
        //对应配置在application.yml配置文件中的过滤器参数名
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
