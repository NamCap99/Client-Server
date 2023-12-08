import aggregationserver.API;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import shares.Constant;

import java.net.URI;
import java.util.stream.Stream;

/**
 * init server with localhost, port 6969
 */
public class AggregationServer {
    public static void start() {
        final String port = "6969";
        final String serverAddress = "localhost";
        final String url = "http://" + serverAddress + ":" + port;
        URI uri = URI.create(url);
        ResourceConfig resourceConfig = new ResourceConfig(API.class);

        Server server = JettyHttpContainerFactory.createServer(uri, resourceConfig, false);
        Stream.of(server.getConnectors()).flatMap(connector -> connector.getConnectionFactories().stream())
                .filter(connFactory -> connFactory instanceof HttpConnectionFactory)
                .forEach(httpConnFactory -> ((HttpConnectionFactory) httpConnFactory).getHttpConfiguration().setSendServerVersion(false));
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length == 1) Constant.DIR_FILE_AGGREGATION = args[0];
        start();
    }
}
