package com.xiaomo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@Data
public class Category {

    @NotNull(groups = Update.class)// 校验分组：声明在 方法中的validated的参数中有该分组，才会需要校验该属性
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;// 创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createTime;// 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updateTime;// 更新时间

    // 如果说某个校验没有指定分组，默认属于Default分组
    // 分组之间可以继承，A extends B ,那么A中拥有B种所有的校验项
    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
