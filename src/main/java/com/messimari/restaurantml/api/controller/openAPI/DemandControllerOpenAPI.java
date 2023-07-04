package com.messimari.restaurantml.api.controller.openAPI;

import com.messimari.restaurantml.api.handler.Problem;
import com.messimari.restaurantml.api.model.dto.demand.DemandCompleteDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandToRestaurantDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import java.util.List;
@Api(tags = "Demand")
public interface DemandControllerOpenAPI {

    @ApiOperation("Create demand")
    void createDemand(DemandCompleteDTO demand);

    @ApiOperation("Find all demand by id of Restaurant")
    PagedModel<DemandToRestaurantDTO> findAllDemandByIdRestaurant(@ApiParam(name = "Id of restaurant", example = "1") Long id);

    @ApiOperation("Find by id of demand")
    DemandDTO findByIdDemand(Long id);

    @ApiOperation("Update demand")
    void updateDemand(Long id, DemandCompleteDTO demand);

    @ApiOperation("Update Status Demand")
    @ApiResponses({
            @ApiResponse(code = 204, message = "update status sucessful, but no content response")
    })
    void updateStatusDemand(@ApiParam(value = "id of demand", example = "1") Long id, StatusDemand statusDemand);
}
