import org.elasticsearch.node.NodeBuilder;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * @author Jonas (Jonas@inventi.lt)
 */
public class ElasticServer {
    public static void main(String[] args) {
        NodeBuilder nodeBuilder = nodeBuilder().client(false);
        nodeBuilder.settings().put("script.disable_dynamic", false);
        nodeBuilder.settings().put("cluster.name", "jonas");
        nodeBuilder.build().start();
        while (true) {

        }
    }
}
