package net.socket;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: ylwys
 * Date: 13-1-24
 * Time: 下午3:09
 */
public class MyService {

    public static void main(String[] args) throws Exception {
        // 配置Server
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        // 设置pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new EchoServerHandler());
            }
        });

        // 绑定，开始接受client连接
        bootstrap.bind(new InetSocketAddress(8080));
    }
}
