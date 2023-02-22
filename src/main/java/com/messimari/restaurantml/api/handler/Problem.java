package com.messimari.restaurantml.api.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;
    @ApiModelProperty(example = "Infringiu validações basicas")
    private String title;

    @ApiModelProperty(example = "Você não passou pelas validações basicas.")
    private String detail;

    @ApiModelProperty(example = "Há alguns erros basico, favor corrigi-los para dar continuidade.")
    private String messageUser;

    public Problem(Problem problem) {
        this.status = problem.getStatus();
        this.title = problem.getTitle();
        this.detail = problem.getDetail();
        this.messageUser = problem.getMessageUser();
    }
}
