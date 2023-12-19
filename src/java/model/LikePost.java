
package model;


public class LikePost {
    private int id;
    private  int id_Account;
    private  int id_Post;
    
    public LikePost(){}
    
    public LikePost (int id, int id_ACcount, int id_Post){
        this.id = id;
        this.id_Account = id_ACcount;
        this.id_Post = id_Post;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Account() {
        return id_Account;
    }

    public void setId_Account(int id_Account) {
        this.id_Account = id_Account;
    }

    public int getId_Post() {
        return id_Post;
    }

    public void setId_Post(int id_Post) {
        this.id_Post = id_Post;
    }

}
