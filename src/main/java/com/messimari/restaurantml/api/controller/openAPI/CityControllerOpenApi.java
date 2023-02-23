package com.messimari.restaurantml.api.controller.openAPI;

import com.messimari.restaurantml.api.handler.Problem;
import com.messimari.restaurantml.api.model.dto.city.CityDTO;
import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "City")
public interface CityControllerOpenApi {
    @ApiOperation("Create City")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City created")
    })
    public void createCity(CityRequestDTO city);

    @ApiOperation("find list of Cities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca feita com sucesso"),
            @ApiResponse(code = 400, message = "erro na busca", response = Problem.class)

    })
    public List<CityDTO> findlistCities();

    @ApiOperation("find by id City")
    public CityResponseDTO findByIdcity (@ApiParam(value = "ID of City", example = "1") Long id);
    @ApiOperation("Update City")
    public void updateCity(@ApiParam(value = "ID of City", example = "1") Long id, CityRequestDTO updatedCity);

    @ApiOperation("Delete City")
    public void deleteCity(@ApiParam(value = "ID of City", example = "1") Long id);
}
