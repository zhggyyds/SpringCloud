package com.hao.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhao
 * @PackageName:com.hao.myrule
 * @Description:
 * @date 2022/4/18 17:41
 **/
@Configuration
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE" , configuration=MySelfRule.class )
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
