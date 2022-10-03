package com.messimari.restaurantml.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    private Integer status;

    private String title;

    private String detail;

    private String messageUser;

    public Problem(Problem problem) {
        this.status = problem.getStatus();
        this.title = problem.getTitle();
        this.detail = problem.getDetail();
        this.messageUser = problem.getMessageUser();
    }
}
