package pro.aem.mason.workflow.step;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;


/**
 * Created by Vladyslav_Dubinin on 22/11/2016.
 */

@Component
@Service
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Test workflow step description."),
        @Property(name = Constants.SERVICE_VENDOR, value = "Adobe"),
        @Property(name = "process.label", value = "Test Workflow Step Name")})
public class CustomStep implements WorkflowProcess {
    private static final String CONTENT_PATH = "/content";
    private static final String MASON_MSG_NODE_PATH = "mason-msg";
    private static final String JCR_MSG = "jcr:msg";
    private static final String JCR_TIME = "jcr:time";

    @Reference
    private SlingRepository repository;

    public void execute(WorkItem item, WorkflowSession wfsession, MetaDataMap args) throws WorkflowException {
        try {
            Session session = repository.loginAdministrative(null);
            Node node = session.getNode(CONTENT_PATH);
            
            if(node.hasNode(MASON_MSG_NODE_PATH)){
                return;
            }
            
            Node msg = node.addNode(MASON_MSG_NODE_PATH, NodeType.NT_UNSTRUCTURED);
            msg.setProperty(JCR_MSG, "workflow is completed");
            msg.setProperty(JCR_TIME, item.getTimeStarted().getTime());
            session.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}