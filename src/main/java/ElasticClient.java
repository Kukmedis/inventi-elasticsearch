import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author Jonas (Jonas@inventi.lt)
 */
public class ElasticClient {
    public static void main(String[] args) {
        Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        /**
        YOUR QUERIES HERE:
        SearchResponse searchResponse = client.prepareSearch("inventi")
                .setQuery(QueryBuilders.matchAllQuery()).get();
        **/
        client.close();
    }
}
