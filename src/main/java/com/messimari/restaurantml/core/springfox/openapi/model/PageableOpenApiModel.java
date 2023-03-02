package com.messimari.restaurantml.core.springfox.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Pageable")
public class PageableOpenApiModel {

    @ApiModelProperty(example = "5", value = "Tamanho de conteudos na pagina")
    private Integer size;

    @ApiModelProperty(example = "1", value = "pagina: ela se inicia na pag 0")
    private Integer page;

    @ApiModelProperty(example = "name,asc", value = "aqui utilizamos os filtros")
    private List<String> sort;
}
