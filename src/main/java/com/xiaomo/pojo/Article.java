package com.xiaomo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaomo.anno.State;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class Article {
    @NotNull
    private int id;//主键id
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$") //正则表达式，数据长度需要满足1-10位长度
    private String title;//文章标题
    private String content;// 文章内容
    @NotEmpty
    @URL
    private String coverImg;// 封面图像
    @State
    private String state;// 发布状态，已发布/草稿
    private Integer categoryId;// 文章分类ID
    private Integer createUser;//  创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;// 更新时间

}
