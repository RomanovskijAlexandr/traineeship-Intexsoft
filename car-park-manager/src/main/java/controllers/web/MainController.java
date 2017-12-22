package controllers.web;

import javassist.tools.reflect.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.BusService;
import services.DriveService;
import services.RoteService;

import java.util.Locale;


@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(Sample.class.getName());

    @Autowired
    BusService busService;

    @Autowired
    DriveService driveService;

    @Autowired
    RoteService roteService;

    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = "buses", method = RequestMethod.GET)
    public String indexBus(ModelMap model) {
        logger.info("do method indexBus() in MainController");
        logger.info(messageSource.getMessage("entities.title", null, Locale.getDefault()));
        StringBuilder sb = new StringBuilder("<br>");
        sb.append("Buses").append("<br>");
        busService.getBuses().forEach(it->sb.append(it.toString()).append("<br>"));
        model.put("msg", sb.toString());
        return "index";
    }

    @RequestMapping(value = "drivers", method = RequestMethod.GET)
    public String indexDriver(ModelMap model) {
        logger.info("do method indexDriver() in MainController");
        StringBuilder sb = new StringBuilder("<br>");
        sb.append("Drivers:").append("<br>");
        driveService.getDrivers().forEach(it->sb.append(it.toString()).append("<br>"));
        model.put("msg", sb.toString());
        return "index";
    }

    @RequestMapping(value = "rotes", method = RequestMethod.GET)
    public String indexRote(ModelMap model) {
        logger.info("do method indexRote() in MainController");
        StringBuilder sb = new StringBuilder("<br>");
        sb.append("Rotes:").append("<br>");
        roteService.getRotes().forEach(it->sb.append(it.toString()).append("<br>"));
        model.put("msg", sb.toString());
        return "index";
    }
}
