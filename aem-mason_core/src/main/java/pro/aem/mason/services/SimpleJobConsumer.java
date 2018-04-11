package pro.aem.mason.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Service(value = {JobConsumer.class})
@Property(name = JobConsumer.PROPERTY_TOPICS, value = "fast/event/adventure")
public class SimpleJobConsumer implements JobConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleJobConsumer.class);

    public JobResult process(Job job) {
        String talk = job.getProperty("talk", String.class);

        LOG.warn("\n\nCore Bundle: consumer for topic: fast/event/adventure\n" + talk + "\n\n");

        return JobResult.OK;
    }
}
