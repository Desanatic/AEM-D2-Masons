package pro.aem.mason.replication;

import com.day.cq.replication.*;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ValueMap;

import java.util.List;

/**
 * Created by Vladyslav_Dubinin on 10/12/2016.
 */
@Component(policy = ConfigurationPolicy.OPTIONAL, immediate = true, metatype = false)
@Service(TransportHandler.class)
public class ReplicationTransportHandler implements TransportHandler {
    public boolean canHandle(AgentConfig agentConfig) {
        return isAemMReplication(agentConfig);
    }

    public ReplicationResult deliver(TransportContext transportContext, ReplicationTransaction replicationTransaction) throws ReplicationException {
        ReplicationAction replicationAction = replicationTransaction.getAction();


        ReplicationActionType actionType = replicationAction.getType();
        if(ReplicationActionType.ACTIVATE.equals(actionType)) {
            System.out.println("ACTIVATE: " + replicationAction.getPath());
            return new ReplicationResult(true, 200, "OK");
        }

        if(ReplicationActionType.DEACTIVATE.equals(actionType)) {
            System.out.println("DEACTIVATE: " + replicationAction.getPath() + "user id: " + replicationAction.getUserId());
            return new ReplicationResult(true, 200, "OK");
        }
        System.out.println("Replication Error!! Path: " + replicationAction.getPath());
        return new ReplicationResult(false, 500, "Error");
    }

    public ReplicationResult poll(TransportContext transportContext, ReplicationTransaction replicationTransaction, List<ReplicationContent> list, ReplicationContentFactory replicationContentFactory) throws ReplicationException {
        return new ReplicationResult(true, 200, "OK");
    }

    private boolean isAemMReplication(AgentConfig agentConfig) {
        ValueMap properties = agentConfig.getProperties();
        String[] headers = properties.get("protocolHTTPHeaders", new String[0]);
        for (String header : headers) {
            if(header.contains("AemMRepAgent")){
                return true;
            }
        }
        return false;
    }
}
