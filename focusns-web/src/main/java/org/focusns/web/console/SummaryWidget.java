

package org.focusns.web.console;

import org.focusns.model.env.Environment;
import org.focusns.service.env.EnvironmentService;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Widget
public class SummaryWidget {

    @Autowired
    private EnvironmentService environmentService;

    public String os(Map<String, Object> model) {
        Environment envOS = environmentService.lookupEnvironment(Environment.Type.OS);
        model.put("envOS", envOS);
        return "console/summary-os";
    }

    public String jre(Map<String, Object> model) {
        Environment envJava = environmentService.lookupEnvironment(Environment.Type.JRE);
        model.put("envJava", envJava);
        return "console/summary-jre";
    }

    public String db(Map<String, Object> model) {
        Environment envDB = environmentService.lookupEnvironment(Environment.Type.DB);
        model.put("envDB", envDB);
        return "console/summary-db";
    }

}
