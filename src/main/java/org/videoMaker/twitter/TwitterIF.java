package org.videoMaker.twitter;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

@ImplementedBy(TwitterIF.Impl.class)
public interface TwitterIF {

    ResponseList<Status> getTweets();

    class Impl implements TwitterIF {

        private final ResponseList<Status> timelineTweets;
        private final List<String> images;

        @Inject
        public Impl(ResponseList<Status> timelineTweets,
                    List<String> images) {
            this.timelineTweets = timelineTweets;
            this.images = images;
        }

        @Override
        public ResponseList<Status> getTweets() {

            // Creates a twitter4j configuration
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("")
                    .setOAuthConsumerSecret("")
                    .setOAuthAccessToken("")
                    .setOAuthAccessTokenSecret("");

            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            // Gathering 20 responses
            ResponseList<Status> responseList;
            try {
                responseList = twitter.getHomeTimeline();
            }
            catch (TwitterException te) {
                return null;
            }

            return responseList;

        }

    }

}