package pro.aem.mason.services.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.aem.mason.services.RunJobService;

import java.util.HashMap;
import java.util.Map;

@Service(RunJobService.class)
@Component(immediate = true)
public class RunJobServiceImpl implements RunJobService {

    private static final Logger LOG = LoggerFactory.getLogger(RunJobServiceImpl.class);

    @Reference
    private JobManager jobManager;

    public String startScheduledJob(String msg) {
        final Map<String, Object> props = new HashMap<String, Object>();
        props.put("talk", "Hi, Rick!\n" + msg);
        props.put("count", 0);

        Job job = jobManager.addJob("fast/event/adventure", props);

        LOG.warn("\n\nCore Bundle:Job was added to queue(default)!\nMsg:" + msg + " \n");

        return job.getProperty("talk", String.class);
    }
}
