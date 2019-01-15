package org.hm.demo.mcpreport.webservice.rest;

import org.hm.demo.mcpreport.businessAction.GetKpisAction;
import org.hm.demo.mcpreport.businessAction.GetMetricsAction;
import org.hm.demo.mcpreport.businessAction.ValidateAndLoadRequestAction;
import org.hm.demo.mcpreport.model.Kpis;
import org.hm.demo.mcpreport.model.Metrics;
import org.hm.demo.mcpreport.webservice.rest.exception.DateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Expose the communication reports Rest web service
 */
@RestController
public class GenerateReportController {

    Logger logger = LoggerFactory.getLogger(GenerateReportController.class);


    @RequestMapping("/load")
    public void load(@RequestParam("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
        logger.info("Load data for the date: "+date.toString());

        ValidateAndLoadRequestAction action = new ValidateAndLoadRequestAction(date);
        Boolean result = action.execute();
        if (!result){
            throw new DateNotFoundException();
        }
        //If the data exist, will return the code 200.
    }

    @RequestMapping("/metrics")
    public Metrics metrics() {
        logger.info("Get metrics");

        //The data model exposed in rest should be different from internal data model
        //I believe for this demo this is enough
        GetMetricsAction action = new GetMetricsAction();
        return action.execute();
    }

    @RequestMapping("/kpis")
    public Kpis kpis() {
        logger.info("Get kpis");

        //The data model exposed in rest should be different from internal data model
        //I believe for this demo this is enough
        GetKpisAction action = new GetKpisAction();
        return action.execute();
    }

}
