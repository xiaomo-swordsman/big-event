package com.xiaomo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

// lombok 在编译阶段，为实体类自动生成setter/getter/tostring方法
// 1、引入lombok依赖(org.projectlombok.lombok)
// 2、在实体类上添加注解@Data(lombok.Data)
// 3、maven --> complie 编译既可
@Data
public class User {
    @NotNull //值不能为null
    private int id;//主键id
    private String username;//用户名
    @JsonIgnore // springmvc 将当前对象转json字符时候，忽略password，最终json就没有改password
    private String password;// 用户密码

    @NotEmpty  // 值不能为null，并且内容不能为空
    @Pattern(regexp = "^\\S{1,10}$") //正则表达式，数据长度需要满足1-10位长度
    private String nickname;// 昵称

    @NotEmpty  // 值不能为null，并且内容不能为空
    @Email //必须为邮箱格式
    private String email;// 邮箱
    private String userPic;// 用户头像地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;// 更新时间

}
