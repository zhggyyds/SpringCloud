package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouhao
 * @PackageName:com.hao.entities
 * @Description: 用于将对象转换为通用结果，方便前端人员
 * @date 2022/4/15 16:30
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
