import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;

/**
 * @author Jonas (Jonas@inventi.lt)
 */
public class ElasticClient {
    public static void main(String[] args) {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "jonas").build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        SearchResponse searchResponse = client.prepareSearch("inventi")
                .setQuery(QueryBuilders.matchAllQuery()).setSearchType(SearchType.COUNT)
                .addAggregation(AggregationBuilders.extendedStats("ageStats").field("age"))
                .addAggregation(AggregationBuilders.terms("names").field("name")).get();

        ExtendedStats stats = searchResponse.getAggregations().get("ageStats");
        System.out.println("age avg:" + stats.getAvg());
        System.out.println("age min:" + stats.getMin());
        System.out.println("age max:" + stats.getMax());
        System.out.println("age sum:" + stats.getSum());
        System.out.println("age deviation:" + stats.getStdDeviation());
        Terms names = searchResponse.getAggregations().get("names");
        for (Terms.Bucket bucket: names.getBuckets()) {
            System.out.println("name: " + bucket.getKey()
                    + " " + "how many?: "+ bucket.getDocCount());
        }
    }
}
