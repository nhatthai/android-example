package demo.nhatthai.cafegrapp.model;

/**
 * Created by nhatthai on 6/20/16.
 */
public class Item {

    private String login;
    private int id;
    private String avatar_url;
    private String type;
    private boolean siteAdmin;

    /**
     * @return The login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login The login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The avatarUrl
     */
    public String getAvatarUrl() {
        return avatar_url;
    }

    /**
     * @param avatar_url The avatar_url
     */
    public void setAvatarUrl(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The siteAdmin
     */
    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    /**
     * @param siteAdmin The site_admin
     */
    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

}
