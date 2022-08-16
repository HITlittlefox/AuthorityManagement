package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TestVo {

    @NotBlank(message = "msg can't be blank")
    private String msg;

    @NotNull(message = "id can't be null")
    @Max(value = 10, message = "id < 10")
    @Min(value = 0, message = "id >= 0")
    private Integer id;

    private List<String> str;
}
