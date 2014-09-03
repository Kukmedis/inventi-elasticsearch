import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * @author Jonas (Jonas@inventi.lt)
 */
public class ElasticServer {
    public static void main(String[] args) {
        Node node = nodeBuilder().client(false).node();
        node.start();
        while (true) {

        }
    }
}
