package demo.nhatthai.cafegrapp.model;

/**
 * Created by nhatthai on 5/12/16.
 */
public class Feed {
    private String username;
    private int feedId;
    private String imgFeedUrl;
    private String name;
    private boolean isLike;

    public Feed(String name, String imgFeedUrl, String username, boolean isLike) {
        this.username = username;
        this.imgFeedUrl = imgFeedUrl;
        this.name = name;
        this.isLike = isLike;
    }

//    public int getFeedId() {
//        return feedId;
//    }

    //public void setFeedId(int feedId) {
    //    this.feedId = feedId;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public boolean getLike() {
        return isLike;
    }
    public String getImageUrl() {
        return imgFeedUrl;
    }

    //public void setImageUrl(String imgFeedUrl) {
    //    this.imgFeedUrl = imgFeedUrl;
    //}
}
