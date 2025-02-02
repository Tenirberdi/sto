package com.sto.check.controlller;

import com.sto.check.model.Bid;
import com.sto.check.model.rest.response.RestResponse;
import com.sto.check.service.CheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CheckController {
    private final CheckService checkService;

    @GetMapping("/checks/{id}")
    public RestResponse<Bid> getCheck(@PathVariable Long id) {
        return RestResponse.success(checkService.getCheck(id));
    }
}
